package bg.infosys.interns.bmanagement.core.dto;

public class OrderProductFilterDTO {
	private Integer order;
	private Integer product;
	
	public OrderProductFilterDTO(Integer order, Integer product) {
		this.order = order;
		this.product = product;
	}

	public Integer getOrder() {
		return order;
	}


	public Integer getProduct() {
		return product;
	}

}
