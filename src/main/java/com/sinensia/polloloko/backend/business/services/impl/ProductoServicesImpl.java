package com.sinensia.polloloko.backend.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.ProductoServices;

@Service
public class ProductoServicesImpl implements ProductoServices {

	@Override
	public Producto create(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto read(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalProductos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumeroTotalProductosByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioByCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void incrementarPrecios(Categoria categoria, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> getCategorias() {
		// TODO Auto-generated method stub
		return null;
	}

}
