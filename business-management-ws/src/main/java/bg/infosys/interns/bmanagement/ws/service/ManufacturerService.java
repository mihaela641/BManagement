package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import bg.infosys.interns.bmanagement.core.dao.ManufacturerDAO;
import bg.infosys.interns.bmanagement.core.dto.ManufacturerFilterDTO;

import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ManufacturerMapper;

import bg.infosys.interns.bmanagement.ws.dto.ManufacturerDTO;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ManufacturerService {
	
	private ManufacturerDAO manufacturerDAO;
	private ManufacturerMapper manufacturerMapper;
	
	public ManufacturerService(ManufacturerDAO manufacturerDAO, ManufacturerMapper manufacturerMapper) {
		this.manufacturerDAO = manufacturerDAO;
		this.manufacturerMapper = manufacturerMapper;
		
	}

	public List<ManufacturerDTO>findAll(){
		return manufacturerMapper.toDtoList(manufacturerDAO.getAll());
	}
	
	public ManufacturerDTO findById(Integer id) {
		return manufacturerMapper.toDto(manufacturerDAO.getById(id));
	}
	
	public Page<ManufacturerDTO> findAllByFilter(ManufacturerFilterDTO filter, PagingSorting pagingSorting) {
		List<Manufacturer> results = manufacturerDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ManufacturerDTO>(results.stream()
					 .map(p -> manufacturerMapper.toDto(p))
					.collect(Collectors.toList()), manufacturerDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	} 
	
	@Transactional
	public ManufacturerDTO save(ManufacturerDTO manufacturerDTO) {
		Manufacturer newManufacturer = manufacturerMapper.toEntity(manufacturerDTO);
		manufacturerDAO.save(newManufacturer);
		
		return manufacturerMapper.toDto(newManufacturer);
	}

	@Transactional
	public ManufacturerDTO update(ManufacturerDTO manufacturerDTO, Integer id) {
		Manufacturer updatedManufacturer = manufacturerDAO.findById(id);

		manufacturerDTO.setId(id);
		updatedManufacturer = manufacturerDAO.merge(manufacturerMapper.toEntity(manufacturerDTO));
		return manufacturerMapper.toDto(updatedManufacturer);
	}
	
	@Transactional
	public boolean deleteById(Integer manufacturerId) {
		return manufacturerDAO.delete(manufacturerId);
	}
}
