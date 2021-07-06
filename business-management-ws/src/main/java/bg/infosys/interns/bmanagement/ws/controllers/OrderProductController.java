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

import bg.infosys.interns.bmanagement.core.dto.OrderProductFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.OrderProductDTO;
import bg.infosys.interns.bmanagement.ws.service.OrderProductService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "OrdersProducts")
@RequestMapping(ControllerConfig.ORDERPRODUCT_URL)
public class OrderProductController {
	private OrderProductService orderProductService;
	
	public OrderProductController(OrderProductService orderProductService) {
		this.orderProductService = orderProductService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all OrdersProduct", notes = "This method will return all OrdersProduct")
	public List<OrderProductDTO>getOrderProduct(){
		return orderProductService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one OrdersProduct", notes = "This method will return OrdersProduct")
	public OrderProductDTO getOrderProduct(@PathVariable Integer id) {
		return orderProductService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of OrdersProduct", notes = "This method will return page of OrdersProduct , product")
	public Page<OrderProductDTO> getOrderProductPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) Integer order,
	        @RequestParam(required = false) Integer product){
		
		return orderProductService.findAllByFilter(new OrderProductFilterDTO(order, product),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Find all OrdersProduct", notes = "This method will return OrdersProduct with given id")
	public ResponseEntity<OrderProductDTO> create(@RequestBody OrderProductDTO orderProductDTO) {
		return ResponseEntity.status(201).body(orderProductService.create(orderProductDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update OrdersProduct", notes = "This method will update the OrdersProduct order with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<OrderProductDTO> update(@PathVariable Integer id, @RequestBody @Valid OrderProductDTO orderProductDTO) {
		return ResponseEntity.status(200).body(orderProductService.update(id, orderProductDTO));
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete OrdersProduct", notes = "This method will delete OrdersProduct with given id")
	public void delete(Integer id) {
		orderProductService.delete(id);
	}
}
