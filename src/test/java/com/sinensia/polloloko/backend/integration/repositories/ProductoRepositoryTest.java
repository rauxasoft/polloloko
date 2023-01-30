package com.sinensia.polloloko.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;

@DataJpaTest
@Sql(scripts= {"/data/h2/schema.sql","/data/h2/data.sql"})
public class ProductoRepositoryTest {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Test
	public void devuelve_numero_total_productos_por_categoria() {
		
		int numeroBebidas = productoRepository.getNumeroTotalProductosByCategoria(Categoria.BEBIDA);
		int numeroComidas = productoRepository.getNumeroTotalProductosByCategoria(Categoria.COMIDA);
		int numeroTapas = productoRepository.getNumeroTotalProductosByCategoria(Categoria.TAPA);
		
		assertEquals(6, numeroBebidas);
		assertEquals(5, numeroComidas);
		assertEquals(3, numeroTapas);
	}
	
	@Test
	public void devuelve_productos_entre_rango_precios() {
		
		// ARRANGE
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		Producto p4 = new Producto();
		Producto p5 = new Producto();
		
		p1.setCodigo(18L);
		p2.setCodigo(20L);
		p3.setCodigo(21L);
		p4.setCodigo(23L);
		p5.setCodigo(24L);
		
		List<Producto> productosEsperados = Arrays.asList(p1, p2, p3, p4, p5);
		
		// ACT
		
		List<Producto> productos = productoRepository.findByPrecioBetween(5.0, 10.0);
		
		// ASSERT
	
		assertNotNull(productos);
		assertEquals(5, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
		
	}
	
}
