package bg.infosys.interns.bmanagement.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDAOImpl;
import bg.infosys.interns.bmanagement.core.dto.OrderProductFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.core.entity.OrderProduct;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;



@Repository
public class OrderProductDAO extends GenericDAOImpl<OrderProduct, Integer> {

	public List<OrderProduct>getAll(){
	CriteriaBuilder builder = criteriaBuilder();
	CriteriaQuery<OrderProduct> criteria = builder.createQuery(OrderProduct.class);
	Root<OrderProduct> root = criteria.from(OrderProduct.class);
	
	root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
	root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
	root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);  
	root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
	root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
	root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
	
	criteria.select(root);
	
	
	return createQuery(criteria).getResultList();
	}
	
	public OrderProduct getById(Integer id){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<OrderProduct> criteria = builder.createQuery(OrderProduct.class);
		Root<OrderProduct> root = criteria.from(OrderProduct.class);
		 
		root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(_id), id));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<OrderProduct> findAllByFilter(OrderProductFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<OrderProduct> criteria = builder.createQuery(OrderProduct.class);
		Root<OrderProduct> root = criteria.from(OrderProduct.class);
		
		root.fetch(OrderProduct._order,JoinType.LEFT).fetch(Order._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(OrderProduct._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
	
		if (filter.getOrder() != null) {			
			predicates.add(builder.equal(root.get(OrderProduct._order).get(_id), filter.getOrder()));
		}
		
		if (filter.getProduct() != null) {			
			predicates.add(builder.equal(root.get(OrderProduct._product).get(_id), filter.getProduct()));
		}
		
		
		SortingUtil.<OrderProduct>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(OrderProductFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<OrderProduct> root = criteria.from(OrderProduct.class);
		
		root.join(OrderProduct._order,JoinType.LEFT).join(Order._product,JoinType.LEFT).join(Product._type,JoinType.LEFT);
		root.join(OrderProduct._order,JoinType.LEFT).join(Order._shop,JoinType.LEFT).join(Shop._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(OrderProduct._product,JoinType.LEFT).join(Product._manufacturer,JoinType.LEFT).join(Manufacturer._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(OrderProduct._product,JoinType.LEFT).join(Product._type,JoinType.LEFT);
		root.join(OrderProduct._product,JoinType.LEFT).join(Product._tags,JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getOrder() != null) {			
			predicates.add(builder.equal(root.get(OrderProduct._order).get(_id), filter.getOrder()));
		}
		
		if (filter.getProduct() != null) {			
			predicates.add(builder.equal(root.get(OrderProduct._product).get(_id), filter.getProduct()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
}
