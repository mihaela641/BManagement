package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

import java.util.Objects;

@Entity
@Table(schema = "public", name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "street" , nullable = false, length = 150)
	private String street;
	public static final String _street = "street";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "country_id" ,  nullable = false)
	private Country country;
	public static final String _country = "country";
	
	@Column(name = "is_deleted", nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Address() {}
	
	public Address(Integer id, String street, Country country, Boolean isDeleted) {
		super();
		this.id = id;
		this.street = street;
		this.country = country;
		this.isDeleted= isDeleted;
		}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.getId());
	}
}