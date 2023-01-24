package com.sinensia.polloloko.backend.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.integration.DummyDB;
import com.sinensia.polloloko.backend.model.Categoria;
import com.sinensia.polloloko.backend.model.Producto;
import com.sinensia.polloloko.backend.services.ProductoServices;

@Service
public class ProductoServicesImpl implements ProductoServices {
	
	private final DummyDB dummyDB;
	
	public ProductoServicesImpl() {
		this.dummyDB = DummyDB.getInstance();
	}
	
	@Override
	public Producto create(Producto producto) { 
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("No se puede crear un producto que ya tiene código [" +  producto.getCodigo() + "]");
		}
		
		Long ultimoCodigo = ((TreeMap<Long, Producto>) dummyDB.getProductosDB()).lastKey();
		
		Long nuevoCodigo = ultimoCodigo++;
		
		producto.setCodigo(nuevoCodigo);
		
		dummyDB.getProductosDB().put(producto.getCodigo(), producto);
		
		return producto;
	}

	@Override
	public Producto read(Long codigo) {
		return dummyDB.getProductosDB().get(codigo);
	}
	
	@Override
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		if(!dummyDB.getProductosDB().containsKey(codigo)) {
			throw new IllegalStateException("No existe un producto con código [" + codigo + "]");
		}
		
		dummyDB.getProductosDB().replace(codigo, producto);
		
	}

	@Override
	public void delete(Long codigo) {
		
		if(!dummyDB.getProductosDB().containsKey(codigo)) {
			throw new IllegalStateException("No existe un producto con código [" + codigo + "]");
		}
		
		dummyDB.getProductosDB().remove(codigo);
		
	}

	@Override
	public List<Producto> getAll() {
		return new ArrayList<>(dummyDB.getProductosDB().values());
	}

	// PROGRAMACION DECLARATIVA
	
	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		
		return dummyDB.getProductosDB().values().stream()
				.filter(x -> x.getCategoria() == categoria)
				.collect(Collectors.toList());
	}
	
	@Override
	public int getNumeroTotalProductos() {
		return dummyDB.getProductosDB().size();
	}

	@Override
	public int getNumeroTotalProductosByCategoria(Categoria categoria) {
		
		return (int) dummyDB.getProductosDB().values().stream()
				.filter(x -> x.getCategoria() == categoria)
				.count();
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
	
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: dummyDB.getProductosDB().values()) {
			if(producto.getPrecio() >= min && producto.getPrecio() <= max) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: dummyDB.getProductosDB().values()) {
			if(producto.getFechaAlta().after(desde) && producto.getFechaAlta().before(hasta)) {
				productos.add(producto);
			}
		}
		
		return productos;
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		
		Map<Categoria, Integer> estadistica = new HashMap<>();
		
		for(Categoria categoria: Categoria.values()) {
			estadistica.put(categoria, 0);
		}
		
		for(Producto producto : dummyDB.getProductosDB().values()) {
			
			Categoria categoria = producto.getCategoria();
			
			int valorActual = estadistica.get(categoria);
			int nuevoValor = ++valorActual;
	
			estadistica.replace(categoria, nuevoValor);	
		}
		
		return estadistica;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioByCategoria() {
		
		Map<Categoria, Double> estadistica = new HashMap<>();
		Map<Categoria, Integer> aux1 = new HashMap<>();	// Cantidad de productos
	
		for(Categoria categoria: Categoria.values()) {
			estadistica.put(categoria, null);
			aux1.put(categoria, 0);
			
		}
		
		for(Producto producto : dummyDB.getProductosDB().values()) {
			
			Categoria categoria = producto.getCategoria();
			Double precio = producto.getPrecio();
			
			// Numero de productos
			aux1.replace(categoria, aux1.get(categoria) + 1);	
			
			// Acumular precios
			Double precioActualAcumulado = estadistica.get(categoria);
			Double nuevoPrecioAcumulado = (precioActualAcumulado == null) ? precio : precioActualAcumulado + precio;
			estadistica.replace(categoria, nuevoPrecioAcumulado);
			
		}
		
		for(Categoria categoria: Categoria.values()) {
			
			Double importeAcumulado = estadistica.get(categoria);
			
			if(importeAcumulado != null) {
				
				Integer numeroProductos = aux1.get(categoria);
				Double precioMedio = importeAcumulado / numeroProductos;
				
				estadistica.replace(categoria, precioMedio);
			}
		}
		
		return estadistica;
	}

	@Override
	public void incrementarPrecios(Categoria categoria, double porcentaje) {
		
		for(Producto producto: dummyDB.getProductosDB().values()) {
			
			if(producto.getCategoria() == categoria) {
				
				double precio = producto.getPrecio();
				precio += precio * (porcentaje / 100);
				
				producto.setPrecio(precio);
			}
			
		}
		
	}

}
