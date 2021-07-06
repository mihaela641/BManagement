package bg.infosys.interns.bmanagement.core.dto;

public class ProductShopFilterDTO {
	private String product;
	private String shop;
	private Double quantity;
	
	public ProductShopFilterDTO(String product, String shop, Double quantity) {
		this.product = product;
		this.shop = shop;
		this.quantity = quantity;
	}

	public String getProduct() {
		return product;
	}

	public String getShop() {
		return shop;
	}

	public Double getQuantity() {
		return quantity;
	}
	
}
