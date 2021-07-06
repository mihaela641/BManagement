package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a order in the application")
public class OrderDTO {
	@ApiModelProperty(
			  value = "Unique identifier of the Order",
			  name = "id",
			  dataType = "Integer",
			  example = "16",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			  value = "Code of the order",
			  name = "code",
			  dataType = "tring",
			  example = "12G5",
			  required = true,
			  position = 1)
	private String code;
	
	@ApiModelProperty(
			  value = "Product of the order",
			  name = "product",
			  dataType = "ProductDTO",
			  example = "chips",
			  required = true,
			  position = 2)
	private ProductDTO product;
	
	@ApiModelProperty(
			  value = "Shop of the order",
			  name = "shop",
			  dataType = "ShopDTO",
			  example = "shop22",
			  required = true,
			  position = 3)
	private ShopDTO shop;
	
	@ApiModelProperty(
			  value = "Quantity of the order",
			  name = "quantity",
			  dataType = "Integer",
			  required = true,
			  example = "1",
			  position = 4)
	private Integer quantity;
	
	@ApiModelProperty(
			  value = "Status of the order",
			  name = "status",
			  dataType = "Integer",
			  required = true,
			  example = "1",
			  position = 5)
	private Integer status;
	
	 @ApiModelProperty(
				value = "Delete Flag",
				name = "isDeleted",
				dataType = "Boolean",
				required = false,
				position = 6)
	 private Boolean isDeleted;
	
	public OrderDTO() {}

	public OrderDTO(Integer id, String code, ProductDTO product, ShopDTO shop, Integer quantity, Integer status,
			Boolean isDeleted) {
		super();
		this.id = id;
		this.code = code;
		this.product = product;
		this.shop = shop;
		this.quantity = quantity;
		this.status = status;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
