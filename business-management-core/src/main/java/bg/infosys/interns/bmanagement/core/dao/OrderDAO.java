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
import bg.infosys.interns.bmanagement.core.dto.OrderFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class OrderDAO extends GenericDAOImpl<Order, Integer>{

public List<Order>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);
		
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Order._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Order._isDeleted), false));

		return createQuery(criteria).getResultList();
		
	}
	
	public Order getById(Integer id){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);
		
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Order._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		
		criteria.select(root);
		
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Order._isDeleted), false));
	
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Order> findAllByFilter(OrderFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);
		
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(Order._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Order._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getCode() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Order._code)), "%" + filter.getCode().toLowerCase() + "%"));
		}
		
		if (filter.getProduct() != null) {			
			predicates.add(builder.equal(root.get(Order._product).get(Product._name), filter.getProduct()));
		} 
		
		if (filter.getShop() != null) {			
			predicates.add(builder.equal(root.get(Order._shop).get(Shop._name), filter.getShop()));
		} 
		
		if (filter.getQuantity() != null) {			
			predicates.add(builder.equal(root.get(Order._quantity), filter.getQuantity()));
		}
		
		if (filter.getStatus() != null) {			
			predicates.add(builder.equal(root.get(Order._status), filter.getStatus()));
		}
		
		SortingUtil.<Order>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Order._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(OrderFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Order> root = criteria.from(Order.class);
		
		root.join(Order._product,JoinType.LEFT).join(Product._type,JoinType.LEFT).join(Type._parentType,JoinType.LEFT);
		root.join(Order._product,JoinType.LEFT).join(Product._tags,JoinType.LEFT);
		root.join(Order._product,JoinType.LEFT).join(Product._manufacturer,JoinType.LEFT).join(Manufacturer._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(Order._shop,JoinType.LEFT).join(Shop._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getCode() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Order._code)), "%" + filter.getCode().toLowerCase() + "%"));
		}
		
		if (filter.getProduct() != null) {			
			predicates.add(builder.equal(root.get(Order._product).get(Product._name), filter.getProduct()));
		}
		
		if (filter.getShop() != null) {			
			predicates.add(builder.equal(root.get(Order._shop).get(Shop._name), filter.getShop()));
		}
		
		if (filter.getQuantity() != null) {			
			predicates.add(builder.equal(root.get(Order._quantity), filter.getQuantity()));
		}
		
		if (filter.getStatus() != null) {			
			predicates.add(builder.equal(root.get(Order._status), filter.getStatus()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Order._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Order> criteria = builder.createCriteriaUpdate(Order.class);
		Root<Order> root = criteria.from(Order.class);
		
		criteria.set(Order._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}
