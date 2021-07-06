package bg.infosys.interns.bmanagement.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDAOImpl;
import bg.infosys.interns.bmanagement.core.dto.AddressFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class AddressDAO  extends GenericDAOImpl<Address, Integer>{
	public List<Address>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> root = criteria.from(Address.class);
		 
		root.fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Address._isDeleted), false));
		
		return createQuery(criteria).getResultList();
	}
	
	public Address getById(Integer id){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> root = criteria.from(Address.class);
		 
		root.fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Address._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Address> findAllByFilter(AddressFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> root = criteria.from(Address.class);
		
		root.fetch(Address._country, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getCountry() != null) {			
			predicates.add(builder.equal(root.get(Address._country).get(_id), filter.getCountry()));
		}
		
		if (filter.getStreet() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Address._street)), "%" + filter.getStreet().toLowerCase() + "%"));
		}
	
		
		SortingUtil.<Address>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Address._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(AddressFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Address> root = criteria.from(Address.class);
		
		root.join(Address._country, JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getCountry() != null) {			
			predicates.add(builder.equal(root.get(Address._country).get(_id), filter.getCountry()));
		}
		
		if (filter.getStreet() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Address._street)), "%" + filter.getStreet().toLowerCase() + "%"));
		}
	
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Address._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Address> criteria = builder.createCriteriaUpdate(Address.class);
		Root<Address> root = criteria.from(Address.class);
		
		criteria.set(Address._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}