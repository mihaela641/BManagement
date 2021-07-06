package bg.infosys.interns.bmanagement.core.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "reports")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	private Shop shop;
	public static final String _shop = "shop";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	public static final String _product = "product";
	
	@Column(name = "quantity")
	private Double quantity;
	public static final String _quantity = "quantity";

	@Column(name = "date")
	private LocalDate date;
	public static final String _date = "date";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Report(Integer id, Shop shop, Product product, Double quantity, LocalDate date, Boolean isDeleted) {
		super();
		this.id = id;
		this.shop = shop;
		this.product = product;
		this.quantity = quantity;
		this.date = date;
		this.isDeleted = isDeleted;
	}
	
	public Report() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Report))
			return false;
		Report other = (Report) obj;
		return Objects.equals(id, other.getId());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Report id: %d%n", this.id));
		sb.append(String.format("Shop id: %d%n",this.shop.getId()));
		sb.append(String.format("Product id: %d%n",this.product.getId()));
		sb.append(String.format("Quantity: %d%n",this.quantity));
		sb.append(String.format("Date: %s",this.date.toString()));
		
		return sb.toString().trim();
	}
}
