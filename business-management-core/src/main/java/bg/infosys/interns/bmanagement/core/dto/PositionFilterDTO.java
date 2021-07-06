package bg.infosys.interns.bmanagement.core.dto;

public class PositionFilterDTO {
		
	private String name ;
	private String code;
	
	public PositionFilterDTO (String name,String code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}
}
