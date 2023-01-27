package com.sinensia.polloloko.backend.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	// Implementación usando el sistema de nombres de Spring Data (5.3. Query methods)
	
	List<Producto> findByCategoria(Categoria catgoria);
	
	// Implementación usando el lenguaje de consulta JPQL (Java Persistence Query Languaje)
	
	@Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
	List<Producto> dameProductosPorCategoria(Categoria categoria);
	
}
