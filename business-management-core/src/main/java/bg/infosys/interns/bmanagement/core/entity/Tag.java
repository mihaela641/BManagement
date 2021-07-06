package bg.infosys.interns.bmanagement.core.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(schema="public",name="tags")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="code", nullable = false, length = 10,unique = true)
	private String code;
	public static final String _code = "code";

	@Column(name="name",nullable = false)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Tag() {}
	
	public Tag(Integer id, String code, String name, Boolean isDeleted) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.isDeleted = isDeleted;
	}

	/* @ManyToMany(mappedBy = "tags")
	private List<Product>products; */

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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/* public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	} */

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tag))
			return false;
		Tag other = (Tag) obj;
		return Objects.equals(id, other.getId());
	}
}