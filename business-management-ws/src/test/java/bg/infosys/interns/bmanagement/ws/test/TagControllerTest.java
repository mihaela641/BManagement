package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bg.infosys.interns.bmanagement.core.dao.TagDAO;
import bg.infosys.interns.bmanagement.core.entity.Tag;
import bg.infosys.interns.bmanagement.ws.dto.mapper.TagMapper;
import bg.infosys.interns.bmanagement.ws.service.TagService;

public class TagControllerTest {

	private TagDAO tagDAO;
	private TagMapper tagMapper;
	private TagService tagService;

	private List<Tag> tag;

	@Before
	public void init() {
	
	tagDAO = Mockito.mock(TagDAO.class);
	tagMapper = new TagMapper();
	tagService= new TagService(tagDAO , tagMapper);

	Tag tag1 = new Tag();
	tag1.setId(1);
	tag1.setName("FirstTag");
	tag1.setCode("12s2");
	
	Tag secondTag = new Tag();
	secondTag.setId(2);
	secondTag.setName("SecondTag");
	secondTag.setCode("gg3g");

	Tag thirdTag = new Tag();
	thirdTag.setId(3);
	thirdTag.setName("ThirdTag");
	thirdTag.setCode("949m");

	tag = new ArrayList<>(List.of(tag1,secondTag,thirdTag));
	Mockito.when(tagDAO.findAll()).thenReturn(tag);

	}
	
	@Test
	public void findTag() {
		assertEquals(3, tagService.findAll().size());
		//return the size
	}

	@Test
	public void findTagZero() {
		tag.clear();
		assertEquals(0,tagService.findAll().size());
	}
	
	@Test
	public void findById() {
		Integer id = 1;
		
		Mockito.when(tagDAO.findById(Mockito.anyInt())).thenReturn(tag.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
		
		assertNotNull(tagDAO.findById(id));
	}

	@Test
	public void NotExistsTag() {
		Integer id = 15;
		
		Mockito.when(tagDAO.findById(id)).thenReturn(tag.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
	}

}
