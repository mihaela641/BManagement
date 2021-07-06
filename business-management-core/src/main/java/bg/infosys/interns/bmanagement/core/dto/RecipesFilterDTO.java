package bg.infosys.interns.bmanagement.core.dto;

public class RecipesFilterDTO {

	private String name ;
	private String product;
	private String description;
	
	public RecipesFilterDTO(String name, String product,String description) {
		this.name = name;
		this.product = product;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getProduct() {
		return product;
	}

	public String getDescription() {
		return description;
	}	
}
