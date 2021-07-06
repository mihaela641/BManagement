package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.ProductDAO;
import bg.infosys.interns.bmanagement.core.dto.ProductFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Product;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.ProductDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ProductMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ProductService {
	
	private ProductDAO productDAO;
	private ProductMapper productMapper;

	public ProductService(ProductDAO productDAO, ProductMapper productMapper) {
		this.productDAO = productDAO;
		this.productMapper = productMapper;
	}
	
	public List<ProductDTO>findAll(){
		return productMapper.toDtoList(productDAO.getAll());
	}
	
	public ProductDTO findById(Integer id) {
		return productMapper.toDto(productDAO.getById(id));
	}
	
	public Page<ProductDTO> findAllByFilter(ProductFilterDTO filter, PagingSorting pagingSorting) {
		List<Product> results = productDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ProductDTO>(results.stream()
						.map(p -> productMapper.toDto(p))
						 .collect(Collectors.toList()), productDAO.countAllByFilter(filter)
						 ,pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {
		Product newProduct = productMapper.toEntity(productDTO);
		productDAO.save(newProduct);
		
		return productMapper.toDto(newProduct);
	}
	
	@Transactional
	public ProductDTO update(Integer id, ProductDTO product ) {
		Product updatedProduct = productDAO.findById(id);

		product.setId(id);
		updatedProduct = productDAO.merge(productMapper.toEntity(product));
		return productMapper.toDto(updatedProduct);
	}
	
	@Transactional
	public boolean deleteById(Integer productId) {
		return productDAO.delete(productId);
	}
}
