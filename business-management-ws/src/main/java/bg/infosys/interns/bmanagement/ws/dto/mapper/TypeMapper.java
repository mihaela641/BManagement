package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.ws.dto.TypeDTO;

@Service
public class TypeMapper implements IModelMapper<TypeDTO, Type> {
	
	

	public TypeDTO toDto(Type entity) {
		if (entity == null) return null;
		
		return new TypeDTO(entity.getId(),entity.getCode() ,entity.getName(), entity.getDescription(), entity.getParentType(),entity.getIsDeleted());
	}
	public List<TypeDTO> entityToDto(List<Type>types){
		if (types == null) return null;
		return types.stream().map(e-> toDto(e)).collect(Collectors.toList());
	}
	
	
	
	public Type toEntity(TypeDTO dto) {
		if (dto == null) return null;
		
		return new Type(dto.getId(),dto.getCode() ,dto.getName(), dto.getDescription(), dto.getParentType(),dto.getIsDeleted());
	}
	public List<Type> dtoToEntity(List<TypeDTO>types){
		return types.stream().map(e-> toEntity(e)).collect(Collectors.toList());
	}

	

}
