package bg.infosys.interns.bmanagement.core.dto;

public class AddressFilterDTO {
	 private String street;
     private String country;
    public AddressFilterDTO(String street, String country) {
        super();
        this.street = street;
        this.country = country;
    }
   
    public String getStreet() {
        return street;
    }
   
    public String getCountry() {
        return country;
    }
}
