package bg.infosys.interns.bmanagement.core.dto;

public class ShopFilterDTO {
	private String name;
	private String address;
	private String phoneNumber;
	private Integer status;
	
	public ShopFilterDTO(String name, String address, String phoneNumber, Integer status) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Integer getStatus() {
		return status;
	}
	
}
