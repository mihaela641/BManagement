package bg.infosys.interns.bmanagement.ws.dto.mapper;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Country;
import bg.infosys.interns.bmanagement.ws.dto.CountryDTO;

@Service
public class CountryMapper implements IModelMapper<CountryDTO, Country>{
	
	public CountryDTO toDto(Country entity) {
		if (entity == null) return null;
		
		return new CountryDTO (entity.getId(),entity.getCode(),entity.getName());
	} 
	
	public Country toEntity(CountryDTO dto) {
		if (dto == null) return null;
		
		return new Country (dto.getId(),dto.getCode(),dto.getName());
	}

}
