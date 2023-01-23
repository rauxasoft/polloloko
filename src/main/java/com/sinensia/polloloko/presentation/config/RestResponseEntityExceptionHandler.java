package com.sinensia.polloloko.presentation.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PresentationException.class)
	protected ResponseEntity<?> presentationExceptionHandler(PresentationException ex, WebRequest webRequest){
		return handleExceptionInternal(ex, new MensajeError(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus(), webRequest);
	}
	
}
