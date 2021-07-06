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
import bg.infosys.interns.bmanagement.core.dto.ProductFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ProductDAO extends GenericDAOImpl<Product, Integer>{

	public List<Product>getAll(){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		root.fetch(Product._type, JoinType.LEFT).fetch(Type._parentType, JoinType.LEFT);
		root.fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(Product._tags, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Product._isDeleted), false));

		return createQuery(criteria).getResultList();
	}
	
	public Product getById(Integer id){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		root.fetch(Product._type, JoinType.LEFT).fetch(Type._parentType, JoinType.LEFT);
		root.fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(Product._tags, JoinType.LEFT);
		
		criteria.select(root);
		
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Product._isDeleted), false));
	
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Product> findAllByFilter(ProductFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		root.fetch(Product._type, JoinType.LEFT).fetch(Type._parentType, JoinType.LEFT);
		root.fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(Product._tags, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Product._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getType() != null) {			
			predicates.add(builder.equal(root.get(Product._type).get(_id), filter.getType()));
		}
		
		if (filter.getPrice() != null) {			
			predicates.add(builder.equal(root.get(Product._price), filter.getPrice()));
		}
		
		if (filter.getDateFrom() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Product._dateAdded).as(LocalDate.class), filter.getDateFrom()));
		}
		
		if (filter.getDateTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Product._dateAdded).as(LocalDate.class), filter.getDateTo()));
		}
		if (filter.getManufacturer() != null) {
			predicates.add(builder.equal(root.get(Product._manufacturer).as(Manufacturer.class), filter.getManufacturer()));
		}
		
		SortingUtil.<Product>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Product._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(ProductFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Product> root = criteria.from(Product.class);
		
		root.join(Product._type, JoinType.LEFT);
		root.join(Product._manufacturer, JoinType.LEFT).join(Manufacturer._address, JoinType.LEFT).join(Address._country, JoinType.LEFT);
		root.join(Product._tags, JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Product._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getType() != null) {			
			predicates.add(builder.equal(root.get(Product._type), filter.getType()));
		}
		
		if (filter.getPrice() != null) {			
			predicates.add(builder.equal(root.get(Product._price), filter.getPrice()));
		}
		
		if (filter.getDateFrom() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Product._dateAdded).as(LocalDate.class), filter.getDateFrom()));
		}
		
		if (filter.getDateTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Product._dateAdded).as(LocalDate.class), filter.getDateTo()));
		}
		
		if (filter.getManufacturer() != null) {
			predicates.add(builder.equal(root.get(Product._manufacturer).as(Manufacturer.class), filter.getManufacturer()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Product._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Product> criteria = builder.createCriteriaUpdate(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		criteria.set(Product._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}
