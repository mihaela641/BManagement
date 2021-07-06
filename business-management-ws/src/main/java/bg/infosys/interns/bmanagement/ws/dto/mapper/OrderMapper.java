package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.ws.dto.OrderDTO;

@Service
public class OrderMapper implements IModelMapper<OrderDTO, Order>{

	private ProductMapper productMapper;
	private ShopMapper shopMapper;
	
	public OrderMapper(ProductMapper productMapper,ShopMapper shopMapper) {
		this.productMapper = productMapper;
		this.shopMapper = shopMapper;
	}
	
	@Override
	public OrderDTO toDto(Order entity) {
		if (entity == null) return null;
		return new OrderDTO(entity.getId(),entity.getCode()
				,productMapper.toDto(entity.getProduct()),shopMapper.toDto(entity.getShop()),entity.getQuantity(),entity.getStatus()
				,entity.getIsDeleted());
	}
	
	public List<OrderDTO>toDtoList(List<Order>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	@Override
	public Order toEntity(OrderDTO dto) {
		if (dto == null) return null;
		return new Order(dto.getId(),dto.getCode(),
				productMapper.toEntity(dto.getProduct()),shopMapper.toEntity(dto.getShop()),dto.getQuantity(),
				dto.getStatus(),dto.getIsDeleted());

	}
	
	public List<Order>toEntityList(List<OrderDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
	
	
}
