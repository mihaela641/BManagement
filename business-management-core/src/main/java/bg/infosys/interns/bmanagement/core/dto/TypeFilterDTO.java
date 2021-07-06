package bg.infosys.interns.bmanagement.core.dto;


public class TypeFilterDTO {
	
	private String name;
	private String code;
	private String parentType;
	//private Type subType;
	
	public TypeFilterDTO(String name, String code,String parentType) {
		this.code = code;
		this.name = name;
		this.parentType = parentType;
		//this.subType = subType;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getParentType() {
		return parentType;
	}

	/*public Type getSubType() {
		return subType;
	} */
}
