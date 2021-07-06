package bg.infosys.interns.bmanagement.ws.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
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

import bg.infosys.interns.bmanagement.core.dto.ProductFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.ProductDTO;
import bg.infosys.interns.bmanagement.ws.service.ProductService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Products")
@RequestMapping(ControllerConfig.PRODUCT_URL)
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Products", notes = "This method will return all Products")
	public List<ProductDTO>getAll(){
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Product", notes = "This method will get the Product with given id")
	public ProductDTO getProduct(@PathVariable(value = "id") Integer id){
		return productService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Products", notes = "This method will return page of Products")
	public Page<ProductDTO> getProductsPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) Integer type,
	        @RequestParam(required = false) Double price,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
		    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
			@RequestParam(required = false) Integer manufacturer){
		
		return productService.findAllByFilter(new ProductFilterDTO(name, type, price, dateFrom, dateTo, manufacturer),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Product", notes = "This method will create new Product")
	public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
		
		return ResponseEntity.status(201).body(productService.save(productDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Product", notes = "This method will update the Product with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody @Valid ProductDTO product) {
		ProductDTO updatedPlayer = productService.update(id, product);
		
		return ResponseEntity.status(200).body(updatedPlayer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Product", notes = "This method will delete the Product matching the passed id")
	public void delete(@PathVariable Integer id) {
		productService.deleteById(id);
	}
}