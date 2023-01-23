package com.sinensia.polloloko.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinensia.polloloko.backend.model.Categoria;
import com.sinensia.polloloko.backend.model.Producto;

public interface ProductoServices {

	/**
	 * El sistema calcula el codigo del producto que creamos
	 * El producto entrante deber tener codigo null. Si no el caso, se lanza excepci�n del tipo IllegalStateException
	 *
	 */
	Producto create(Producto producto);	// C
	
	/**
	 * Si no se encuentra el producto devuelve null
	 */
	Producto read(Long codigo);			// R
	
	/**
	 * El producto entrante debe tener un c�digo existente en el sistema. 
	 * Si el c�digo no existe lanza una excepci�n del tipo IllegalStateException
	 */
	void update(Producto producto);		// U
	
	/**
	 * Si el producto no existe se lanza una excepci�n del tipo IllegalStateException
	 * 
	 */
	void delete(Long codigo);			// D
	
	List<Producto> getAll();
	List<Producto> getByCategoria(Categoria categoria);
	int getNumeroTotalProductos();
	int getNumeroTotalProductosByCategoria(Categoria categoria);
	List<Producto> getBetweenPriceRange(double min, double max);
	List<Producto> getBetweenDates(Date desde, Date hasta);
	Map<Categoria, Integer> getEstadisticaNumeroProductosByCategoria();
	Map<Categoria, Double> getEstadisticaPrecioMedioByCategoria();

	/**
	 * Por ejemplo: porcentaje 20.0 ==> 20%
	 * 
	 */
	void incrementarPrecios(Categoria categoria, double porcentaje);


}
