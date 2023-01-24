package com.sinensia.polloloko.backend.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.ProductoServices;
import com.sinensia.polloloko.backend.business.services.impl.ProductoServicesImpl;

class ProductoServicesImplTest {

	private ProductoServices productoServices = null;
	
	@BeforeEach
	void init() {
		productoServices = new ProductoServicesImpl();
	}
	
	@Test
	void crear_producto_sin_codigo() {
		
		// Arrange
		
		Producto producto = new Producto(null, "NUEVO PRODUCTO", Categoria.BEBIDA, "DESCRIPCION", 10.0, new Date(0L));
		
		// Act
		
		Producto createdProducto = productoServices.create(producto);
		
		// Assert
		
		Producto productoLeido = null;
		
		productoLeido = productoServices.read(createdProducto.getCodigo());
		
		assertNotNull(createdProducto);
		assertNotNull(createdProducto.getCodigo());
		assertNotNull(productoLeido);
		assertEquals("NUEVO PRODUCTO", productoLeido.getNombre());
		assertEquals(Categoria.BEBIDA, productoLeido.getCategoria());
		
	}
	
	@Test
	void crear_producto_con_codigo_lanza_exception() {
		
		Producto producto = new Producto(100L,"NUEVO PRODUCTO", Categoria.BEBIDA, "DESCRIPCION", 10.0, new Date(0L));
	
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(producto);
		});
		
		String mensaje = exception.getMessage();
		
		assertEquals("No se puede crear un producto que ya tiene c�digo [100]", mensaje);
	}

	@Test
	void testRead() {
	
		Producto producto = productoServices.read(12L);
		
		assertNotNull(producto);
		assertEquals(12L, producto.getCodigo());
		assertEquals("Caf� cortado", producto.getNombre());
		assertEquals(Categoria.CAFE, producto.getCategoria());
		
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		Producto p4 = new Producto();
		Producto p5 = new Producto();
		
		p1.setCodigo(10L);
		p2.setCodigo(11L);
		p3.setCodigo(12L);
		p4.setCodigo(13L);
		p5.setCodigo(14L);
		
		List<Producto> productosEsperados = Arrays.asList(p1,p4,p5,p3,p2);
		
		List<Producto> productos = productoServices.getAll();
		
		assertNotNull(productos);
		assertEquals(5, productos.size());
		assertTrue(productos.containsAll(productosEsperados));
		
	}

	@Test
	void testGetByCategoria() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNumeroTotalProductos() {

		int numeroTotalProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(5, numeroTotalProductos);
		
	}

	@Test
	void testGetNumeroTotalProductosByCategoria() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBetweenPriceRange() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBetweenDates() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEstadisticaNumeroProductosByCategoria() {
	
		Map<Categoria, Integer> estadistica = productoServices.getEstadisticaNumeroProductosByCategoria();
		
		assertNotNull(estadistica);
		assertEquals(7, estadistica.size());
		assertEquals(1, estadistica.get(Categoria.BEBIDA));
		assertEquals(2, estadistica.get(Categoria.CAFE));
		assertEquals(0, estadistica.get(Categoria.CERVEZA));
		assertEquals(1, estadistica.get(Categoria.COMIDA));
		assertEquals(0, estadistica.get(Categoria.LICOR));
		assertEquals(0, estadistica.get(Categoria.POSTRE));
		assertEquals(1, estadistica.get(Categoria.TAPA));
		
	}

	@Test
	void testGetEstadisticaPrecioMedioByCategoria() {
		
		Map<Categoria, Double> estadistica = productoServices.getEstadisticaPrecioMedioByCategoria();
		
		for(Categoria categoria: estadistica.keySet()) {
			System.out.println(categoria + ": " + estadistica.get(categoria));
		}
		
	}

	@Test
	void testIncrementarPrecios() {
		fail("Not yet implemented");
	}

}
