package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.ws.dto.ProductDTO;

@Service
public class ProductMapper implements IModelMapper<ProductDTO, Product>{
	
	private TypeMapper typeMapper;
	private ManufacturerMapper manufacturerMapper;
	private TagMapper tagMapper;
	
	public ProductMapper(TypeMapper typeMapper,ManufacturerMapper manufacturerMapper,TagMapper tagMapper) {
		this.typeMapper = typeMapper;
		this.manufacturerMapper = manufacturerMapper;
		this.tagMapper = tagMapper;
	}

	@Override
	public ProductDTO toDto(Product entity) {
		if (entity == null) return null;
		return new ProductDTO(entity.getId(),entity.getName()
				,typeMapper.toDto(entity.getType()),entity.getPrice()
				,entity.getDateAdded(),manufacturerMapper.toDto(entity.getManufacturer()),
				tagMapper.toDtoList(entity.getTags()), entity.getIsDeleted());
	}
	
	public List<ProductDTO>toDtoList(List<Product>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	@Override
	public Product toEntity(ProductDTO dto) {
		if (dto == null) return null;
		return new Product(dto.getId(),dto.getName()
				,typeMapper.toEntity(dto.getType()),dto.getPrice()
				,dto.getDateAdded()
				,manufacturerMapper.toEntity(dto.getManufacturer())
				,tagMapper.toEntityList(dto.getTags()),dto.getIsDeleted());
	}
	
	public List<Product>toEntityList(List<ProductDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}
