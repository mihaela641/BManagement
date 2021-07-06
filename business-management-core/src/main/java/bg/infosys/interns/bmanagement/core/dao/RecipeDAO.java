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
import bg.infosys.interns.bmanagement.core.dto.RecipesFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.Recipes;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class RecipeDAO extends GenericDAOImpl<Recipes, Integer> {

	public List<Recipes>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Recipes> criteria = builder.createQuery(Recipes.class);
		Root<Recipes> root = criteria.from(Recipes.class);
		
		
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Order._isDeleted), false));

		return createQuery(criteria).getResultList();
		
	}
	
public Recipes getById(Integer id){
		
	CriteriaBuilder builder = criteriaBuilder();
	CriteriaQuery<Recipes> criteria = builder.createQuery(Recipes.class);
	Root<Recipes> root = criteria.from(Recipes.class);
		
	
		criteria.select(root);
		
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Order._isDeleted), false));
	
		return createQuery(criteria).getSingleResult();
	}

public List<Recipes> findAllByFilter(RecipesFilterDTO filter, PagingSorting pagingSorting) {
	CriteriaBuilder builder = criteriaBuilder();
	CriteriaQuery<Recipes> criteria = builder.createQuery(Recipes.class);
	Root<Recipes> root = criteria.from(Recipes.class);
	

	List<Predicate> predicates = new ArrayList<Predicate>();
	
	if (filter.getName() != null) {			
		predicates.add(builder.like(builder.lower(root.get(Recipes._name)), "%" + filter.getName().toLowerCase() + "%"));
	}
	
	SortingUtil.<Recipes>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
	criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Recipes._isDeleted), false));
	
	return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
								.setMaxResults(pagingSorting.getPageSize())
								.getResultList();
}

public long countAllByFilter(RecipesFilterDTO filter) {
	CriteriaBuilder builder = criteriaBuilder();
	CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	Root<Recipes> root = criteria.from(Recipes.class);
	

	
	criteria.select(builder.countDistinct(root));
	
	List<Predicate> predicates = new ArrayList<Predicate>();
	
	
	if (filter.getName() != null) {			
		predicates.add(builder.like(builder.lower(root.get(Recipes._name)), "%" + filter.getName().toLowerCase() + "%"));
	}
	
	criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Recipes._isDeleted), false));
	
	return createQuery(criteria).getSingleResult();
}

public boolean delete(Integer id) {
	CriteriaBuilder builder = criteriaBuilder();
	CriteriaUpdate<Recipes> criteria = builder.createCriteriaUpdate(Recipes.class);
	Root<Recipes> root = criteria.from(Recipes.class);
	
	criteria.set(Recipes._isDeleted, true);
	criteria.where(builder.equal(root.get(_id),id));
	
	if(createQuery(criteria).executeUpdate()==1) {
		return true;
	}
		return false;
}

}