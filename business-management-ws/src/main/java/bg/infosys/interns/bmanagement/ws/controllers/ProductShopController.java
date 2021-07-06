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

import bg.infosys.interns.bmanagement.core.dto.ProductShopFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.ProductShopDTO;
import bg.infosys.interns.bmanagement.ws.service.ProductShopService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "ProductsShops")
@RequestMapping(ControllerConfig.PRODUCTSHOP_URL)
public class ProductShopController {
	private ProductShopService productShopService;
	
	public ProductShopController(ProductShopService productShopService) {
		this.productShopService = productShopService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all ProductShop", notes = "This method will return all ProductsShop")
	public List<ProductShopDTO>getProductShops(){
		return productShopService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one ProductShop", notes = "This method will return ProductsShop")
	public ProductShopDTO getProductShop(@PathVariable Integer id) {
		return productShopService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of ProductsShops", notes = "This method will return page of Products quantities in shops")
	public Page<ProductShopDTO> getProductsShopsPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String product,
	        @RequestParam(required = false) String shop,
	        @RequestParam(required = false) Double quantity){
		
		return productShopService.findAllByFilter(new ProductShopFilterDTO(product, shop, quantity),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Find all ProductShop", notes = "This method will return ProductsShop with given id")
	public ResponseEntity<ProductShopDTO> create(@RequestBody ProductShopDTO productShopDTO) {
		return ResponseEntity.status(201).body(productShopService.create(productShopDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update ProductShop", notes = "This method will update the ProductShop quantity with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<ProductShopDTO> update(@PathVariable Integer id, @RequestBody @Valid ProductShopDTO productShopDTO) {
		return ResponseEntity.status(200).body(productShopService.update(id, productShopDTO));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete ProductShops", notes = "This method will delete ProductsShops with given id")
	public boolean delete(@PathVariable Integer id) {
		return productShopService.delete(id);
	}
}
