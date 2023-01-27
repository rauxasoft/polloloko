package com.sinensia.polloloko.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	List<Producto> findByCategoria(Categoria catgoria);

	@Query("SELECT COUNT(p) FROM Producto p WHERE p.categoria = :categoria")
	int getNumeroTotalProductosByCategoria(Categoria categoria);
	
	List<Producto> findByPrecioBetween(double min, double max);
	
	List<Producto> findByFechaAltaBetween(Date desde, Date hasta);
	
	@Query("SELECT p.categoria, COUNT(p) FROM Producto p GROUP BY p.categoria ORDER BY p.categoria")
	List<Object[]> getEstadisticaNumeroProductosByCategoria();
	
	@Query("SELECT p.categoria, AVG(p.precio) FROM Producto p GROUP BY p.categoria ORDER BY p.categoria")
	List<Object[]> getEstadisticaPrecioMedioByCategoria();
	
	@Modifying
	@Query("UPDATE Producto p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p.categoria = :categoria")
	int incrementarPrecios(Categoria categoria, double porcentaje);
	
}
