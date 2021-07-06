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

import bg.infosys.interns.bmanagement.core.dto.TagFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.TagDTO;
import bg.infosys.interns.bmanagement.ws.service.TagService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Tag")
@RequestMapping(ControllerConfig.TAG_URL)
public class TagController {
	
	private TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Tags", notes = "This method will return all Tags")
	public List<TagDTO>getAll(){
		return tagService.findAll();
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Tags", notes = "This method will return page of Tags")
	public Page<TagDTO> getTagsPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String code){
		return tagService.findAllByFilter(new TagFilterDTO(code , name),
				new PagingSorting(page-1, size, sortBy, sortDirection));
	}
			
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Tag", notes = "This method will get the Tag with given id")
	public TagDTO getTag(@PathVariable Integer id){
		return tagService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Tag", notes = "This method will create new Tag")
	public TagDTO create(@RequestBody @Valid TagDTO tagDTO) {
		return tagService.save(tagDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Tag", notes = "This method will update the Tag with given id or will return HTTP Status 404 Not Found")
	public TagDTO update(@PathVariable Integer id, @RequestBody @Valid TagDTO tagDTO) {
		return tagService.update(tagDTO,id); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Tag", notes = "This method will delete the Tag matching the passed id")
	public void delete(@PathVariable Integer id) {
		tagService.deleteById(id);
	}
	

}
