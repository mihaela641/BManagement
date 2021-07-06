package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.OrderProduct;
import bg.infosys.interns.bmanagement.ws.dto.OrderProductDTO;

@Service
public class OrderProductMapper implements IModelMapper<OrderProductDTO, OrderProduct>{

	private OrderMapper orderMapper;
	private ProductMapper productMapper;
	
	public OrderProductMapper(OrderMapper orderMapper, ProductMapper productMapper) {
		this.orderMapper = orderMapper;
		this.productMapper = productMapper;
	}
	
	@Override
	public OrderProductDTO toDto(OrderProduct entity) {
		if (entity == null) return null;
		return new OrderProductDTO(entity.getId(),orderMapper.toDto(entity.getOrder())
				,productMapper.toDto(entity.getProduct()));
	}
	
	public List<OrderProductDTO>toDtoList(List<OrderProduct>orderProductEntityList){
		if (orderProductEntityList == null) return null;
		return orderProductEntityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
	
	@Override
	public OrderProduct toEntity(OrderProductDTO dto) {
		if (dto == null) return null;
		return new OrderProduct(dto.getId(),orderMapper.toEntity(dto.getOrder())
				,productMapper.toEntity(dto.getProduct()));
	}
	
	public List<OrderProduct>toEntityList(List<OrderProductDTO>orderProductDTOList){
		if (orderProductDTOList == null) return null;
		return orderProductDTOList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
	
	
}
