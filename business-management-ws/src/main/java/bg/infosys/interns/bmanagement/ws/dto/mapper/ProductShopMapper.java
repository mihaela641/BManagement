package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.ProductShop;
import bg.infosys.interns.bmanagement.ws.dto.ProductShopDTO;

@Service
public class ProductShopMapper implements IModelMapper<ProductShopDTO, ProductShop>{
	private ProductMapper productMapper;
	private ShopMapper shopMapper;

	public ProductShopMapper(ProductMapper productMapper, ShopMapper shopMapper) {
		this.productMapper = productMapper;
		this.shopMapper = shopMapper;
	}

	@Override
	public ProductShopDTO toDto(ProductShop entity) {
		if (entity == null) return null;
		return new ProductShopDTO(entity.getId(),productMapper.toDto(entity.getProduct())
				,shopMapper.toDto(entity.getShop())
				,entity.getQuantity(),entity.getIsDeleted());
	}
	public List<ProductShopDTO>toDtoList(List<ProductShop>productShopEntityList){
		if (productShopEntityList == null) return null;
		return productShopEntityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	@Override
	public ProductShop toEntity(ProductShopDTO dto) {
		if (dto == null) return null;
		return new ProductShop(dto.getId(),productMapper.toEntity(dto.getProduct())
				,shopMapper.toEntity(dto.getShop())
				,dto.getQuantity(),dto.getIsDeleted());
	}
	
	public List<ProductShop>toEntityList(List<ProductShopDTO>productShopDTOList){
		if (productShopDTOList == null) return null;
		return productShopDTOList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}

}
