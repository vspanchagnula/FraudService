package com.fraud.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice  extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(FraudException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Object> handleException(
			FraudException ex, WebRequest request) {

	    Map<String, Object> body = new LinkedHashMap<>();
	    
	    body.put("message", ex.getMessage());
	    return (ResponseEntity<Object>) body;
	}
	

}
