package bg.infosys.interns.bmanagement.ws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.interns.bmanagement.core.dao.ReportDAO;
import bg.infosys.interns.bmanagement.core.dto.ReportFilterDTO;
import bg.infosys.interns.bmanagement.core.entity.Report;
import bg.infosys.interns.bmanagement.core.page.PagingSorting;
import bg.infosys.interns.bmanagement.ws.dto.ReportDTO;
import bg.infosys.interns.bmanagement.ws.dto.mapper.ReportMapper;
import bg.infosys.interns.bmanagement.ws.util.Page;

@Service
public class ReportService {

	private ReportDAO reportDAO;
	private ReportMapper reportMapper;
	
	public ReportService(ReportDAO reportDAO, ReportMapper reportMapper) {
		this.reportDAO = reportDAO;
		this.reportMapper = reportMapper;
	}
	
	public List<ReportDTO>findAll(){
		return reportMapper.toDtoList(reportDAO.getAll());
	}
	
	public ReportDTO findById(Integer id) {
		return reportMapper.toDto(reportDAO.getById(id));
	}
	
	public Page<ReportDTO> findAllByFilter(ReportFilterDTO filter, PagingSorting pagingSorting) {
		List<Report> results = reportDAO.findAllByFilter(filter, pagingSorting);
		
		return new Page<ReportDTO>(results.stream()
						.map(p -> reportMapper.toDto(p))
						 .collect(Collectors.toList()), reportDAO.countAllByFilter(filter)
						 ,pagingSorting.getPageNumber(), pagingSorting.getPageSize());
	}
	
	public List<ReportDTO>getDailyReport(){
		return reportMapper.toDtoList(reportDAO.getDailyReport());
	}
	
	@Transactional
	public ReportDTO save(ReportDTO reportDTO) {
		Report newReport = reportMapper.toEntity(reportDTO);
		reportDAO.save(newReport);
		
		return reportMapper.toDto(newReport);
	}
	
	@Transactional
	public ReportDTO update(Integer id, ReportDTO report ) {
		Report updatedReport = reportDAO.findById(id);

		report.setId(id);
		updatedReport = reportDAO.merge(reportMapper.toEntity(report));
		return reportMapper.toDto(updatedReport);
	}
	
	@Transactional
	public boolean deleteById(Integer reportId) {
		return reportDAO.delete(reportId);
	}
}
