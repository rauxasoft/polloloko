package com.sinensia.polloloko.presentation.config;

import org.springframework.http.HttpStatus;

public class PresentationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;

	public PresentationException(String mensaje, HttpStatus httpStatus) {
		super(mensaje);
		this.httpStatus = httpStatus;
	}
	
	public PresentationException(String mensaje, int statusCode) {
		super(mensaje);
		this.httpStatus = HttpStatus.valueOf(statusCode) ;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String toString() {
		return "PresentationException [httpStatus=" + httpStatus + "]";
	}

}
