package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.dao.CountryDAO;
import bg.infosys.interns.bmanagement.ws.dto.CountryDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.CountryMapper;

@Service
public class EnumService {
	
	private CountryDAO countryDao;
	private CountryMapper countryMapper;
	
	public EnumService(CountryDAO dao,CountryMapper mapper) {
		this.countryDao = dao;
		this.countryMapper = mapper;
	}
	
	public List<CountryDTO> getAllCountries(){
		return countryDao.getAll().stream().map(e->countryMapper.toDto(e)).collect(Collectors.toList());
	}
}
