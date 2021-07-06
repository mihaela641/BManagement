package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a orderProduct in the application")
public class OrderProductDTO {

	@ApiModelProperty(
			  value = "Unique identifier of the OrderProduct",
			  name = "id",
			  dataType = "Integer",
			  example = "13",
			  required = true,
			  position = 0)
  private Integer id;
	
	@ApiModelProperty(
			  value = "Order",
			  name = "odrer",
			  dataType = "OrderDTO",
			  required = true,
			  position = 1)
  private OrderDTO order;
	
	@ApiModelProperty(
			  value = "Product in the order",
			  name = "product",
			  dataType = "ProductDTO",
			  required = true,
			  position = 2)
  private ProductDTO product;

	public OrderProductDTO() {}
	
	public OrderProductDTO(Integer id, OrderDTO order, ProductDTO product) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
	
	
	
}
