package bg.infosys.interns.bmanagement.core.dto;

import java.time.LocalDate;

public class ManufacturerFilterDTO {
    private String name;
    private String address;
    //private LocalDate dateFrom;
	//private LocalDate dateTo;
    private LocalDate foundationDate;
	
    public ManufacturerFilterDTO(String name, String address , LocalDate foundationDate) {
		this.name = name;
		this.address = address;
		this.foundationDate= foundationDate;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public LocalDate getFoundationDate() {
		return foundationDate;
	}
    
}
