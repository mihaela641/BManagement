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
import bg.infosys.interns.bmanagement.core.dto.ReportFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.entity.Report;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class ReportDAO extends GenericDAOImpl<Report, Integer> {
	
	public List<Report>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Report> criteria = builder.createQuery(Report.class);
		Root<Report> root = criteria.from(Report.class);
		
		root.fetch(Report._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Report._isDeleted), false));

		return createQuery(criteria).getResultList();
	}
	public Report getById(Integer id){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Report> criteria = builder.createQuery(Report.class);
		Root<Report> root = criteria.from(Report.class);
		
		root.fetch(Report._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		
		criteria.select(root);
		
		criteria.where(builder.equal(root.get(_id), id),builder.equal(root.get(Report._isDeleted), false));
	
		return createQuery(criteria).getSingleResult();
	}

	public List<Report> findAllByFilter(ReportFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Report> criteria = builder.createQuery(Report.class);
		Root<Report> root = criteria.from(Report.class);

		root.fetch(Report._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
		root.fetch(Report._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getShop() != null) {
			predicates.add(builder.equal(root.get(Report._shop).get(_id), filter.getShop()));
		}

		if (filter.getProduct() != null) {
			predicates.add(builder.equal(root.get(Report._product).get(_id), filter.getProduct()));
		}

		if (filter.getQuantity() != null) {
			predicates.add(builder.equal(root.get(Report._quantity), filter.getQuantity()));
		}

		if (filter.getDateFrom() != null) {
			predicates.add(builder.equal(root.get(Report._date), filter.getDateFrom()));
		}

		if (filter.getDateTo() != null) {
			predicates.add(builder.equal(root.get(Report._date), filter.getDateTo()));
		}

		SortingUtil.<Report>sort(criteria, builder, root.get(pagingSorting.getSortBy()),
				pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Report._isDeleted), false));

		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
				.setMaxResults(pagingSorting.getPageSize()).getResultList();
	}

	public long countAllByFilter(ReportFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Report> root = criteria.from(Report.class);

		root.join(Report._shop,JoinType.LEFT).join(Shop._address,JoinType.LEFT).join(Address._country,JoinType.LEFT);
		root.join(Report._product,JoinType.LEFT).join(Product._manufacturer,JoinType.LEFT);
		root.join(Report._product,JoinType.LEFT).join(Product._type,JoinType.LEFT);
		root.join(Report._product,JoinType.LEFT).join(Product._tags,JoinType.LEFT);
		
		criteria.select(builder.countDistinct(root));

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getShop() != null) {
			predicates.add(builder.equal(root.get(Report._shop).get(_id), filter.getShop()));
		}

		if (filter.getProduct() != null) {
			predicates.add(builder.equal(root.get(Report._product).get(_id), filter.getProduct()));
		}

		if (filter.getQuantity() != null) {
			predicates.add(builder.equal(root.get(Report._quantity), filter.getQuantity()));
		}

		if (filter.getDateFrom() != null) {
			predicates.add(builder.equal(root.get(Report._date), filter.getDateFrom()));
		}

		if (filter.getDateTo() != null) {
			predicates.add(builder.equal(root.get(Report._date), filter.getDateTo()));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Report._isDeleted), false));

		return createQuery(criteria).getSingleResult();
	}
	
		public List<Report>getDailyReport(){
			CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<Report> criteria = builder.createQuery(Report.class);
			Root<Report> root = criteria.from(Report.class);
			
			root.fetch(Report._shop,JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country,JoinType.LEFT);
			root.fetch(Report._product,JoinType.LEFT).fetch(Product._manufacturer,JoinType.LEFT);
			root.fetch(Report._product,JoinType.LEFT).fetch(Product._type,JoinType.LEFT);
			root.fetch(Report._product,JoinType.LEFT).fetch(Product._tags,JoinType.LEFT);
			
			criteria.select(root);
			criteria.orderBy(builder.asc(root.get(Report._shop)));
			
			return  createQuery(criteria).getResultList();
		}
		
		public boolean delete(Integer id) {
			CriteriaBuilder builder = criteriaBuilder();
			CriteriaUpdate<Report> criteria = builder.createCriteriaUpdate(Report.class);
			Root<Report> root = criteria.from(Report.class);
			
			criteria.set(Report._isDeleted, true);
			criteria.where(builder.equal(root.get(_id),id));
			
			if(createQuery(criteria).executeUpdate()==1) {
				return true;
			}
				return false;
		}
}
