package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.RecipeDAO;
import bg.infosys.interns.bmanagement.core.dto.RecipesFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Recipes;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.RecipeDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.RecipeMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;


@Service
public class RecipeService {
	
	private RecipeDAO recipeDAO;
	private RecipeMapper recipeMapper;
	
	public RecipeService(RecipeDAO recipeDAO, RecipeMapper recipeMapper) {
		this.recipeDAO = recipeDAO;
		this.recipeMapper = recipeMapper;
	}
	
	public List<RecipeDTO>findAll(){
		return recipeDAO.getAll().stream().map(e->recipeMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public RecipeDTO findById(Integer id) {
		return recipeMapper.toDto(recipeDAO.getById(id));
	}
	
	public Page<RecipeDTO> findAllByFilter(RecipesFilterDTO filter, PagingSorting pagingSorting) {
		List<Recipes> results = recipeDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<RecipeDTO>(results.stream()
						.map(p -> recipeMapper.toDto(p))
						 .collect(Collectors.toList()), recipeDAO.countAllByFilter(filter)
						 ,pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public RecipeDTO save(RecipeDTO recipeDTO) {
		Recipes newRecipe = recipeMapper.toEntity(recipeDTO);
		recipeDAO.save(newRecipe);
		
		return recipeMapper.toDto(newRecipe);
	}

	@Transactional
	public RecipeDTO update(Integer id, RecipeDTO recipe ) {
		Recipes updatedRecipe = recipeDAO.findById(id);

		recipe.setId(id);
		updatedRecipe = recipeDAO.merge(recipeMapper.toEntity(recipe));
		return recipeMapper.toDto(updatedRecipe);
	}
	
	@Transactional
	public boolean deleteById(Integer recipeId) {
		return recipeDAO.delete(recipeId);
	}
	

	
}
