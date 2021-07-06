package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a recipes in the application")
public class RecipeDTO {
	@ApiModelProperty(
			  value = "Unique identifier of the Order",
			  name = "id",
			  dataType = "Integer",
			  example = "16",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			value = "Description of the Recipe",
			name = "Recipe",
			dataType = "String",
			example = "Recipe description",
		    required = true,
		    position = 1)
	private String description;
	
	@ApiModelProperty(
			value = "Name of the Recipe",
			name = "name",
			dataType = "String",
			example = "Recipe name",
		    required = true,
		    position = 3)
	private String name;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 4)
	 private Boolean isDeleted;

	public RecipeDTO() {}
	
	public RecipeDTO(Integer id, String description,String name,Boolean isDeleted) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.isDeleted = isDeleted;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}


