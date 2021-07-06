package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Recipes;

import bg.infosys.interns.bmanagement.ws.dto.RecipeDTO;

@Service
public class RecipeMapper implements IModelMapper<RecipeDTO, Recipes> {
	
	private ProductMapper productMapper;
	
	public RecipeMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public RecipeDTO toDto(Recipes entity) {
		if (entity == null) return null;
		return new RecipeDTO(entity.getId(),entity.getDescription()
			,entity.getName(),entity.getIsDeleted());
	}
	
	public List<RecipeDTO>toDtoList(List<Recipes>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public Recipes toEntity(RecipeDTO dto) {
		if (dto == null) return null;
		return new Recipes(dto.getId(),dto.getDescription()
				,dto.getName(),dto.getIsDeleted());

	}
	
	public List<Recipes>toEntityList(List<RecipeDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}

}
