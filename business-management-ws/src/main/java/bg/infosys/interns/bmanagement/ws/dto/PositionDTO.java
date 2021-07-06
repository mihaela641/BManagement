package bg.infosys.interns.bmanagement.ws.dto;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = " ")
public class PositionDTO {
	@ApiModelProperty(
			value = "Unique identifier of the Position",
			name = "id",
			dataType = "Integer",
			example = "1",
			required = true,
			position = 0)
		private Integer id;

	 @ApiModelProperty(
			value = "Code of the Position",
			name = "code",
			dataType ="String",
			example = "",
			required = true,
			position = 1)
		@Size(max = 20, message = "Code length must be less than or equal to 20 characters")
		private  String code;
	 
	 @ApiModelProperty(
			value = "Name of the Position",
			name = "name",
			dataType ="String",
			example = "",
			required = true,
			position = 2)
		@Size(max = 20, message = "Name length must be less than or equal to 20 characters")
		private  String name;

	 @ApiModelProperty(
			value = "Description of the Position",
			name = "description",
			dataType ="String",
			example = "",
			required = true,
			position = 3)
		@Size(max = 100, message = "Name length must be less than or equal to 100 characters")
		private  String description;
	 
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 4)
	 private Boolean isDeleted;

		public PositionDTO() {}

		public PositionDTO(Integer id, String code, String name, String description, Boolean isDeleted) {
			this.id = id;
			this.code = code;
			this.name = name;
			this.description = description;
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

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}	
}
