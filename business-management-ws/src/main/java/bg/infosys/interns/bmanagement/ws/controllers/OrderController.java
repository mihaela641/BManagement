package bg.infosys.interns.bmanagement.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.interns.bmanagement.core.dto.OrderFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.OrderDTO;
import bg.infosys.interns.bmanagement.ws.service.OrderService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Orders")
@RequestMapping(ControllerConfig.ORDER_URL)
public class OrderController {
	
private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Orders", notes = "This method will return all Orders")
	public List<OrderDTO>getAll(){
		return orderService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Order", notes = "This method will get the Order with given id")
	public OrderDTO getOrder(@PathVariable(value = "id") Integer id){
		return orderService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Orders", notes = "This method will return page of Orders")
	public Page<OrderDTO> getOrdersPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String code,
	        @RequestParam(required = false) String product,
	        @RequestParam(required = false) String shop,
	        @RequestParam(required = false) Integer quantity,
	        @RequestParam(required = false) Integer status){
		
		return orderService.findAllByFilter(new OrderFilterDTO(code ,product, shop,quantity,status),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Order", notes = "This method will create new Order")
	public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO orderDTO) {
		
		return ResponseEntity.status(201).body(orderService.save(orderDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Order", notes = "This method will update the Order with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<OrderDTO> update(@PathVariable Integer id, @RequestBody @Valid OrderDTO order) {
		OrderDTO updatedOrder = orderService.update(id, order);
		
		return ResponseEntity.status(200).body(updatedOrder);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Order", notes = "This method will delete the Order matching the passed id")
	public void delete(@PathVariable Integer id) {
		orderService.deleteById(id);
	}

}
