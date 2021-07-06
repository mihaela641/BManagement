package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Report;
import bg.infosys.interns.bmanagement.ws.dto.ReportDTO;

@Service
public class ReportMapper implements IModelMapper<ReportDTO, Report> {

	private ProductMapper productMapper;
	private ShopMapper shopMapper;
	
	public ReportMapper(ProductMapper productMapper, ShopMapper shopMapper) {
		this.productMapper = productMapper;
		this.shopMapper = shopMapper;
	}

	@Override
	public ReportDTO toDto(Report entity) {
		return new ReportDTO(entity.getId(),shopMapper.toDto(entity.getShop())
				,productMapper.toDto(entity.getProduct()),entity.getQuantity(),entity.getDate(),entity.getIsDeleted());
	}
	
	public List<ReportDTO>toDtoList(List<Report>entityList){
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	@Override
	public Report toEntity(ReportDTO dto) {
		return new Report(dto.getId(),shopMapper.toEntity(dto.getShop())
				,productMapper.toEntity(dto.getProduct()),dto.getQuantity(),dto.getDate(), dto.getIsDeleted());
	}
	
	public List<Report>toEntityList(List<ReportDTO>dtoList){
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}
