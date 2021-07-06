package bg.infosys.interns.bmanagement.ws.cron;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bg.infosys.interns.bmanagement.ws.dto.ReportDTO;
import bg.infosys.interns.bmanagement.ws.service.ReportService;

@Component
@EnableScheduling
public class ScheduledTasks {
	
	private ReportService reportService;
	
	public ScheduledTasks(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@Scheduled(cron = "0 59 23 * * MON-FRI")
	public void generateReport() throws InterruptedException{
		List<ReportDTO>reports=reportService.getDailyReport();
		
		for(int i =0; i<reports.size();i++) {
			System.out.println(reports.get(i).toString());
			System.out.println("#############################################");
		}
	}
}