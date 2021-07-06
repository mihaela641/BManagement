package bg.infosys.interns.bmanagement.ws.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.interns.bmanagement.core.entity.Employee;
import bg.infosys.interns.bmanagement.ws.dto.EmployeeDTO;

@Service
public class EmployeeMapper implements IModelMapper<EmployeeDTO, Employee>{
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private CountryMapper countryMapper;
	
	
	/*public EmployeeMapper(PositionMapper positionMapper, ShopMapper shopMapper, CountryMapper countryMapper) {
		this.positionMapper = positionMapper;
		this.shopMapper = shopMapper;
		this.countryMapper = countryMapper;
	}*/

	@Override
	public EmployeeDTO toDto(Employee entity) {
		if (entity == null) return null;
		return new EmployeeDTO(entity.getId(),entity.getName(),entity.getBirthDate()
				,positionMapper.toDto(entity.getPosition())
				,countryMapper.toDto(entity.getCountry())
				,entity.getSalary()
				,shopMapper.toDto(entity.getShop()),entity.getIsDeleted());
	}
	
	public List<EmployeeDTO>toDtoList(List<Employee>entityList){
		if (entityList == null) return null;
		return entityList.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	@Override
	public Employee toEntity(EmployeeDTO dto) {
		if (dto == null) return null;
		return new Employee(dto.getId(),dto.getName(),dto.getBirthDate()
				,positionMapper.toEntity(dto.getPosition())
				,countryMapper.toEntity(dto.getCountry())
				,dto.getSalary()
				,shopMapper.toEntity(dto.getShop()),dto.getIsDeleted());
	}
	
	public List<Employee>toEntityList(List<EmployeeDTO>dtoList){
		if (dtoList == null) return null;
		return dtoList.stream().map(e->toEntity(e)).collect(Collectors.toList());
	}
}