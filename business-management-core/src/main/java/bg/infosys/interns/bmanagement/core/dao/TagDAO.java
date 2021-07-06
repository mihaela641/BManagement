package bg.infosys.interns.bmanagement.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDAOImpl;
import bg.infosys.interns.bmanagement.core.dto.TagFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Tag;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class TagDAO extends GenericDAOImpl<Tag, Integer>{
	
	public List<Tag> getAll() {
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
		Root<Tag> root = criteria.from(Tag.class);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Tag._isDeleted), false));

		return createQuery(criteria).getResultList();
	}
	
	public Tag getById(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
		Root<Tag> root = criteria.from(Tag.class);
		
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Tag._isDeleted), false));
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Tag> findAllByFilter(TagFilterDTO filter,PagingSorting pagingSorting) {
	    CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
		Root<Tag> root = criteria.from(Tag.class); 
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Tag._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getCode() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Tag._code)), "%" + filter.getCode().toLowerCase() + "%"));
		} 
		

	SortingUtil.<Tag>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Tag._isDeleted), false));
		
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
			.setMaxResults(pagingSorting.getPageSize())
			.getResultList(); 
	}
	
	public long countAllByFilter (TagFilterDTO filter) {
		   CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
			Root<Tag> root = criteria.from(Tag.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if (filter.getName() != null) {			
				predicates.add(builder.like(builder.lower(root.get(Tag._name)), "%" + filter.getName().toLowerCase() + "%"));
			}
			
			if (filter.getCode() != null) {			
				predicates.add(builder.like(builder.lower(root.get(Tag._code)), "%" + filter.getCode().toLowerCase() + "%"));
			}
			
			criteria.select(builder.count(root)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Tag._isDeleted), false));
			
			return createQuery(criteria).getSingleResult();
	}
	
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Tag> criteria = builder.createCriteriaUpdate(Tag.class);
		Root<Tag> root = criteria.from(Tag.class);
		
		criteria.set(Tag._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}

}
