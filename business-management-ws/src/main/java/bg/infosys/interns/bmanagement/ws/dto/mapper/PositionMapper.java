package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Position;
import bg.infosys.interns.bmanagement.ws.dto.PositionDTO;

@Service
public class PositionMapper implements IModelMapper <PositionDTO, Position>{
	
	public PositionDTO toDto(Position entity) {
		if (entity == null) return null;
		
		return new PositionDTO(entity.getId(),entity.getCode(),entity.getName(),entity.getDescription(), entity.getIsDeleted());
	}
	
	
	public Position toEntity(PositionDTO dto) {
		if (dto == null) return null;
		
		return new Position(dto.getId(),dto.getCode(),dto.getName(), dto.getDescription(), dto.getIsDeleted());
	}
	
	public List<PositionDTO>toDtoList(List<Position>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
}
