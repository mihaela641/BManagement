package bg.infosys.interns.bmanagement.core.dto;

import java.time.LocalDate;

public class EmployeeFilterDTO {
	
	private String name;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Integer position;
	private Integer country;
	private Double salary;
	private String shop;
	
	public EmployeeFilterDTO(String name, LocalDate dateFrom, LocalDate dateTo, Integer position, Integer country, Double salary,
			String shop) {
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.position = position;
		this.country = country;
		this.salary = salary;
		this.shop = shop;
	}

	public String getName() {
		return name;
	}

	public Integer getPosition() {
		return position;
	}

	public Integer getCountry() {
		return country;
	}

	public Double getSalary() {
		return salary;
	}

	public String getShop() {
		return shop;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}
	
}
