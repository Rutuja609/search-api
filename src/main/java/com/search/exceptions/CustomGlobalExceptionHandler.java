package com.search.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import com.search.beans.Error;
import static com.search.constants.ErrorConstants.CODE_INTERNAL_SERVER_ERROR;
import static com.search.constants.ErrorConstants.SUGGESTED_ACTION_INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Constructs a new runtime exception with the specified detail
     * code, message, status, suggested action.
     * @param NegativeValueException  the detail message.
	 */
	
	@ExceptionHandler(NegativeValueException.class)
	public ResponseEntity<Error> handleCustomBadInputException(NegativeValueException ex) {
		log.error("Negative Value Exception: ", ex);
		Error error = new Error();
		error.setCode(CODE_INTERNAL_SERVER_ERROR);
		error.setIssue(ex.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setSuggestedAction(SUGGESTED_ACTION_INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}