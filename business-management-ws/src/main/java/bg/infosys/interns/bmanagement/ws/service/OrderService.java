package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.OrderDAO;
import bg.infosys.interns.bmanagement.core.dto.OrderFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Order;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.OrderDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.OrderMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class OrderService {

	private OrderDAO orderDAO;
	private OrderMapper orderMapper;
	
	public OrderService(OrderDAO orderDAO, OrderMapper orderMapper) {
		this.orderDAO = orderDAO;
		this.orderMapper = orderMapper;
	}
	
	public List<OrderDTO>findAll(){
		return orderDAO.getAll().stream().map(e->orderMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public OrderDTO findById(Integer id) {
		return orderMapper.toDto(orderDAO.getById(id));
	}
	
	public Page<OrderDTO> findAllByFilter(OrderFilterDTO filter, PagingSorting pagingSorting) {
		List<Order> results = orderDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<OrderDTO>(results.stream()
						.map(p -> orderMapper.toDto(p))
						 .collect(Collectors.toList()), orderDAO.countAllByFilter(filter)
						 ,pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public OrderDTO save(OrderDTO orderDTO) {
		Order newOrder = orderMapper.toEntity(orderDTO);
		orderDAO.save(newOrder);
		
		return orderMapper.toDto(newOrder);
	}
	
	@Transactional
	public OrderDTO update(Integer id, OrderDTO order ) {
		Order updatedOrder = orderDAO.findById(id);

		order.setId(id);
		updatedOrder = orderDAO.merge(orderMapper.toEntity(order));
		return orderMapper.toDto(updatedOrder);
	}
	
	@Transactional
	public boolean deleteById(Integer orderId) {
		return orderDAO.delete(orderId);
	}
	
}
