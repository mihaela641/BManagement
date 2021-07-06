package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Address;
import bg.infosys.interns.bmanagement.ws.dto.AddressDTO;

@Service
public class AddressMapper implements IModelMapper<AddressDTO, Address> {
	
	private CountryMapper countryMapper;
	
	public AddressMapper (CountryMapper countryMapper) {
		this.countryMapper = countryMapper;
	}
		
	public AddressDTO toDto(Address entity) {
		if (entity == null) return null;
		
		return new AddressDTO(entity.getId(),entity.getStreet(),countryMapper.toDto(entity.getCountry()),entity.getIsDeleted());
	}
	
	public List<AddressDTO> toDtoList(List<Address>addresses){
		return addresses.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public Address toEntity(AddressDTO dto) {
		if (dto == null) return null;
		return new Address(dto.getId(),dto.getStreet(),countryMapper.toEntity(dto.getCountry()), dto.getIsDeleted());
	}
	
	public List<Address> toEntityList(List<AddressDTO>addresses){
		return addresses.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}
