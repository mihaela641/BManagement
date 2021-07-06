package bg.infosys.interns.bmanagement.ws.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;

public class EmployeeDTO {
	@ApiModelProperty(
			value = "Unique string identifier of the Employee",
			name = "id",
			dataType = "Integer",
			example = "1",
			required = true,
			position = 0)
	private Integer id;
	
	@ApiModelProperty(
			value = "Name of the Employee",
			name = "name",
			dataType = "String",
			example = "George",
			required = true,
			position = 1)
	private String name;
	
	@ApiModelProperty(
			value = "Birthdate of the Employee",
			name = "birthDate",
			dataType = "LocalDate",
			example = "2000-01-01",
			required = true,
			position = 2)
	private LocalDate birthDate;
	
	@ApiModelProperty(
			value = "Position of the Employee",
			name = "position",
			dataType = "PositionDTO",
			required = true,
			position = 3)
	private PositionDTO position;
	
	@ApiModelProperty(
			value = "Country of the Employee",
			name = "country",
			dataType = "CountryDTO",
			required = true,
			position = 4)
	private CountryDTO country;
	
	@ApiModelProperty(
			value = "Salary of the Employee",
			name = "salary",
			dataType = "Double",
			example = "1000.50",
			required = true,
			position = 5)
	private Double salary;
	
	@ApiModelProperty(
			value = "Working place of the Employee",
			name = "shop",
			dataType = "ShopDTO",
			required = true,
			position = 6)
	private ShopDTO shop;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 8)
	 private Boolean isDeleted;
	
	public EmployeeDTO() {}

	public EmployeeDTO(Integer id, String name, LocalDate birthDate, PositionDTO position, CountryDTO country, Double salary,
			ShopDTO shop, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.position = position;
		this.country = country;
		this.salary = salary;
		this.shop = shop;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public Double getSalary() {
		return salary;
	}

	public ShopDTO getShop() {
		return shop;
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

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setShop(ShopDTO shop) {
		this.shop = shop;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
