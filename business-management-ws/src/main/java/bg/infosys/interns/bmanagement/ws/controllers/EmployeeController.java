package bg.infosys.interns.bmanagement.ws.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.interns.bmanagement.core.dto.EmployeeFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.EmployeeDTO;
import bg.infosys.interns.bmanagement.ws.service.EmployeeService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Employees")
@RequestMapping(ControllerConfig.EMPLOYEE_URL)
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Employees", notes = "This method will return all Employees")
	public List<EmployeeDTO>getEmployees(){
		return employeeService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Employee", notes = "This method will return Employee by given id")
	public EmployeeDTO getEmployee(@PathVariable Integer id) {
		return employeeService.findById(id);
	}
		
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Employee", notes = "This method will return page of Employee")
	public Page<EmployeeDTO> getEmployeesPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
		    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
		    @RequestParam(required = false) Integer position,
		    @RequestParam(required = false) Integer country,
		    @RequestParam(required = false) Double salary,
		    @RequestParam(required = false) String shop
		    ){
		
		return employeeService.findAllByFilter(new EmployeeFilterDTO(name, dateFrom, dateTo, position, country, salary, shop),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create Employee", notes = "This method will create and return new Employee")
	public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO) {
		return ResponseEntity.status(201).body(employeeService.create(employeeDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Employee", notes = "This method will update the Employee with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id, @RequestBody @Valid EmployeeDTO employee) {
		return ResponseEntity.status(200).body(employeeService.update(id, employee));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Employee", notes = "This method will delete Employee")
	public void delete(@PathVariable Integer id) {
		employeeService.deleteById(id);
	}
}