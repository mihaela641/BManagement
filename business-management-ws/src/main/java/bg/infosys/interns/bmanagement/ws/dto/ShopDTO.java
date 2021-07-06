package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a shop in the application")
public class ShopDTO {
	@ApiModelProperty(
			  value = "Unique identifier of the Shop",
			  name = "id",
			  dataType = "Integer",
			  example = "1806",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			  value = "Name of the Shop",
			  name = "name",
			  dataType = "String",
			  example = "FF",
			  required = true,
			  position = 1)
	private String name;
	
	@ApiModelProperty(
			  value = "Address of the Shop",
			  name = "address",
			  dataType = "AddressDTO",
			  required = true,
			  position = 2)
	private AddressDTO address;
	
	@ApiModelProperty(
			  value = "PhoneNumber of the Shop",
			  name = "phoneNumber",
			  dataType = "String",
			  example = "+359878445880",
			  required = true,
			  position = 3)
	private String phoneNumber;
	
	@ApiModelProperty(
			  value = "Status of the Shop",
			  name = "status",
			  dataType = "Integer",
			  required = true,
			  example = "1",
			  position = 5)
	private Integer status;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 6)
	 private Boolean isDeleted;
	
	public ShopDTO() {}

	public ShopDTO(Integer id, String name, AddressDTO address, String phoneNumber, Integer status, Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.status = status;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Integer getStatus() {
		return status;
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

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
