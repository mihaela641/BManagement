package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.ProductShopDAO;
import bg.infosys.interns.bmanagement.core.dto.ProductShopFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.ProductShop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.ProductShopDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ProductShopMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ProductShopService {

	private ProductShopDAO productShopDAO;
	private ProductShopMapper productShopMapper;
	
	public ProductShopService(ProductShopDAO productShopDAO, ProductShopMapper productShopMapper) {
		this.productShopDAO = productShopDAO;
		this.productShopMapper = productShopMapper;
	}
	
	public List<ProductShopDTO>findAll(){
		return productShopDAO.getAll().stream().map(e->productShopMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public ProductShopDTO findById(Integer id) {
		return productShopMapper.toDto(productShopDAO.getById(id));
	}
	
	public Page<ProductShopDTO> findAllByFilter(ProductShopFilterDTO filter, PagingSorting pagingSorting) {
		List<ProductShop> results = productShopDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ProductShopDTO>(results.stream()
									     .map(p -> productShopMapper.toDto(p))
									     .collect(Collectors.toList()), productShopDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public ProductShopDTO create(ProductShopDTO productShopDTO) {
		ProductShop productShop = productShopMapper.toEntity(productShopDTO);
		productShopDAO.save(productShop);
		
		return productShopMapper.toDto(productShop);
	}
	
	@Transactional
	public ProductShopDTO update(Integer id, ProductShopDTO productShop) {
		ProductShop updatedProductShop = productShopDAO.findById(id);

		productShop.setId(id);
		updatedProductShop = productShopDAO.merge(productShopMapper.toEntity(productShop));
		return productShopMapper.toDto(updatedProductShop);
	}
	
	@Transactional
	public boolean delete(Integer id) {
		return productShopDAO.delete(id);
	}
}
