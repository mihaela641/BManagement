package bg.infosys.interns.bmanagement.core.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDAOImpl;
import bg.infosys.interns.bmanagement.core.dto.PositionFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Position;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class PositionDAO extends GenericDAOImpl<Position, Integer> {
	
	public List<Position>getAll(){
		
	   CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
		Root<Position> root = criteria.from(Position.class);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Position._isDeleted), false));

		return createQuery(criteria).getResultList(); 
	}
		public Position getById(Integer id){
			
			CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
			Root<Position> root = criteria.from(Position.class);
			
			criteria.select(root);
			
			criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Position._isDeleted), false));
		
			return createQuery(criteria).getSingleResult();
		}
	
	public List<Position> findAllByFilter (PositionFilterDTO filter,PagingSorting pagingSorting) {
	    CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
		Root<Position> root = criteria.from(Position.class); 
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Position._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getCode() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Position._code)), "%" + filter.getCode().toLowerCase() + "%"));
		} 
		

	SortingUtil.<Position>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Position._isDeleted), false));
		
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
			.setMaxResults(pagingSorting.getPageSize())
			.getResultList();   
	}
	
	public long countAllByFilter(PositionFilterDTO filter) {
	   CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Position> root = criteria.from(Position.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Position._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getCode() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Position._code)), "%" + filter.getCode().toLowerCase() + "%"));
		}
		
		criteria.select(builder.count(root)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Position._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	} 
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Position> criteria = builder.createCriteriaUpdate(Position.class);
		Root<Position> root = criteria.from(Position.class);
		
		criteria.set(Position._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
 
}