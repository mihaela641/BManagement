package bg.infosys.interns.bmanagement.ws.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bg.infosys.interns.bmanagement.core.dao.PositionDAO;
import bg.infosys.interns.bmanagement.core.entity.Position;
import bg.infosys.interns.bmanagement.ws.dto.mapper.PositionMapper;
import bg.infosys.interns.bmanagement.ws.service.PositionService;

public class PositionContollerTest {

		private PositionDAO positionDAO;
		private PositionMapper positionMapper;
		private PositionService positionService;

		private List<Position> position;

		@Before
		public void init() {
		
		positionDAO = Mockito.mock(PositionDAO.class);
		positionMapper = new PositionMapper();
		positionService = new PositionService(positionDAO, positionMapper);

		Position position1 = new Position();
		position1.setId(1);
		position1.setName("CEO");
		position1.setCode("12s2");

		Position secondPosition = new Position();
		secondPosition.setId(2);
		secondPosition.setName("CAO");
		secondPosition.setCode("23d3");

		Position thirdPosition = new Position();
		thirdPosition.setId(3);
		thirdPosition.setName("DAO");
		thirdPosition.setCode("2ss1");

		position = new ArrayList<>(List.of(position1,secondPosition,thirdPosition));
		Mockito.when(positionDAO.findAll()).thenReturn(position);

		}
		
		@Test
		public void findPosition() {
			assertEquals(3, positionDAO.findAll().size());
			//return the size
		}

		@Test
		public void findPositionZero() {
			position.clear();
			assertEquals(0,positionService.findAll().size());
		}
		
		@Test
		public void findById() {
			Integer id = 1;
			
			Mockito.when(positionDAO.findById(Mockito.anyInt())).thenReturn(position.stream()
					.filter(a -> a.getId().equals(id)).findAny().orElse(null));
			
			assertNotNull(positionDAO.findById(id));
		}
		
		@Test
		public void NotExistsPosition() {
			Integer id = 19;
			
			Mockito.when(positionDAO.findById(id)).thenReturn(position.stream()
					.filter(a -> a.getId().equals(id)).findAny().orElse(null));
			
		}
	

}
