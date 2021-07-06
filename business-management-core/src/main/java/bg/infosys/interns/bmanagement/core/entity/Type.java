package bg.infosys.interns.bmanagement.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "types")
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "code", nullable = false, length = 20,unique = true)
	private String code;
	public static final String _code = "code";
	
	@Column (name = "name", nullable = false, length = 50)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_deleted",nullable = false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
	public static final String _isDeleted = "isDeleted";
	
	public Type () {}
	
	public Type(Integer id, String code, String name, String description, Type parentType, Boolean isDeleted) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.parentType = parentType;
		this.isDeleted = isDeleted;
	}

	@Column (name = "description", nullable = true, length = 100)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "main_type_id" , nullable = true)
	private Type parentType;
	public static final String _parentType = "parentType";
	
	@OneToMany(mappedBy = "parentType")
	private List<Type> subTypes;
	public static final String _subTypes = "subTypes";
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Type getParentType() {
		return parentType;
	}

	public void setParentType(Type parentType) {
		this.parentType = parentType;
	}

	public List<Type> SubTypes() {
		return subTypes;
	}

	public void ListSubTypes(List<Type> subTypes) {
		this.subTypes = subTypes;
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
		if (!(obj instanceof Type))
			return false;
		Type other = (Type) obj;
		return Objects.equals(id, other.getId());
	}
}