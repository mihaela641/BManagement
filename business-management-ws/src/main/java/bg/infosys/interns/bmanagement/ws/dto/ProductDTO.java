package bg.infosys.interns.bmanagement.ws.dto;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a product in the application")
public class ProductDTO {
	
	@ApiModelProperty(
			  value = "Unique identifier of the Product",
			  name = "id",
			  dataType = "Integer",
			  example = "1806",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			  value = "Name of the product",
			  name = "name",
			  dataType = "String",
			  example = "Pepsi",
			  required = true,
			  position = 1)
	private String name;
	
	@ApiModelProperty(
			  value = "Type of the product",
			  name = "type",
			  dataType = "TypeDTO",
			  example = "drink",
			  required = true,
			  position = 2)
	private TypeDTO type;
	
	@ApiModelProperty(
			  value = "Price of the product",
			  name = "price",
			  dataType = "Double",
			  example = "5.99",
			  required = true,
			  position = 3)
	private Double price;
	
	@ApiModelProperty(
			  value = "Date when product added",
			  name = "dateAdded",
			  dataType = "LocalDate",
			  example = "2000-01-01",
			  required = true,
			  position = 4)
	private LocalDate dateAdded;
	
	@ApiModelProperty(
			  value = "Manufacturer of the product",
			  name = "manufacturer",
			  dataType = "ManufacturerDTO",
			  required = true,
			  position = 5)
	private ManufacturerDTO manufacturer;
	
	@ApiModelProperty(
			  value = "Tags of the product",
			  name = "id",
			  dataType = "List<TagDTO>",
			  required = true,
			  position = 7)
	private List<TagDTO> tags;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 8)
	 private Boolean isDeleted;

	public ProductDTO() {}

	public ProductDTO(Integer id, String name, TypeDTO type, Double price, LocalDate dateAdded,
			ManufacturerDTO manufacturer, List<TagDTO> tags, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.dateAdded = dateAdded;
		this.manufacturer = manufacturer;
		this.tags = tags;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TypeDTO getType() {
		return type;
	}

	public Double getPrice() {
		return price;
	}

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public ManufacturerDTO getManufacturer() {
		return manufacturer;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setManufacturer(ManufacturerDTO manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
  
}