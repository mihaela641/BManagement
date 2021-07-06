package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.infosys.interns.bmanagement.core.dao.ProductDAO;
import bg.infosys.interns.bmanagement.ws.dto.ProductDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ProductMapper;
import bg.infosys.interns.bmanagement.ws.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	private static final Integer PRODUCT_ID = 1;
	private static final String PRODUCT_NAME = "Cola";
	
	@Mock
	private ProductDAO dao;
	
	@Mock
	private ProductMapper mapper;
	
	private ProductDTO dto;
	
	@InjectMocks
	private ProductService service;
	
	public void setUp() {
		dto= new ProductDTO();
		dto.setId(PRODUCT_ID);
		dto.setName(PRODUCT_NAME);
	}
	
	@Test
	public void testGetById() {
		when(service.findById(Mockito.anyInt())).thenReturn(dto);
		assertEquals(service.findById(PRODUCT_ID),dto);
	}
	
	@Test
	public void findAll() {
		List<ProductDTO>products=Arrays.asList(dto);
		when(service.findAll()).thenReturn(products);
		assertEquals(service.findAll(),products);
	}
}
