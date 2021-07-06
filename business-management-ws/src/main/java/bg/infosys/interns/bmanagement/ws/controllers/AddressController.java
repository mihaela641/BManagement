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

import bg.infosys.interns.bmanagement.core.dto.AddressFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.AddressDTO;
import bg.infosys.interns.bmanagement.ws.service.AddressService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Address")
@RequestMapping(ControllerConfig.ADDRESS_URL)
public class AddressController {
	private AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Address", notes = "This method will return all Address")
	public List<AddressDTO> getAddresses() {
		return addressService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Address", notes = "This method will return Address by given id")
	public AddressDTO getAddress(@PathVariable Integer id) {
		return addressService.findById(id);
	}

	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Address", notes = "This method will return page of Address")
	public Page<AddressDTO> getAddressPaging(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id", required = false) String sortBy,
			@RequestParam(defaultValue = "asc", required = false) String sortDirection,
			@RequestParam(required = false) String street, @RequestParam(required = false) String country) {

		return addressService.findAllByFilter(new AddressFilterDTO(street, country),
				new PagingSorting(page-1, size, sortBy, sortDirection));
	}

/*	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Address" , notes = "This method will return new Address")
	public AddressDTO create(@RequestBody @Valid AddressDTO addressDTO) {
		return addressService.save(addressDTO);
	} */
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create Address", notes = "This method will create and return new Address")
	public AddressDTO create(@RequestBody @Valid AddressDTO addressDTO) {
		return addressService.save(addressDTO);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Address", notes = "This method will update the Address with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<AddressDTO> update(@PathVariable Integer id, @RequestBody @Valid AddressDTO address) {
		return ResponseEntity.status(200).body(addressService.update(id, address));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Address", notes = "This method will delete Address")
	public boolean delete(@PathVariable Integer id) {
		return addressService.deleteById(id);
	}
}
