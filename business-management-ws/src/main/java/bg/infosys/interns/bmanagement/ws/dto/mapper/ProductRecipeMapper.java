package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.ProductRecipe;
import bg.infosys.interns.bmanagement.ws.dto.ProductRecipeDTO;

@Service
public class ProductRecipeMapper  implements IModelMapper<ProductRecipeDTO, ProductRecipe>{
	
	private RecipeMapper recipeMapper;
	private ShopMapper shopMapper;
	private ProductMapper productMapper;
	
	
	public ProductRecipeMapper(RecipeMapper recipeMapper, ShopMapper shopMapper,ProductMapper productMapper) {
		this.recipeMapper = recipeMapper;
		this.shopMapper = shopMapper;
		this.productMapper = productMapper;
	}
	
	@Override
	public ProductRecipeDTO toDto(ProductRecipe entity) {
		if (entity == null) return null;
		return new ProductRecipeDTO(entity.getId(),recipeMapper.toDto(entity.getRecipe())
				,shopMapper.toDto(entity.getShop()),entity.getIsDeleted(),entity.getProductQuantity()
				,productMapper.toDto(entity.getProduct()));
	}
	
	public List<ProductRecipeDTO>toDtoList(List<ProductRecipe>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public ProductRecipe toEntity(ProductRecipeDTO dto) {
		if (dto == null) return null;
		return new ProductRecipe(dto.getId(),shopMapper.toEntity(dto.getShop()),recipeMapper.toEntity(dto.getRecipe())
				,dto.getIsDeleted(),dto.getProductQuantity(),productMapper.toEntity(dto.getProduct()));

	}
	
	public List<ProductRecipe>toEntityList(List<ProductRecipeDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
	
	
	
}
