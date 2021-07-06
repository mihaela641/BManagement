package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import bg.infosys.interns.bmanagement.core.dao.EmployeeDAO;
import bg.infosys.interns.bmanagement.ws.controllers.EmployeeController;
import bg.infosys.interns.bmanagement.ws.dto.EmployeeDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.EmployeeMapper;
import bg.infosys.interns.bmanagement.ws.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	private static final Integer EMPLOYEE_ID = 1;
	private static final String EMPLOYEE_NAME = "George";
	
	@Mock
	private EmployeeDAO dao;
	
	@Mock
	private EmployeeMapper mapper;
	
	@InjectMocks
	private EmployeeService service;
	
	private EmployeeDTO dto;

	@Before
	public void setUp() {
		dto=new EmployeeDTO();
		dto.setId(EMPLOYEE_ID);
		dto.setName(EMPLOYEE_NAME);
	}
	
	@Test
	public void testGetById() {
		when(service.findById(Mockito.anyInt())).thenReturn(dto);
		assertEquals(service.findById(EMPLOYEE_ID),dto);
	}
	
	@Test
	public void findAll() {
		List<EmployeeDTO>employees=Arrays.asList(dto);
		when(service.findAll()).thenReturn(employees);
		assertEquals(service.findAll(),employees);
	}

}
