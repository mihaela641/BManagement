package bg.infosys.interns.bmanagement.ws.dto;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = " ")
public class AddressDTO {
	@ApiModelProperty(
			value = "Unique string identifier of the Address",
			name = "street",
			dataType = "Integer",
			example = "1",
			required = true,
			position = 0)
	private Integer id;

	 @ApiModelProperty (
			value = "Street name",
			name = "street",
			dataType = "String",
			example = "Gentle Rain Drive 45",
			required = true,
			position = 1)
	@Size(max = 150, message = "Street name length must be less than or equal to 150 characters")
	private String street;


	 @ApiModelProperty(
			value = "Connection to CountryDTO",
			name = "country",
			dataType = "CountryDTO",
			required = false,
			position = 2)
	private CountryDTO country;
	 
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 3)
	 private Boolean isDeleted;

	public AddressDTO() {}

	public AddressDTO(Integer id , String street , CountryDTO country, Boolean isDeleted) {
	this.id = id;
	this.street = street;
	this.country = country;
	this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
