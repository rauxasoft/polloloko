package com.sinensia.polloloko.presentation.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PresentationException.class)
	protected ResponseEntity<?> presentationExceptionHandler(PresentationException ex, WebRequest webRequest){
		
		// TODO Log
		
		return handleExceptionInternal(ex, new MensajeError(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus(), webRequest);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<?> argumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex, WebRequest webRequest){
		
		String mensaje = "El parámetro " + ex.getPropertyName() + " ha de ser del tipo " + ex.getRequiredType().getSimpleName();
		
		// TODO Log
		
		return handleExceptionInternal(ex, new MensajeError(mensaje), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> genericExceptionHandler(Exception ex, WebRequest webRequest){
		
		// TODO Log
		
		return handleExceptionInternal(ex, new MensajeError("Server error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
}
