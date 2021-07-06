package bg.infosys.interns.bmanagement.ws.controllers.handler;

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

import bg.infosys.interns.bmanagement.core.dto.RecipesFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.RecipeDTO;
import bg.infosys.interns.bmanagement.ws.service.RecipeService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Recipes")
@RequestMapping(ControllerConfig.RECIPE_URL)
public class RecipeContorller {
	
	private RecipeService recipeService;

	public RecipeContorller(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Recipes", notes = "This method will return all Recipes")
	public List<RecipeDTO>getAll(){
		return recipeService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Recipe", notes = "This method will get the Recipe with given id")
	public RecipeDTO getRecipe(@PathVariable(value = "id") Integer id){
		return recipeService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Recipe", notes = "This method will return page of Recipe")
	public Page<RecipeDTO> getRecipesPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) String product,
	        @RequestParam(required = false) String description){
		
		return recipeService.findAllByFilter(new RecipesFilterDTO(name ,product, description),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Recipe", notes = "This method will create new Recipe")
	public ResponseEntity<RecipeDTO> create(@RequestBody @Valid RecipeDTO recipeDTO) {
		
		return ResponseEntity.status(201).body(recipeService.save(recipeDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Recipe", notes = "This method will update the Recipe with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<RecipeDTO> update(@PathVariable Integer id, @RequestBody @Valid RecipeDTO recipe) {
		RecipeDTO updatedRecipe = recipeService.update(id,recipe);
		
		return ResponseEntity.status(200).body(updatedRecipe);
	}
	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Position", notes = "This method will delete the Position matching the passed id")
	public void delete(@PathVariable Integer id) {
		recipeService.deleteById(id);
	}
	
	

}
