package com.sinensia.polloloko.presentation.config;

import java.io.Serializable;

public class MensajeError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public MensajeError(String mensaje) {
		this.mensaje = mensaje;
		
	}

	public String getMensaje() {
		return mensaje;
	}

}
