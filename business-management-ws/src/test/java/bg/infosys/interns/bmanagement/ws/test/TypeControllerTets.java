package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bg.infosys.interns.bmanagement.core.dao.TypeDAO;
import bg.infosys.interns.bmanagement.core.entity.Type;
import bg.infosys.interns.bmanagement.ws.dto.mapper.TypeMapper;
import bg.infosys.interns.bmanagement.ws.service.TypeService;

public class TypeControllerTets {

	private TypeDAO typeDAO;
	private TypeMapper typeMapper;
	private TypeService typeService;

	private List<Type> type;

	@Before
	public void init() {
	
	typeDAO = Mockito.mock(TypeDAO .class);
	typeMapper = new TypeMapper();
	typeService= new TypeService(typeDAO , typeMapper);

	Type type1 = new Type();
	type1.setId(1);
	type1.setName("FirstType");
	type1.setCode("12s2");
	type1.setParentType(null); 
	
	Type secondType = new Type();
	secondType.setId(2);
	secondType.setName("SecondType");
	secondType.setCode("gg3g");
	secondType.setParentType(null);

	Type thirdType = new Type();
	thirdType.setId(3);
	thirdType.setName("ThirdType");
	thirdType.setCode("949m");
	thirdType.setParentType(null);

	type = new ArrayList<>(List.of(type1,secondType,thirdType));
	Mockito.when(typeDAO.findAll()).thenReturn(type);

	}
	
	@Test
	public void findType() {
		assertEquals(3, typeService.findAll().size());
		//return the size
	}

	@Test
	public void findTypeZero() {
		type.clear();
		assertEquals(0,typeService.findAll().size());
	}
	
	@Test
	public void findById() {
		Integer id = 1;
		
		Mockito.when(typeDAO.findById(Mockito.anyInt())).thenReturn(type.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
		
		assertNotNull(typeService.findById(id));
	}

	@Test
	public void NotExistsType() {
		Integer id = 15;
		
		Mockito.when(typeDAO.findById(id)).thenReturn(type.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
		
	}

}
