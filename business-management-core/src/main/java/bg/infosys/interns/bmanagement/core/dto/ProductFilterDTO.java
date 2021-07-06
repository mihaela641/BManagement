package bg.infosys.interns.bmanagement.core.dto;

import java.time.LocalDate;

public class ProductFilterDTO {

	private String name;
	private Integer type;
	private Double price;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Integer manufacturer;
	
	public ProductFilterDTO(String name, Integer type, Double price, LocalDate dateFrom, LocalDate dateTo,
			Integer manufacturer) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public Integer getType() {
		return type;
	}

	public Double getPrice() {
		return price;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public Integer getManufacturer() {
		return manufacturer;
	}	
}
