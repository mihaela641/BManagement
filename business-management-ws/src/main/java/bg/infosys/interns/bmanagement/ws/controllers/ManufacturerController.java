package bg.infosys.interns.bmanagement.ws.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import bg.infosys.interns.bmanagement.core.dto.ManufacturerFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.ManufacturerDTO;
import bg.infosys.interns.bmanagement.ws.service.ManufacturerService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(tags = "Manufacturer")
@RequestMapping(ControllerConfig.MANUFACTURER_URL)
public class ManufacturerController {
	
	private ManufacturerService manufacturerService;
	
	ManufacturerController(ManufacturerService manufacturerService){
		this.manufacturerService = manufacturerService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Manufacturer", notes = "This method will return all Manufacturer")
	public List<ManufacturerDTO>findAll(){
		return manufacturerService.findAll();
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Manufacturers", notes = "This method will return page of Manufacturers")
	public Page<ManufacturerDTO> getManufacturersPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String address,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundationDate)
		    {	
		return manufacturerService.findAllByFilter(new ManufacturerFilterDTO(name, address, foundationDate),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Manufacturer", notes = "This method will get the Manufacturer with given id")
	public ManufacturerDTO getManufacturer(@PathVariable Integer id){
		return manufacturerService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Manufacturer", notes = "This method will create new Manufacturer")
	public ManufacturerDTO create(@RequestBody @Valid ManufacturerDTO manufacturerDTO) {
		return manufacturerService.save(manufacturerDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Manufacturer", notes = "This method will update the Manufacturer with given id or will return HTTP Status 404 Not Found")
	public ManufacturerDTO update( @RequestBody @Valid ManufacturerDTO manufacturerDTO, @PathVariable Integer id) {
		return manufacturerService.update(manufacturerDTO, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Manufacturer", notes = "This method will delete the Manufacturer matching the passed id")
	public void delete(@PathVariable Integer id) {
		manufacturerService.deleteById(id);
	}



}