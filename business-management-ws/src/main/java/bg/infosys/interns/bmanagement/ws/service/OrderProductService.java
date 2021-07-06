package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.OrderProductDAO;
import bg.infosys.interns.bmanagement.core.dto.OrderProductFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.OrderProduct;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.OrderProductDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.OrderProductMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class OrderProductService {
	
	private OrderProductDAO orderProductDAO;
	private OrderProductMapper orderProductMapper;
	
	public OrderProductService(OrderProductDAO orderProductDAO, OrderProductMapper orderProductMapper) {
		this.orderProductDAO = orderProductDAO;
		this.orderProductMapper = orderProductMapper;
	}
	
	public List<OrderProductDTO>findAll(){
		return orderProductDAO.getAll().stream().map(e->orderProductMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public OrderProductDTO findById(Integer id) {
		return orderProductMapper.toDto(orderProductDAO.getById(id));
	}
	
	public Page<OrderProductDTO> findAllByFilter(OrderProductFilterDTO filter, PagingSorting pagingSorting) {
		List<OrderProduct> results = orderProductDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<OrderProductDTO>(results.stream()
									     .map(p -> orderProductMapper.toDto(p))
									     .collect(Collectors.toList()), orderProductDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public OrderProductDTO create(OrderProductDTO orderProductDTO) {
		OrderProduct orderProduct = orderProductMapper.toEntity(orderProductDTO);
		orderProductDAO.save(orderProduct);
		
		return orderProductMapper.toDto(orderProduct);
	}
	
	@Transactional
	public OrderProductDTO update(Integer id, OrderProductDTO orderProduct) {
		OrderProduct updatedOrderProduct = orderProductDAO.findById(id);

		orderProduct.setId(id);
		updatedOrderProduct = orderProductDAO.merge(orderProductMapper.toEntity(orderProduct));
		return orderProductMapper.toDto(updatedOrderProduct);
	}
	
	@Transactional
	public void delete(Integer id) {
		orderProductDAO.deleteById(id);
	}

}
