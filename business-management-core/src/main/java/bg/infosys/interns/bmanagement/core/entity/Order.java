package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "public",name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@Column(name="code",nullable = false, length = 20)
	private String code;
	public static final String _code="code";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id",referencedColumnName="id", nullable = false)
	private Product product;
	public static final String _product = "product";
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shop_id",referencedColumnName="id", nullable = false)
	private Shop shop;
	public static final String _shop = "shop";
	
	@Column(name="quantity",nullable = false)
	private Integer quantity;
	public static final String _quantity="quantity";
	
	@Column(name="status",nullable = false)
	private Integer status;
	public static final String _status="status";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
		
	public Order() {}

	public Order(Integer id, String code, Product product, Shop shop, Integer quantity, Integer status,
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
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
