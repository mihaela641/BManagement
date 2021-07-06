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
import bg.infosys.interns.bmanagement.core.dto.ProductRecipeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.ProductRecipe;
import bg.infosys.interns.bmanagement.core.entity.Recipes;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ProductRecipeDAO extends GenericDAOImpl<ProductRecipe, Integer>{

	public List<ProductRecipe>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductRecipe> criteria = builder.createQuery(ProductRecipe.class);
		Root<ProductRecipe> root = criteria.from(ProductRecipe.class);
		
		root.fetch(ProductRecipe._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._recipe,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Order._isDeleted), false));

		return createQuery(criteria).getResultList();
	}
	
	public ProductRecipe getById(Integer id){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductRecipe> criteria = builder.createQuery(ProductRecipe.class);
		Root<ProductRecipe> root = criteria.from(ProductRecipe.class);
			
		root.fetch(ProductRecipe._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._recipe,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
	
		
			criteria.select(root);
			
			criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(ProductRecipe._isDeleted), false));
		
			return createQuery(criteria).getSingleResult();
		}
	
	public List<ProductRecipe> findAllByFilter(ProductRecipeFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProductRecipe> criteria = builder.createQuery(ProductRecipe.class);
		Root<ProductRecipe> root = criteria.from(ProductRecipe.class);
		
		
		root.fetch(ProductRecipe._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._recipe,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT).fetch(Manufacturer._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(ProductRecipe._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT).fetch(Type._parentType,JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getRecipe() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._recipe).get(Recipes._name), filter.getRecipe()));
		} 
		
		
		if (filter.getShop() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._shop).get(Shop._name), filter.getShop()));
		} 
		
		if (filter.getProductQuantity() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._productQuantity), filter.getProductQuantity()));
		} 
		
		SortingUtil.<ProductRecipe>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(ProductRecipe._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(ProductRecipeFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProductRecipe> root = criteria.from(ProductRecipe.class);
		
		root.join(ProductRecipe._shop,JoinType.LEFT).join(Shop._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(ProductRecipe._recipe,JoinType.LEFT);
		root.join(ProductRecipe._product,JoinType.LEFT).join(Product._tags,JoinType.LEFT);
		root.join(ProductRecipe._product,JoinType.LEFT).join(Product._manufacturer,JoinType.LEFT).join(Manufacturer._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(ProductRecipe._product,JoinType.LEFT).join(Product._type,JoinType.LEFT).join(Type._parentType,JoinType.LEFT);
		
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getRecipe() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._recipe).get(Recipes._name), filter.getRecipe()));
		} 
		
		
		if (filter.getShop() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._shop).get(Shop._name), filter.getShop()));
		} 
		
		if (filter.getProductQuantity() != null) {			
			predicates.add(builder.equal(root.get(ProductRecipe._productQuantity), filter.getProductQuantity()));
		} 
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(ProductRecipe._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<ProductRecipe> criteria = builder.createCriteriaUpdate(ProductRecipe.class);
		Root<ProductRecipe> root = criteria.from(ProductRecipe.class);
		
		criteria.set(ProductRecipe._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}
