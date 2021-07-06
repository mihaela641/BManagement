package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import bg.infosys.interns.bmanagement.core.dao.ManufacturerDAO;
import bg.infosys.interns.bmanagement.core.entity.Manufacturer;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ManufacturerMapper;
import bg.infosys.interns.bmanagement.ws.service.ManufacturerService;


public class ManufacturerContorllerTest {
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	private ManufacturerMapper manufacturerMapper;
	private ManufacturerService manufacturerService;

	private List<Manufacturer> manufacturer;

	@Before
	public void init() {
	
		manufacturerDAO = Mockito.mock(ManufacturerDAO .class);
		manufacturerMapper = new ManufacturerMapper(null);
		manufacturerService = new ManufacturerService ( manufacturerDAO ,manufacturerMapper );
	
		Manufacturer manufacturer1 = new Manufacturer();
		manufacturer1.setId(1);
		manufacturer1.setName("Manufacturer OOD");
		manufacturer1.setAddress(null);
		manufacturer1.setDescription("First manufacturer");
	
		Manufacturer secondManufacturer = new Manufacturer();
		secondManufacturer.setId(2);
		secondManufacturer.setName("Best Buy");
		secondManufacturer.setAddress(null);
		secondManufacturer.setDescription("Second Manufacturer");
		
		Manufacturer thirdManufacturer = new Manufacturer();
		thirdManufacturer.setId(3);
		thirdManufacturer.setName("OOD Product Company");
		thirdManufacturer.setAddress(null);
		thirdManufacturer.setDescription("Third Manufacturer");
	
		manufacturer = new ArrayList<>(List.of(manufacturer1,secondManufacturer,thirdManufacturer));
		Mockito.when(manufacturerDAO.findAll()).thenReturn(manufacturer);

	}
	
	@Test
	public void findManufacturer() {
		assertEquals(3, manufacturerDAO.findAll().size());
		//return the size
	}

	@Test
	public void findManufacturerZero() {
		manufacturer.clear();
		assertEquals(0,manufacturerService.findAll().size());
	}
	
	@Test
	public void findById() {
		Integer id = 2;
		Mockito.when(manufacturerDAO.findById(Mockito.anyInt())).thenReturn(manufacturer.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
		
		assertNotNull(manufacturerDAO.findById(id));
	
	}
	
	@Test
	public void NotExistsManufacturer() {
		Integer id = 15;
		
		Mockito.when(manufacturerDAO.findById(id)).thenReturn(manufacturer.stream()
				.filter(a -> a.getId().equals(id)).findAny().orElse(null));
		
	}

}
