package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.PositionDAO;
import bg.infosys.interns.bmanagement.core.dto.PositionFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Position;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.util.Page;
import bg.infosys.interns.bmanagement.ws.dto.PositionDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.PositionMapper;

@Service
public class PositionService {
		private final PositionDAO positionDAO;
		private final PositionMapper positionMapper;
		
		public PositionService(PositionDAO positionDAO,PositionMapper positionMapper) {
			this.positionDAO = positionDAO;
			this.positionMapper = positionMapper;
		}
		
		public List<PositionDTO>findAll(){
			return positionMapper.toDtoList(positionDAO.getAll());
			//return positionDAO.findAll().stream().map(e->positionMapper.toDto(e)).collect(Collectors.toList());
		}
		
		public PositionDTO findById(Integer id) {
			return positionMapper.toDto(positionDAO.getById(id));
		}
		
		public Page<PositionDTO> findAllByFilter(PositionFilterDTO filter, PagingSorting pagingSorting) {
			List<Position> results = positionDAO.findAllByFilter(filter, pagingSorting);
			
			return new Page<PositionDTO>(results.stream()
						.map(p -> positionMapper.toDto(p))
						.collect(Collectors.toList()),positionDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
		}   
		 
		@Transactional
		public PositionDTO save(PositionDTO positionDTO) {
			Position newPosition = positionMapper.toEntity(positionDTO);
			positionDAO.save(newPosition);
			
			return positionMapper.toDto(newPosition);
		}
		
		@Transactional
		public PositionDTO update(PositionDTO positionDTO, Integer id) {
			Position updatedPosition = positionDAO.findById(id);

			positionDTO.setId(id);
			updatedPosition = positionDAO.merge(positionMapper.toEntity(positionDTO));
			return positionMapper.toDto(updatedPosition);
		}
		
		@Transactional
		public boolean deleteById(Integer positionId) {
			return positionDAO.delete(positionId);
		}
}
