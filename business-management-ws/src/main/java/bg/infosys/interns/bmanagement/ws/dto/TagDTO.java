package bg.infosys.interns.bmanagement.ws.dto;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Tags")
public class TagDTO {
	@ApiModelProperty(
			value = "Unique string identifier of the Address",
			name = "id",
			dataType = "Integer",
			example = "1",
			required = true,
			position = 0)
	private Integer id;
	
	@ApiModelProperty(
			value = "Code of the Tags",
			name = "code",
			dataType = "String",
			example = "111ee2",
			required = true,
			position = 1)
	@Size(max = 10, message = "Code length must be less than or equal to 10 characters")
	private String code;
	
	@ApiModelProperty(
			  value = "Tags name",
			  name = "name",
			  dataType = "String",
			  example = "firstTag1",
			  required = true,
			  position = 2)
	@Size(max = 50, message = "Name length must be less than or equal to 50 characters")
	private String name;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 3)
	 private Boolean isDeleted;
	
	public TagDTO() {}

	public TagDTO(Integer id, String code, String name, Boolean isDeleted) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.isDeleted= isDeleted;
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

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
