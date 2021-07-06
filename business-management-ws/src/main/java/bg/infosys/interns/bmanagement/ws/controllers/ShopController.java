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

import bg.infosys.interns.bmanagement.core.dto.ShopFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.ShopDTO;
import bg.infosys.interns.bmanagement.ws.service.ShopService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Shops")
@RequestMapping(ControllerConfig.SHOP_URL)
public class ShopController {

	private ShopService shopService;
	
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Shops", notes = "This method will return all Shops")
	public List<ShopDTO>getShops(){
		return shopService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Shops", notes = "This method will return Shop by given id")
	public ShopDTO getShop(@PathVariable Integer id) {
		return shopService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Shops", notes = "This method will return page of Shops")
	public Page<ShopDTO> getProductsPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String address,
	        @RequestParam(required = false) String phoneNumber,
	        @RequestParam(required = false) Integer status,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
		    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
			@RequestParam(required = false) Integer manufacturer){
		
		return shopService.findAllByFilter(new ShopFilterDTO(name, address, phoneNumber, status),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Shop", notes = "This method will create and return new Shop")
	public ResponseEntity<ShopDTO> create(@RequestBody @Valid ShopDTO shopDTO) {
		return ResponseEntity.status(201).body(shopService.create(shopDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Shop", notes = "This method will update the Shop with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<ShopDTO> update(@PathVariable Integer id, @RequestBody @Valid ShopDTO shopDTO) {
		return ResponseEntity.status(200).body(shopService.update(id, shopDTO));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Shop", notes = "This method will delete Shop by id")
	public void delete(@PathVariable Integer id) {
		shopService.delete(id);
	}
}
