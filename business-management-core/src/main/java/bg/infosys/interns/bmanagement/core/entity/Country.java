package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import java.util.Objects;

@Entity
@Table(schema = "public", name = "countries")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "code", nullable = false, length = 15,unique = true)
	private String code;
	public static final String _code = "code";

	@Column(name = "name", nullable = false, length = 20)
	private String name;
	public static final String _name = "name";

	public Country() {}
	
	public Country (Short id, String code , String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
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
		if (!(obj instanceof Country))
			return false;
		Country other = (Country) obj;
		return Objects.equals(id, other.getId());
	}
}