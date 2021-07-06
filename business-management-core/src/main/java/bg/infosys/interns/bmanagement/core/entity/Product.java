package bg.infosys.interns.bmanagement.core.entity;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "public",name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", nullable = false)
	private String name;
	public static final String _name = "name";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="type_id",referencedColumnName="id", nullable = false)
	private Type type;
	public static final String _type = "type";
	
	@Column(name="price", nullable = false)
	private Double price;
	public static final String _price = "price";
	
	@Column(name="date_added", nullable = false)
	private LocalDate dateAdded;
	public static final String _dateAdded = "dateAdded";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="manufacturer_id",referencedColumnName="id", nullable = false)
	private Manufacturer manufacturer;
	public static final String _manufacturer="manufacturer";
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "products_tags",joinColumns = @JoinColumn(name = "product_id"), 
			  inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	public static final String _tags = "tags";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Product() {}

	public Product(Integer id, String name, Type type, Double price, LocalDate dateAdded, Manufacturer manufacturer, List<Tag> tags,Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.dateAdded = dateAdded;
		this.manufacturer = manufacturer;
		this.tags = tags;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public LocalDate getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}