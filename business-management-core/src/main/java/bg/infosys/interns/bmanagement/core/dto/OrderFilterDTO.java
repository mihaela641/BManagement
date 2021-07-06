package bg.infosys.interns.bmanagement.core.dto;

public class OrderFilterDTO {
	
	private String code;
	private String product;
	private String shop;
	private Integer quantity;
	private Integer status;
	
	public OrderFilterDTO(String code, String product, String shop, Integer quantity, Integer status) {
		this.code = code;
		this.product = product;
		this.shop = shop;
		this.quantity = quantity;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public String getProduct() {
		return product;
	}

	public String getShop() {
		return shop;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getStatus() {
		return status;
	}
	
	
	
	
}
