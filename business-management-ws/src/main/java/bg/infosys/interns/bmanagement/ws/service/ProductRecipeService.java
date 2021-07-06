package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.ProductRecipeDAO;
import bg.infosys.interns.bmanagement.core.dto.ProductRecipeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.ProductRecipe;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.ProductRecipeDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ProductRecipeMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ProductRecipeService {

	private ProductRecipeDAO productRecipeDAO;
	private ProductRecipeMapper productRecipeMapper;
	
	public ProductRecipeService(ProductRecipeDAO productRecipeDAO, ProductRecipeMapper productRecipeMapper) {
		this.productRecipeDAO = productRecipeDAO;
		this.productRecipeMapper = productRecipeMapper;
	}
	
	public List<ProductRecipeDTO>findAll(){
		return productRecipeDAO.getAll().stream().map(e->productRecipeMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public ProductRecipeDTO findById(Integer id) {
		return productRecipeMapper.toDto(productRecipeDAO.getById(id));
	}
	
	public Page<ProductRecipeDTO> findAllByFilter(ProductRecipeFilterDTO filter, PagingSorting pagingSorting) {
		List<ProductRecipe> results = productRecipeDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ProductRecipeDTO>(results.stream()
						.map(p -> productRecipeMapper.toDto(p))
						 .collect(Collectors.toList()), productRecipeDAO.countAllByFilter(filter)
						 ,pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public ProductRecipeDTO save(ProductRecipeDTO productRecipeDTO) {
		ProductRecipe newProductRecipe = productRecipeMapper.toEntity(productRecipeDTO);
		productRecipeDAO.save(newProductRecipe);
		
		return productRecipeMapper.toDto(newProductRecipe);
	}
	
	@Transactional
	public ProductRecipeDTO update(Integer id, ProductRecipeDTO productRecipe ) {
		ProductRecipe updateProductRecipe = productRecipeDAO.findById(id);

		productRecipe.setId(id);
		updateProductRecipe = productRecipeDAO.merge(productRecipeMapper.toEntity(productRecipe));
		return productRecipeMapper.toDto(updateProductRecipe);
	}
	
	@Transactional
	public boolean deleteById(Integer productRecipeId) {
		return productRecipeDAO.delete(productRecipeId);
	}
	
	
	
}
