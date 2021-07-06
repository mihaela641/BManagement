package bg.infosys.interns.bmanagement.ws.controllers.error;

public enum APIErrorCodes {
	METHOD_ARG_NOT_VALID 		("EX-400-1", "Method argument not valid", 	"Invalid arguments are passed to the API endpoint!"),
	
	UNKNOWN_SERVER_EXCEPTION	("EX-500-1", "Unknown server exception", 	"An unknown server error has occurred!");
	
	private String code;
	private String message;
	private String description;
	
	private APIErrorCodes(String code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDescription() {
		return description;
	}
}