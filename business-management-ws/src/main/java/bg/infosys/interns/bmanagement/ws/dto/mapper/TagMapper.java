package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Tag;
import bg.infosys.interns.bmanagement.ws.dto.TagDTO;

@Service
public class TagMapper {

	//private final ProductMapper productMapper;
	
	/* public TagMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
		
	} */
	
	public TagDTO toDto(Tag entity) {
		if (entity == null) return null;
		return new TagDTO(entity.getId(), entity.getCode(), entity.getName(), entity.getIsDeleted());
				
	} 
	public List<TagDTO> toDtoList(List<Tag> tagList){
		if (tagList == null) return null;
		return tagList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}
	
	public Tag toEntity(TagDTO dto) {
		if (dto == null) return null;
		
		return new Tag(dto.getId(), dto.getCode(), dto.getName(),dto.getIsDeleted());
		
	} 
	
	public List<Tag> toEntityList(List<TagDTO> dtoList){
		if (dtoList == null) return null; 
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}
