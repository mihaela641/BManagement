package bg.infosys.interns.bmanagement.ws.controllers.handler;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import bg.infosys.interns.bmanagement.ws.controllers.error.APIError;
import bg.infosys.interns.bmanagement.ws.controllers.error.APIErrorCodes;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	/*private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	private static final String STACK_TRACE_LOG = "Stack trace: ";

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<APIError> handleException(Exception ex) {
		LOGGER.error(STACK_TRACE_LOG, ex);
		APIErrorCodes code = APIErrorCodes.UNKNOWN_SERVER_EXCEPTION;
		return ResponseEntity.status(500)
				 			 .body(new APIError(code.getCode(), code.getMessage(), code.getDescription()));
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<APIError> handleException(MethodArgumentNotValidException ex) {
		LOGGER.error(STACK_TRACE_LOG, ex);
		APIErrorCodes code = APIErrorCodes.METHOD_ARG_NOT_VALID;
		return ResponseEntity.status(400)
				 			 .body(new APIError(code.getCode(), code.getMessage(), code.getDescription()));
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<APIError> handleException(HttpMessageNotReadableException ex) {
		LOGGER.error(STACK_TRACE_LOG, ex);
		APIErrorCodes code = APIErrorCodes.METHOD_ARG_NOT_VALID;
		return ResponseEntity.status(400)
	 			 			 .body(new APIError(code.getCode(), code.getMessage(), code.getDescription()));
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Error> handleException(EntityNotFoundException ex) {
		LOGGER.error(STACK_TRACE_LOG, ex);
		return ResponseEntity.notFound().build();
	}
	*/
}
