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

import bg.infosys.interns.bmanagement.core.dto.PositionFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.PositionDTO;
import bg.infosys.interns.bmanagement.ws.service.PositionService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Position")
@RequestMapping(ControllerConfig.POSITION_URL)
public class PositionController {

	private PositionService positionService;
	
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Position", notes = "This method will return all Position")
	public List<PositionDTO> getAll(){
		return positionService.findAll();
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Positions" , notes ="This method will return page of Positions")
	public Page<PositionDTO> getPositionPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String code) {
		
		return positionService.findAllByFilter(new PositionFilterDTO(name, code),
				 new PagingSorting(page-1, size, sortBy, sortDirection));
	}  
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Position", notes = "This method will return one Position")
	public PositionDTO getPosition(@PathVariable Integer id) {
		return positionService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Position" , notes = "This method will return new Position")
	public PositionDTO create(@RequestBody @Valid PositionDTO positionDTO) {
		return positionService.save(positionDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Position", notes = "This method will update the Position with given id or will return HTTP Status 404 Not Found")
	public PositionDTO update( @RequestBody @Valid PositionDTO positionDTO, @PathVariable Integer id) {
		return positionService.update(positionDTO, id); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Position", notes = "This method will delete the Position matching the passed id")
	public void delete(@PathVariable Integer id) {
		positionService.deleteById(id);
	}
	
	
}
