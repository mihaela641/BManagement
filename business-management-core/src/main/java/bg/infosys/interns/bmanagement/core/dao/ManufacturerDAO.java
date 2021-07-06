package bg.infosys.interns.bmanagement.core.dao;

import java.time.LocalDate;
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
import bg.infosys.interns.bmanagement.core.dto.ManufacturerFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ManufacturerDAO extends GenericDAOImpl<Manufacturer, Integer> {
		
	public List<Manufacturer> getAll() {
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Manufacturer> criteria = builder.createQuery(Manufacturer.class);
		Root<Manufacturer> root = criteria.from(Manufacturer.class);
		
		//root.fetch("address", JoinType.LEFT).fetch("country", JoinType.LEFT);
		//root.fetch("products", JoinType.LEFT).fetch(Product._type, JoinType.LEFT).fetch(Type._parentType, JoinType.LEFT);
		root.fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		//root.fetch(Manufacturer._products, JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Manufacturer._isDeleted), false));

		return createQuery(criteria).getResultList();
	}
	
	public Manufacturer getById (Integer id) {
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Manufacturer> criteria = builder.createQuery(Manufacturer.class);
		Root<Manufacturer> root = criteria.from(Manufacturer.class);
		
		root.fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		//root.fetch("products", JoinType.LEFT).fetch(Product._type, JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		
		criteria.select(root);
		
		criteria.where(builder.equal(root.get("id"), id),builder.equal(root.get(Manufacturer._isDeleted), false));
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Manufacturer> findAllByFilter(ManufacturerFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Manufacturer> criteria = builder.createQuery(Manufacturer.class);
		Root<Manufacturer> root = criteria.from(Manufacturer.class);
		
		root.fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		//root.fetch("products", JoinType.LEFT).fetch(Product._type, JoinType.LEFT).fetch(Type._parentType, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Manufacturer._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getAddress() != null) {			
			predicates.add(builder.equal(root.get(Manufacturer._address).get(Address._street), filter.getAddress()));
		}
		
		if (filter.getFoundationDate() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Manufacturer._foundationDate).as(LocalDate.class), filter.getFoundationDate()));
		}
		
		SortingUtil.<Manufacturer>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Manufacturer._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter (ManufacturerFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Manufacturer> root = criteria.from(Manufacturer.class);
		
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		root.join(Manufacturer._address, JoinType.LEFT).join(Address._country,JoinType.LEFT);
		//root.join("products", JoinType.LEFT).join(Product._type, JoinType.LEFT).join(Type._parentType,JoinType.LEFT);
	
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Manufacturer._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getAddress() != null) {			
			predicates.add(builder.equal(root.get(Manufacturer._address).get(Address._street), filter.getAddress()));
		}
		
		if (filter.getFoundationDate() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Manufacturer._foundationDate).as(LocalDate.class), filter.getFoundationDate()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Manufacturer._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
		
	
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Manufacturer> criteria = builder.createCriteriaUpdate(Manufacturer.class);
		Root<Manufacturer> root = criteria.from(Manufacturer.class);
		
		criteria.set(Manufacturer._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}

}
  