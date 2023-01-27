package com.sinensia.polloloko.backend.business.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.ProductoServices;
import com.sinensia.polloloko.backend.integration.repositories.ProductoRepository;

@Service
public class ProductoServicesImpl implements ProductoServices {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional
	public Producto create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("El código de producto deber ser null.");
		}
		
		return productoRepository.save(producto);
	}

	@Override
	public Producto read(Long codigo) {
		return productoRepository.findById(codigo).orElse(null);
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		
		boolean existe = productoRepository.existsById(producto.getCodigo());
		
		if(!existe) {
			throw new IllegalStateException("No existe un producto con el código [" + producto.getCodigo() + "]");
		}
		
		productoRepository.save(producto);
		
	}

	@Override
	@Transactional
	public void delete(Long codigo) {
		productoRepository.deleteById(codigo);
	}

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		return productoRepository.findByCategoria(categoria);
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoRepository.count();
	}

	@Override
	public int getNumeroTotalProductosByCategoria(Categoria categoria) {
		return productoRepository.getNumeroTotalProductosByCategoria(categoria);
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		return productoRepository.findByPrecioBetween(min, max);
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		return productoRepository.findByFechaAltaBetween(desde, hasta);
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		
		List<Object[]> estadisticasFromRepository = productoRepository.getEstadisticaNumeroProductosByCategoria();
		
		Map<Categoria, Integer> estadisticas = new HashMap<>();
		
		for(Categoria categoria: Categoria.values()) {
			estadisticas.put(categoria, 0);
		}
		
		for(Object[] fila: estadisticasFromRepository) {
			estadisticas.replace((Categoria) fila[0], ((Long) fila[1]).intValue());
		}
	
		return estadisticas;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioByCategoria() {
		
		List<Object[]> estadisticasFromRepository = productoRepository.getEstadisticaPrecioMedioByCategoria();
		
		Map<Categoria, Double> estadisticas = new HashMap<>();
		
		for(Categoria categoria: Categoria.values()) {
			estadisticas.put(categoria, null);
		}
		
		for(Object[] fila: estadisticasFromRepository) {
			estadisticas.replace((Categoria) fila[0], (Double) fila[1]);
		}
	
		return estadisticas;
	}

	@Override
	@Transactional
	public void incrementarPrecios(Categoria categoria, double porcentaje) {
		productoRepository.incrementarPrecios(categoria, porcentaje);
		
	}

	@Override
	public List<Categoria> getCategorias() {
		return Arrays.asList(Categoria.values());
	}

}
