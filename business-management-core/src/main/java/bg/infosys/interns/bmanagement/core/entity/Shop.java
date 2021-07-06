package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema="public",name="shops")
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name",nullable = false)
	private String name;
	public static final String _name="name";
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", nullable = false)
	private Address address;
	public static final String _address="address";
	
	@Column(name="phone_number",nullable = false, length = 15)
	private String phoneNumber;
	public static final String _phoneNumber="phoneNumber";
	
	@Column(name="status",nullable = false)
	private Integer status;
	public static final String _status="status";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Shop() {}

	public Shop(Integer id, String name, Address address, String phoneNumber, Integer status, Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.status = status;
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
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}