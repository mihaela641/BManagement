package bg.infosys.interns.bmanagement.core.dto;

public class TagFilterDTO {
	
	private String code;
	private String name;
	
	public TagFilterDTO (String code , String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	

}
