package bg.infosys.interns.bmanagement.ws.controllers;

import java.util.List;

import javax.validation.Valid;

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

import bg.infosys.interns.bmanagement.core.dto.TypeFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.TypeDTO;
import bg.infosys.interns.bmanagement.ws.service.TypeService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Tape")
@RequestMapping(ControllerConfig.TYPE_URL)
public class TypeController {

	private TypeService typeService;
	
	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Type", notes = "This method will return all Types")
	public List<TypeDTO>getAll(){
		return typeService.findAll();
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Types", notes = "This method will return page of Types")
	public Page<TypeDTO> getTypesPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String code,
	        @RequestParam(required = false) String parentType) 
		    {
		return typeService.findAllByFilter(new TypeFilterDTO(name, code, parentType),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Type", notes = "This method will get the Type with given id")
	public TypeDTO getType(@PathVariable Integer id){
		return typeService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Type", notes = "This method will create new Type")
	public TypeDTO create(@RequestBody @Valid TypeDTO typeDTO) {
		return typeService.save(typeDTO);
	}
	
	@PutMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Type", notes = "This method will update the Type with given id or will return HTTP Status 404 Not Found")
	public TypeDTO update( @RequestBody @Valid TypeDTO typeDTO, @PathVariable String name) {
		return typeService.update(typeDTO, name);
	}
	
	@DeleteMapping("/{typeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Type", notes = "This method will delete the Type matching the passed id")
	public void delete(@PathVariable Integer id) {
		typeService.deleteById(id);
	}
}

