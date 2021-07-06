package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a productShop in the application")
public class ProductShopDTO {
	
	@ApiModelProperty(
			  value = "Unique identifier of the ProductShop",
			  name = "id",
			  dataType = "Integer",
			  example = "1806",
			  required = true,
			  position = 0)
    private Integer id;
	
	@ApiModelProperty(
			  value = "Product in the shop",
			  name = "product",
			  dataType = "ProductDTO",
			  required = true,
			  position = 1)
    private ProductDTO product;
	
	@ApiModelProperty(
			  value = "Shop with product",
			  name = "shop",
			  dataType = "ShopDTO",
			  required = true,
			  position = 2)
    private ShopDTO shop;
	
	@ApiModelProperty(
			  value = "Quantity of the Product in the Shop",
			  name = "quantity",
			  dataType = "double",
			  example = "5.3",
			  position = 3)
    private double quantity;
	
	@ApiModelProperty(
			  value = "delete flag",
			  name = "isDeleted",
			  dataType = "Boolean",
			  example = "true",
			  position = 4)
	private Boolean isDeleted;
    
	public ProductShopDTO() {}

	public ProductShopDTO(Integer id, ProductDTO product, ShopDTO shop, double quantity, Boolean isDeleted) {
		this.id = id;
		this.product = product;
		this.shop = shop;
		this.quantity = quantity;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public ShopDTO getShop() {
		return shop;
	}

	public void setShop(ShopDTO shop) {
		this.shop = shop;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
