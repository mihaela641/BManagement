package bg.infosys.interns.bmanagement.core.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDAOImpl;
import bg.infosys.interns.bmanagement.core.entity.Country;

@Repository
public class CountryDAO extends GenericDAOImpl<Country, Integer>{

	
	public List<Country>getAll(){
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Country> query = builder.createQuery(Country.class);
		Root<Country>root = query.from(Country.class); 
		
		query.select(root);
		
		return createQuery(query).getResultList();
	}
	

}