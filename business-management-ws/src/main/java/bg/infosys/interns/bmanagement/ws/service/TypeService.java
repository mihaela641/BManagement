package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.TypeDAO;
import bg.infosys.interns.bmanagement.core.dto.TypeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.TypeDTO;

import bg.infosys.interns.bmanagement.ws.dto.mapper.TypeMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class TypeService {
	private TypeDAO typeDAO;
	private TypeMapper typeMapper;

	public TypeService(TypeDAO typeDAO, TypeMapper typeMapper) {
		this.typeDAO = typeDAO;
		this.typeMapper = typeMapper;
	}
	
	public List<TypeDTO>findAll(){
		return typeDAO.findAll().stream().map(e->typeMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public TypeDTO findById(Integer id) {
		return typeMapper.toDto(typeDAO.findById(id));
	} 
	
	public Page<TypeDTO> findAllByFilter(TypeFilterDTO filter, PagingSorting pagingSorting) {
		List<Type> results = typeDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<TypeDTO>(results.stream()
					 .map(p -> typeMapper.toDto(p))
					.collect(Collectors.toList()), typeDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	} 
	
	@Transactional
	public TypeDTO save(TypeDTO typeDTO) {
		Type newType = typeMapper.toEntity(typeDTO);
		typeDAO.save(newType);
		
		return typeMapper.toDto(newType);
	}
	
	@Transactional
	public TypeDTO update(TypeDTO typeDTO, String name) {
		Type type = typeMapper.toEntity(typeDTO);
		type.setName(name);
		type = typeDAO.saveOrUpdate(type);
		
		return typeMapper.toDto(type);
	}
	
	@Transactional
	public boolean deleteById(Integer typeId) {
		return typeDAO.delete(typeId);
	}
}
