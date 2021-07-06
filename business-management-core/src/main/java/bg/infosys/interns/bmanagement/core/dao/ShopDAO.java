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
import bg.infosys.interns.bmanagement.core.dto.ShopFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ShopDAO extends GenericDAOImpl<Shop, Integer>{
	public List<Shop>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Shop> criteria = builder.createQuery(Shop.class);
		Root<Shop> root = criteria.from(Shop.class);
		 
		root.fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Shop._isDeleted), false));
		
		return createQuery(criteria).getResultList();
	}
	
	public Shop getById(Integer id){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Shop> criteria = builder.createQuery(Shop.class);
		Root<Shop> root = criteria.from(Shop.class);
		 
		root.fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Shop._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Shop> findAllByFilter(ShopFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Shop> criteria = builder.createQuery(Shop.class);
		Root<Shop> root = criteria.from(Shop.class);
		
		root.fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getAddress() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._address).get(Address._street)),"%" + filter.getAddress().toLowerCase()+"%"));
		}
		
		if (filter.getPhoneNumber() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._phoneNumber)), "%" + filter.getPhoneNumber().toLowerCase() + "%"));
		}
	
		if (filter.getStatus() != null) {			
			predicates.add(builder.equal(root.get(Shop._status), filter.getStatus()));
		}
	
		
		SortingUtil.<Shop>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Shop._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(ShopFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Shop> root = criteria.from(Shop.class);
		
		root.join(Shop._address, JoinType.LEFT).join(Address._country, JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getAddress() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._address).get(Address._street)),"%" + filter.getAddress().toLowerCase()+"%"));
		}
		
		if (filter.getPhoneNumber() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Shop._phoneNumber)), "%" + filter.getPhoneNumber().toLowerCase() + "%"));
		}
	
		if (filter.getStatus() != null) {			
			predicates.add(builder.equal(root.get(Shop._status), filter.getStatus()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Shop._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Shop> criteria = builder.createCriteriaUpdate(Shop.class);
		Root<Shop> root = criteria.from(Shop.class);
		
		criteria.set(Shop._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}