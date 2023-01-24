package com.sinensia.polloloko.presentation.config;

import java.io.Serializable;

public class MensajeError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	private Long timeStamp;
	
	public MensajeError(String mensaje) {
		this.mensaje = mensaje;
		this.timeStamp = System.currentTimeMillis();
	}

	public String getMensaje() {
		return mensaje;
	}
	
	public Long getTimeStamp() {
		return timeStamp;
	}

}
