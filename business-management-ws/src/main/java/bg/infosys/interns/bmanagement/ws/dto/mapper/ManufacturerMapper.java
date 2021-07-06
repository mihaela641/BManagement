package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.ws.dto.ManufacturerDTO;

@Service
public class ManufacturerMapper {
		
		private final AddressMapper addressMapper;
			
		public ManufacturerMapper(AddressMapper addressMapper) {
			this.addressMapper = addressMapper;
		}
		
		public ManufacturerDTO toDto(Manufacturer entity) {
			if (entity == null) return null;
			
			return new ManufacturerDTO(entity.getId(), entity.getName(), entity.getFoundationDate()
					,entity.getDescription(), addressMapper.toDto(entity.getAddress()),entity.getIsDeleted());
		} 
		
		public Manufacturer toEntity(ManufacturerDTO dto) {
			if (dto == null) return null;
			
			return new Manufacturer(dto.getId(), dto.getName(), dto.getFoundationDate()
					,dto.getDescription(), addressMapper.toEntity(dto.getAddress()),dto.getIsDeleted());
		} 
		
		public List<ManufacturerDTO>toDtoList(List<Manufacturer>entityList){
			if (entityList == null) return null;
			return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
		}

		public List<Manufacturer>toEntityList(List<ManufacturerDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}
