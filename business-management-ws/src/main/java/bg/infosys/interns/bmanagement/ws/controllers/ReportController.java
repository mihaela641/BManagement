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

import bg.infosys.interns.bmanagement.core.dto.ReportFilterDTO;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.config.ControllerConfig;
import bg.infosys.interns.bmanagement.ws.dto.ReportDTO;
import bg.infosys.interns.bmanagement.ws.service.ReportService;
import bg.infosys.interns.bmanagement.ws.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Reports")
@RequestMapping(ControllerConfig.REPORT_URL)
public class ReportController {
	private ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all Reports", notes = "This method will return all Reports")
	public List<ReportDTO>getAll(){
		return reportService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find one Report", notes = "This method will get the Report with given id")
	public ReportDTO getReport(@PathVariable(value = "id") Integer id){
		return reportService.findById(id);
	}
	
	@GetMapping("/paging")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get one page of Reports", notes = "This method will return page of Reports")
	public Page<ReportDTO> getProductsPaging(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id", required = false) String sortBy,
	        @RequestParam(defaultValue = "asc", required = false) String sortDirection,
	        @RequestParam(required = false) Integer shop,
	        @RequestParam(required = false) Integer product,
	        @RequestParam(required = false) Double price,
	        @RequestParam(required = false) Double quantity,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
		    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
		
		return reportService.findAllByFilter(new ReportFilterDTO(shop,product,quantity,dateFrom,dateTo),
											 new PagingSorting(page-1, size, sortBy, sortDirection));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new Report", notes = "This method will create new Report")
	public ResponseEntity<ReportDTO> create(@RequestBody @Valid ReportDTO reportDTO) {
		
		return ResponseEntity.status(201).body(reportService.save(reportDTO));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Report", notes = "This method will update the Report with given id or will return HTTP Status 404 Not Found")
	public ResponseEntity<ReportDTO> update(@PathVariable Integer id, @RequestBody @Valid ReportDTO report) {
		ReportDTO updatedReport = reportService.update(id, report);
		
		return ResponseEntity.status(200).body(updatedReport);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Report", notes = "This method will delete the Report matching the passed id")
	public void delete(@PathVariable Integer id) {
		reportService.deleteById(id);
	}
}
