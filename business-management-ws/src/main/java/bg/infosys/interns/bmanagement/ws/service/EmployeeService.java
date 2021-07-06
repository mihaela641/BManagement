package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.EmployeeDAO;
import bg.infosys.interns.bmanagement.core.dto.EmployeeFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Employee;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.EmployeeDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.EmployeeMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class EmployeeService {

	private EmployeeDAO employeeDAO;
	private EmployeeMapper employeeMapper;
	
	public EmployeeService(EmployeeDAO employeeDAO, EmployeeMapper employeeMapper) {
		this.employeeDAO = employeeDAO;
		this.employeeMapper = employeeMapper;
	}

	public List<EmployeeDTO>findAll(){
		return employeeMapper.toDtoList(employeeDAO.getAll());
	}
	
	public EmployeeDTO findById(Integer id) {
		return employeeMapper.toDto(employeeDAO.getById(id));
	}
	
	public Page<EmployeeDTO> findAllByFilter(EmployeeFilterDTO filter, PagingSorting pagingSorting) {
		List<Employee> results = employeeDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<EmployeeDTO>(results.stream()
									     .map(p -> employeeMapper.toDto(p))
									     .collect(Collectors.toList()), employeeDAO.countAllByFilter(filter), pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	@Transactional
	public EmployeeDTO create(EmployeeDTO employeeDTO) {
		Employee newEmployee = employeeMapper.toEntity(employeeDTO);
		employeeDAO.save(newEmployee);
		
		return employeeMapper.toDto(newEmployee);
	}
	
	@Transactional
	public EmployeeDTO update(Integer id, EmployeeDTO employee) {
		Employee updatedEmployee = employeeDAO.findById(id);

		employee.setId(id);
		updatedEmployee = employeeDAO.merge(employeeMapper.toEntity(employee));
		return employeeMapper.toDto(updatedEmployee);
	}
	
	@Transactional
	public boolean deleteById(Integer id) {
		return employeeDAO.delete(id);
	}
}
