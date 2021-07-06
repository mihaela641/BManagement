package bg.infosys.interns.bmanagement.ws.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a report in the application")
public class ReportDTO {
	@ApiModelProperty(
			  value = "Unique identifier of the Report",
			  name = "id",
			  dataType = "Long",
			  example = "1",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			  value = "Shop",
			  name = "shop",
			  dataType = "Shop",
			  required = true,
			  position = 1)
	private ShopDTO shop;
	
	@ApiModelProperty(
			  value = "Product",
			  name = "product",
			  dataType = "Product",
			  required = true,
			  position = 2)
	private ProductDTO product;
	
	@ApiModelProperty(
			  value = "Quantity of Product",
			  name = "quantity",
			  dataType = "Double",
			  example = "1.5",
			  required = true,
			  position = 3)
	private Double quantity;
	
	@ApiModelProperty(
			  value = "Date of the Report",
			  name = "date",
			  dataType = "LocalDate",
			  example = "2000-01-01",
			  required = true,
			  position = 4)
	private LocalDate date;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 5)
	 private Boolean isDeleted;

	public ReportDTO(Integer id, ShopDTO shop, ProductDTO product, Double quantity, LocalDate date, Boolean isDeleted) {
		super();
		this.id = id;
		this.shop = shop;
		this.product = product;
		this.quantity = quantity;
		this.date = date;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}


	public ShopDTO getShop() {
		return shop;
	}


	public ProductDTO getProduct() {
		return product;
	}


	public Double getQuantity() {
		return quantity;
	}


	public LocalDate getDate() {
		return date;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setShop(ShopDTO shop) {
		this.shop = shop;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Report id: %d%n", this.id));
		sb.append(String.format("Shop id: %d%n", this.shop.getId()));
		sb.append(String.format("Product id: %d%n", this.product.getId()));
		sb.append(String.format("Quantity: %.2f%n", this.quantity));
		sb.append(String.format("Date: %s", this.date.toString()));
		return sb.toString().trim();
	}
}
