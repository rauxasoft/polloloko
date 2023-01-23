package com.sinensia.polloloko.backend.model;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Producto producto;
	private int cantidad;

	public LineaPedido() {
		
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "LineaPedido [producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}
