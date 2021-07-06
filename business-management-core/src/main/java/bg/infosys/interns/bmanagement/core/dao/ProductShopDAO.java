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
import bg.infosys.interns.bmanagement.core.dto.ProductShopFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.ProductShop;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ProductShopDAO extends GenericDAOImpl<ProductShop, Integer>{
	public List<ProductShop>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductShop> criteria = builder.createQuery(ProductShop.class);
		Root<ProductShop> root = criteria.from(ProductShop.class);
		 
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._type, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._tags, JoinType.LEFT);
		root.fetch(ProductShop._shop, JoinType.LEFT).fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(ProductShop._isDeleted), false));
		
		
		return createQuery(criteria).getResultList();
	}
	
	public ProductShop getById(Integer id){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductShop> criteria = builder.createQuery(ProductShop.class);
		Root<ProductShop> root = criteria.from(ProductShop.class);
		 
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._type, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._tags, JoinType.LEFT);
		root.fetch(ProductShop._shop, JoinType.LEFT).fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(ProductShop._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<ProductShop> findAllByFilter(ProductShopFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductShop> criteria = builder.createQuery(ProductShop.class);
		Root<ProductShop> root = criteria.from(ProductShop.class);
		
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._type, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._manufacturer, JoinType.LEFT).fetch(Manufacturer._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		root.fetch(ProductShop._product, JoinType.LEFT).fetch(Product._tags, JoinType.LEFT);
		root.fetch(ProductShop._shop, JoinType.LEFT).fetch(Shop._address, JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
	
		if (filter.getProduct() != null) {			
			predicates.add(builder.like(builder.lower(root.get(ProductShop._product).get(Product._name)), "%" + filter.getProduct().toLowerCase() + "%"));
		}
		
		if (filter.getShop() != null) {			
			predicates.add(builder.like(builder.lower(root.get(ProductShop._shop).get(Shop._name)), "%" + filter.getShop().toLowerCase() + "%"));
		}
		
		if (filter.getQuantity() != null) {			
			predicates.add(builder.equal(root.get(ProductShop._quantity), filter.getQuantity()));
		}
		
		SortingUtil.<ProductShop>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(ProductShop._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(ProductShopFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProductShop> root = criteria.from(ProductShop.class);
		
		root.join(ProductShop._product, JoinType.LEFT).join(Product._type, JoinType.LEFT);
		root.join(ProductShop._product, JoinType.LEFT).join(Product._manufacturer, JoinType.LEFT).join(Manufacturer._address, JoinType.LEFT).join(Address._country, JoinType.LEFT);
		root.join(ProductShop._product, JoinType.LEFT).join(Product._tags, JoinType.LEFT);
		root.join(ProductShop._shop, JoinType.LEFT).join(Shop._address, JoinType.LEFT).join(Address._country, JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getProduct() != null) {			
			predicates.add(builder.like(builder.lower(root.get(ProductShop._product).get(Product._name)), "%" + filter.getProduct().toLowerCase() + "%"));
		}
		
		if (filter.getShop() != null) {			
			predicates.add(builder.like(builder.lower(root.get(ProductShop._shop).get(Shop._name)), "%" + filter.getShop().toLowerCase() + "%"));
		}
		
		if (filter.getQuantity() != null) {			
			predicates.add(builder.equal(root.get(ProductShop._quantity), filter.getQuantity()));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(ProductShop._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<ProductShop> criteria = builder.createCriteriaUpdate(ProductShop.class);
		Root<ProductShop> root = criteria.from(ProductShop.class);
		
		criteria.set(ProductShop._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}
