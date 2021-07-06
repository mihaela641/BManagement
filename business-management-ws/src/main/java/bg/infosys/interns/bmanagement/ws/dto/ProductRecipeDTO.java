package bg.infosys.interns.bmanagement.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a recipe in the application")
public class ProductRecipeDTO {
	@ApiModelProperty(
			  value = "Unique identifier of the Order",
			  name = "id",
			  dataType = "Integer",
			  example = "16",
			  required = true,
			  position = 0)
	private Integer id;
	
	@ApiModelProperty(
			  value = "Recipe of the productRecipe",
			  name = "recipe",
			  dataType = "RecipeDTO",
			  example = "firstRecipe",
			  required = true,
			  position = 1)
	private RecipeDTO recipe;
	
	@ApiModelProperty(
			  value = "Shop of the recipe",
			  name = "shop",
			  dataType = "ShopDTO",
			  example = "Shop",
			  required = true,
			  position = 2)
	private ShopDTO shop;
	
	@ApiModelProperty(
			value = "Delete Flag",
			name = "isDeleted",
			dataType = "Boolean",
			required = false,
			position = 4)
		private Boolean isDeleted;
	
	@ApiModelProperty(
			  value = "Product-Quantity of the recipe",
			  name = "productQuantity",
			  dataType = "Integer",
			  example = "2",
			  required = true,
			  position = 5)
	private Integer productQuantity;
	
	@ApiModelProperty(
			  value = "Product of the recipe",
			  name = "product",
			  dataType = "ProductDTO",
			  example = "Product",
			  required = true,
			  position = 3)
	private ProductDTO product;
	
	public ProductRecipeDTO() {}

	public ProductRecipeDTO(Integer id, RecipeDTO recipe, ShopDTO shop, Boolean isDeleted,
			Integer productQuantity , ProductDTO product) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.shop = shop;
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

	public RecipeDTO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDTO recipe) {
		this.recipe = recipe;
	}

	public ShopDTO getShop() {
		return shop;
	}

	public void setShop(ShopDTO shop) {
		this.shop = shop;
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
	
	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
}

	
