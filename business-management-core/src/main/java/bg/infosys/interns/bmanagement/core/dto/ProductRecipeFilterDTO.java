package bg.infosys.interns.bmanagement.core.dto;

public class ProductRecipeFilterDTO {
	
	private String recipe;
	private String shop;
	private Integer productQuantity;
	private String product;
	
	
	public ProductRecipeFilterDTO(String recipe, String shop,Integer productQuantity,String product) {
		this.recipe = recipe;
		this.shop = shop;
		this.productQuantity = productQuantity;
		this.product = product;
	}


	public String getRecipe() {
		return recipe;
	}

	public String getShop() {
		return shop;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}	
	
	public String getProduct() {
		return product;
	}

}
