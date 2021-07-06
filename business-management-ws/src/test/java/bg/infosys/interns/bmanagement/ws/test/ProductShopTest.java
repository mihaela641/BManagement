package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.infosys.interns.bmanagement.core.dao.ProductShopDAO;
import bg.infosys.interns.bmanagement.ws.dto.ProductShopDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ProductShopMapper;
import bg.infosys.interns.bmanagement.ws.service.ProductShopService;

@RunWith(MockitoJUnitRunner.class)
public class ProductShopTest {
	private static final Integer PRODUCT_SHOP_ID = 1;
	
	@Mock
	private ProductShopDAO dao;
	
	@Mock
	private ProductShopMapper mapper;
	
	
	private ProductShopDTO dto;
	
	@InjectMocks
	private ProductShopService service;
	
	@Before
	public void setUp() {
		dto = new ProductShopDTO();
		dto.setId(PRODUCT_SHOP_ID);
		dto.setQuantity(10);
	}
	
	@Test
	public void testGetById() {
		when(service.findById(Mockito.anyInt())).thenReturn(dto);
		assertEquals(service.findById(PRODUCT_SHOP_ID),dto);
	}
}
