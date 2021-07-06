package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.TagDAO;
import bg.infosys.interns.bmanagement.core.dto.TagFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Tag;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.TagDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.TagMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class TagService {
	private TagDAO tagDAO;
	private TagMapper tagMapper;
	
	public TagService(TagDAO tagDAO, TagMapper tagMapper) {
		this.tagDAO = tagDAO;
		this.tagMapper = tagMapper;
	}
	
	public List<TagDTO>findAll(){
		return tagDAO.getAll().stream().map(e->tagMapper.toDto(e)).collect(Collectors.toList());
	}
	
	public TagDTO findById(Integer id) {
		return tagMapper.toDto(tagDAO.getById(id));
	}
	
	public Page<TagDTO> findAllByFilter(TagFilterDTO filter, PagingSorting pagingSorting) {
		List<Tag> results = tagDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<TagDTO>(results.stream()
					 .map(p -> tagMapper.toDto(p))
					.collect(Collectors.toList()), tagDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	} 
	
	@Transactional
	public TagDTO save(TagDTO tagDTO) {
		Tag newTag = tagMapper.toEntity(tagDTO);
		tagDAO.save(newTag);
		
		return tagMapper.toDto(newTag);
	}

	@Transactional
	public TagDTO update(TagDTO tagDTO, Integer id) {
		Tag updatedTag = tagDAO.findById(id);

		tagDTO.setId(id);
		updatedTag = tagDAO.merge(tagMapper.toEntity(tagDTO));
		return tagMapper.toDto(updatedTag);
	}
	
	@Transactional
	public boolean deleteById(Integer tagId) {
		return tagDAO.delete(tagId);
	}
}
