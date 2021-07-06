package bg.infosys.interns.bmanagement.core.entity;

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
@Table(schema = "public",name="products_recipes")
public class ProductRecipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shop_id",referencedColumnName="id", nullable = false)
	private Shop shop;
	public static final String _shop = "shop";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recipes_id",referencedColumnName="id", nullable = false)
	private Recipes recipe;
	public static final String _recipe = "recipe";
	
	@Column(name = "is_deleted",columnDefinition = "boolean default false",nullable = false)
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	@Column(name="product_quantity", nullable = false)
	private Integer productQuantity;
	public static final String _productQuantity="productQuantity";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id",referencedColumnName="id", nullable = false)
	private Product product;
	public static final String _product = "product";
	
	public ProductRecipe() {}
	
	public ProductRecipe(Integer id, Shop shop, Recipes recipe, Boolean isDeleted, Integer productQuantity,Product product) {
		super();
		this.id = id;
		this.shop = shop;
		this.recipe = recipe;
		this.isDeleted = isDeleted;
		this.productQuantity = productQuantity;
		this.product = product;
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

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	
}

