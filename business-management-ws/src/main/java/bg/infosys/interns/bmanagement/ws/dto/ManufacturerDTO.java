package bg.infosys.interns.bmanagement.ws.dto;

import java.time.LocalDate;
//import java.time.LocalDateTime;

//import javax.validation.constraints.Past;
//import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonFormat;

//import bg.infosys.common.utils.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = " ")
public class ManufacturerDTO {
	@ApiModelProperty(
			value = "Unique identifier of the Manufacturer",
			name = "id",
			dataType = "Integer",
			example = "",
			required = true,
			position = 0)
	private Integer id;
	
	@ApiModelProperty(
			value = "Name of the Manufacturer",
			name = "name",
			dataType = "String",
			example = "",
			required = true,
			position = 1)
	@Size(max = 20, message = "Name length must be less than or equal to 20 characters")
	private String name;

	@ApiModelProperty(
			value = "Connection to AddressDTO",
			name = "address",
			dataType = "AddressDTO",
			required = false,
			position = 2)
		private AddressDTO address;
	
	@ApiModelProperty(
			value = "Date of foundation",
			name = "foundationDate",
			dataType = "LocalDate",
			example = "",
			required = true,
			position = 3)
	private LocalDate foundationDate;
	
	@ApiModelProperty(
			value = "Description of the Manufacturer",
			name = "description",
			dataType = "String",
			example = "Manufacturer description",
		    required = true,
		    position = 4)
	@Size(max = 100, message = "Description length must be less than or equal to 100 characters")
	private String description;
	
	/* manufacturer OneToMany */
	/* @ApiModelProperty(
			value = "Connection to ProductDTO",
			name = "product",
			dataType = "ProductDTO",
			required = false,
			position = 5)
		private ProductDTO product;  */
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 5)
	 private Boolean isDeleted;
	
   public ManufacturerDTO(){}
	
	public ManufacturerDTO(Integer id, String name, LocalDate foundationDate, String description, AddressDTO address, Boolean isDeleted){
		this.id = id;
		this.name = name;
		this.address = address;
		this.foundationDate = foundationDate;
		this.description = description;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public LocalDate getFoundationDate() {
		return foundationDate;
	}

	public String getDescription() {
		return description;
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

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


/*	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	 
		*/
}
