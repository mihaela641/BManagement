package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.infosys.interns.bmanagement.core.dao.ShopDAO;
import bg.infosys.interns.bmanagement.ws.dto.ShopDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ShopMapper;
import bg.infosys.interns.bmanagement.ws.service.ShopService;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

	private static final Integer SHOP_ID = 1;
	private static final String SHOP_NAME = "FF";
	
	@Mock
	private ShopDAO dao;
	
	@Mock
	private ShopMapper mapper;
	
	@InjectMocks
	private ShopService service;
	
	private ShopDTO dto;
	
	@Before
	public void setUp() {
		dto = new ShopDTO();
		dto.setId(SHOP_ID);
		dto.setName(SHOP_NAME);
	}
	
	@Test
	public void testFindById() {
		when(service.findById(Mockito.anyInt())).thenReturn(dto);
		assertEquals(service.findById(SHOP_ID),dto);
	}
}
