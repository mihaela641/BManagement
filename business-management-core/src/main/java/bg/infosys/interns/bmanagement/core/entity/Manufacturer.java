																																																																														package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import java.time.LocalDate;

import java.util.Objects;

@Entity
@Table(schema = "public", name = "manufacturers")
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "name", nullable = false, length = 20)
	private String name;
	public static final String _name = "name";
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="address_id",referencedColumnName="id", nullable = false)
	private Address address;
	public static final String _address = "address";
	
	@Column(name="foundation_date", nullable = false)
	private LocalDate foundationDate;
	public static final String _foundationDate = "foundationDate";

	@Column (name = "description", nullable = true, length = 100)
	private String description;
	public static final String _description = "description";
	
/*	@OneToMany(mappedBy = "manufacturer")
	private List<Product>products;
	public static final String _products =  "products"; */
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Manufacturer() {}

	public Manufacturer(Integer id, String name, LocalDate foundationDate, String description,Address address, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.foundationDate = foundationDate;
		this.description = description;
		this.address = address;
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

	public LocalDate getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		if (!(obj instanceof Manufacturer))
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(id, other.getId());
	}
}