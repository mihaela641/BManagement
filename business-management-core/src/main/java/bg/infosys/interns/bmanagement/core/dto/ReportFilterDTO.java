package bg.infosys.interns.bmanagement.core.dto;

import java.time.LocalDate;

public class ReportFilterDTO {

	private Integer shop;
	private Integer product;
	private Double quantity;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	
	public ReportFilterDTO(Integer shop, Integer product, Double quantity, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.shop = shop;
		this.product = product;
		this.quantity = quantity;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Integer getShop() {
		return shop;
	}

	public Integer getProduct() {
		return product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}
	
}
