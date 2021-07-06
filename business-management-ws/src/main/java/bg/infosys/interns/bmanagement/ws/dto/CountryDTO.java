package bg.infosys.interns.bmanagement.ws.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a country in the application")
public class CountryDTO {
	@ApiModelProperty(value = "Unique string identifier of the Country", name = "id", dataType = "Short", example = "1", required = true, position = 0)
	@NotBlank(message = "id cannot be blank")
	private Short id;

	@ApiModelProperty(value = "Code of the Country", name = "code", dataType = "String", example = "+357", required = true, position = 1)
	@Size(max = 15, message = "Code length must be less than or equal to 15 characters")
	private String code;

	@ApiModelProperty(value = "Name of the Country", name = "name", dataType = "String", example = "Bulgaria", required = true, position = 2)
	@Size(max = 20, message = "Full name length must be less than or equal to 20 characters")
	private String name;

	public CountryDTO() {
	}

	public CountryDTO(short id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Short getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
