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
import bg.infosys.interns.bmanagement.core.dto.TypeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class TypeDAO extends GenericDAOImpl<Type, Integer> {

	public List<Type> getAll() {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Type> criteria = builder.createQuery(Type.class);
		Root<Type> root = criteria.from(Type.class);

		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Type._isDeleted), false));

		return createQuery(criteria).getResultList();
	}

	public Type getById(Integer id) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Type> criteria = builder.createQuery(Type.class);
		Root<Type> root = criteria.from(Type.class);

		root.fetch("parentType", JoinType.LEFT);

		criteria.select(root);

		criteria.where(builder.equal(root.get("id"), id),builder.equal(root.get(Type._isDeleted), false));
		return createQuery(criteria).getSingleResult();

	}

	public List<Type> findAllByFilter(TypeFilterDTO filter, PagingSorting pagingSorting) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Type> criteria = builder.createQuery(Type.class);
		Root<Type> root = criteria.from(Type.class);

		root.fetch("parentType", JoinType.LEFT);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getName() != null) {
			predicates
					.add(builder.like(builder.lower(root.get(Type._name)), "%" + filter.getName().toLowerCase() + "%"));
		}

		if (filter.getCode() != null) {
			predicates
					.add(builder.like(builder.lower(root.get(Type._code)), "%" + filter.getCode().toLowerCase() + "%"));
		}

		if (filter.getParentType() != null) {
			predicates.add(builder.equal(root.get(Type._parentType).get("id"), filter.getParentType()));
		}

		SortingUtil.<Type>sort(criteria, builder, root.get(pagingSorting.getSortBy()),
				pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Type._isDeleted), false));

		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
				.setMaxResults(pagingSorting.getPageSize()).getResultList();
	}

	public long countAllByFilter(TypeFilterDTO filter) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Type> root = criteria.from(Type.class);

		criteria.select(builder.countDistinct(root));

		List<Predicate> predicates = new ArrayList<Predicate>();

		root.join("parentType", JoinType.LEFT);

		if (filter.getName() != null) {
			predicates
					.add(builder.like(builder.lower(root.get(Type._name)), "%" + filter.getName().toLowerCase() + "%"));
		}

		if (filter.getCode() != null) {
			predicates
					.add(builder.like(builder.lower(root.get(Type._code)), "%" + filter.getCode().toLowerCase() + "%"));
		}

		if (filter.getParentType() != null) {
			predicates.add(builder.equal(root.get(Type._parentType).get("id"), filter.getParentType()));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Type._isDeleted), false));

		return createQuery(criteria).getSingleResult();

	}

	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Type> criteria = builder.createCriteriaUpdate(Type.class);
		Root<Type> root = criteria.from(Type.class);

		criteria.set(Type._isDeleted, true);
		criteria.where(builder.equal(root.get(_id), id));

		if (createQuery(criteria).executeUpdate() == 1) {
			return true;
		}
		return false;
	}

}