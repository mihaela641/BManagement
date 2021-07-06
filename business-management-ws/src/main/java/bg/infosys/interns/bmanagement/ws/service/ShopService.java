package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.ShopDAO;
import bg.infosys.interns.bmanagement.core.dto.ShopFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Shop;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.ShopDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ShopMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ShopService {
	private ShopDAO shopDAO;
	private ShopMapper shopMapper;
	
	public ShopService(ShopDAO shopDAO, ShopMapper shopMapper) {
		this.shopDAO = shopDAO;
		this.shopMapper = shopMapper;
	}
	
	public List<ShopDTO>findAll(){
		return shopDAO.getAll().stream().map(e->shopMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public ShopDTO findById(Integer id){
		return shopMapper.toDto(shopDAO.getById(id));
	}
	
	public Page<ShopDTO> findAllByFilter(ShopFilterDTO filter, PagingSorting pagingSorting) {
		List<Shop> results = shopDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ShopDTO>(results.stream()
									     .map(p -> shopMapper.toDto(p))
									     .collect(Collectors.toList()), shopDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public ShopDTO create(ShopDTO shopDTO) {
		Shop newShop = shopMapper.toEntity(shopDTO);
		 shopDAO.save(shopMapper.toEntity(shopDTO));
		 
		return shopMapper.toDto(newShop);
	}
	
	@Transactional
	public ShopDTO update(Integer id, ShopDTO shop) {
		Shop updatedShop = shopDAO.findById(id);

		shop.setId(id);
		updatedShop = shopDAO.merge(shopMapper.toEntity(shop));
		return shopMapper.toDto(updatedShop);
	}
	
	@Transactional
	public boolean delete(Integer id){
		return shopDAO.delete(id);
	}
}
