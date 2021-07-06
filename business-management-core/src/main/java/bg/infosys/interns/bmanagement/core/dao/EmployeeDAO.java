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
import bg.infosys.interns.bmanagement.core.dto.EmployeeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.core.entity.Employee;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.core.util.SortingUtil;

@Repository
public class EmployeeDAO extends GenericDAOImpl<Employee, Integer>{

	public List<Employee>getAll(){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		root.fetch(Employee._position, JoinType.LEFT);
		root.fetch(Employee._country, JoinType.LEFT);
		root.fetch(Employee._shop, JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Employee._isDeleted), false));
		
		return createQuery(criteria).getResultList();
	}
	
	public Employee getById(Integer id){
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		root.fetch(Employee._position, JoinType.LEFT);
		root.fetch(Employee._country, JoinType.LEFT);
		root.fetch(Employee._shop, JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get("id"), id),builder.equal(root.get(Employee._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}

	public List<Employee> findAllByFilter(EmployeeFilterDTO filter, PagingSorting pagingSorting) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		root.fetch(Employee._position, JoinType.LEFT);
		root.fetch(Employee._country, JoinType.LEFT);
		root.fetch(Employee._shop, JoinType.LEFT).fetch(Shop._address,JoinType.LEFT).fetch(Address._country, JoinType.LEFT);
				
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Employee._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getSalary() != null) {			
			predicates.add(builder.equal(root.get(Employee._salary), filter.getSalary()));
		}
		
		if (filter.getDateFrom() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Employee._birthDate).as(LocalDate.class), filter.getDateFrom()));
		}
		
		if (filter.getDateTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Employee._birthDate).as(LocalDate.class), filter.getDateTo()));
		}
		
		if (filter.getCountry() != null) {
			predicates.add(builder.equal(root.get(Employee._country).get(_id), filter.getCountry()));
		}
		
		if (filter.getPosition() != null) {
			predicates.add(builder.equal(root.get(Employee._position).get(_id), filter.getPosition()));
		}
		
		if (filter.getShop() != null) {
			predicates.add(builder.like(builder.lower(root.get(Employee._shop).get(Shop._name)), "%" + filter.getShop().toLowerCase() + "%"));
		}
		
		
		SortingUtil.<Employee>sort(criteria, builder, root.get(pagingSorting.getSortBy()), pagingSorting.getSortDirection());
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Employee._isDeleted), false));
		
		return createQuery(criteria).setFirstResult(pagingSorting.getPageSize() * pagingSorting.getPageNumber())
									.setMaxResults(pagingSorting.getPageSize())
									.getResultList();
	}
	
	public long countAllByFilter(EmployeeFilterDTO filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		root.join(Employee._position, JoinType.LEFT);
		root.join(Employee._country, JoinType.LEFT);
		root.join(Employee._shop, JoinType.LEFT);
	
		criteria.select(builder.countDistinct(root));
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getName() != null) {			
			predicates.add(builder.like(builder.lower(root.get(Employee._name)), "%" + filter.getName().toLowerCase() + "%"));
		}
		
		if (filter.getSalary() != null) {			
			predicates.add(builder.equal(root.get(Employee._salary), filter.getSalary()));
		}
		
		if (filter.getDateFrom() != null) {			
			predicates.add(builder.greaterThanOrEqualTo(root.get(Employee._birthDate).as(LocalDate.class), filter.getDateFrom()));
		}
		
		if (filter.getDateTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Employee._birthDate).as(LocalDate.class), filter.getDateTo()));
		}
		
		if (filter.getCountry() != null) {
			predicates.add(builder.equal(root.get(Employee._country).get(_id), filter.getCountry()));
		}
		
		if (filter.getPosition() != null) {
			predicates.add(builder.equal(root.get(Employee._position).get(_id), filter.getPosition()));
		}
		
		if (filter.getShop() != null) {
			predicates.add(builder.like(builder.lower(root.get(Employee._shop).get(Shop._name)), "%" + filter.getShop().toLowerCase() + "%"));
		}
		
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.equal(root.get(Employee._isDeleted), false));
		
		return createQuery(criteria).getSingleResult();
	}
	public boolean delete(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<Employee> criteria = builder.createCriteriaUpdate(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.set(Employee._isDeleted, true);
		criteria.where(builder.equal(root.get(_id),id));
		
		if(createQuery(criteria).executeUpdate()==1) {
			return true;
		}
			return false;
	}
}
