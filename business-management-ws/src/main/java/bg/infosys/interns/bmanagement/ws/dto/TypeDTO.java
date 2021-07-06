package bg.infosys.interns.bmanagement.ws.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import bg.infosys.interns.bmanagement.core.entity.Type;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 @ApiModel(description = " ")
public class TypeDTO {

	 @ApiModelProperty(
			value = "Unique identifier of the Type",
			name = "id",
			dataType = "Integer",
			example = "1",
			required = true,
			position = 0) 
	private Integer id;


	@ApiModelProperty(
			value = "Unique code of the Type",
			name = "code",
			dataType = "String",
			example = "",
			required = true,
			position = 1)
	@Size(max = 20, message = "Code length must be less than or equal to 20 characters")
	private String code;


			@ApiModelProperty(
			  value = "Type name",
			  name = "name",
			  dataType = "String",
			  example = "",
			  required = true,
			  position = 2)
	@Size(max = 50, message = "Name length must be less than or equal to 50 characters") 
	private String name;
	
 	@ApiModelProperty(
			  value = "Description of the Type",
			  name = "description",
			  dataType = "String",
			  example = "Description Type",
			  required = true,
			  position = 3)
	@Size(max = 100, message = "Name length must be less than or equal to 100 characters") 
	private String description;

		@ApiModelProperty(
			  value = "Parent Types connected to Type ",
			  name = "parentType",
			  dataType = "Type",
			  example = "",
			  required = false,
			  position = 4)
	private Type parentType;
		
	 @ApiModelProperty(
			  value = "Sub Types connected to Type",
			  name = "subTypes",
			  dataType = "List<Type>",
			  example = "",
			  required = false,
			  position = 5)
	private List<TypeDTO> subTypes;
	 
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 6)
	 private Boolean isDeleted;

	public TypeDTO() {
	}
	
	public TypeDTO (Integer id,String code, String name, String description, Type parentType,Boolean isDeleted) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.subTypes = new ArrayList<>();
		this.parentType = parentType;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Type getParentType() {
		return parentType;
	}

	public List<TypeDTO> getSubTypes() {
		return subTypes;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setParentType(Type parentType) {
		this.parentType = parentType;
	}

	public void setSubTypes(List<TypeDTO> subTypes) {
		this.subTypes = subTypes;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	} 
}
