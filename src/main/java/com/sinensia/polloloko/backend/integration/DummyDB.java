package com.sinensia.polloloko.backend.integration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sinensia.polloloko.backend.model.Categoria;
import com.sinensia.polloloko.backend.model.Empleado;
import com.sinensia.polloloko.backend.model.Estado;
import com.sinensia.polloloko.backend.model.LineaPedido;
import com.sinensia.polloloko.backend.model.Pedido;
import com.sinensia.polloloko.backend.model.Producto;

public class DummyDB {

	private static DummyDB dummyDB;
	
	private final TreeMap<Long, Producto> PRODUCTOS_DB = new TreeMap<>();
	private final TreeMap<Long, Empleado> EMPLEADOS_DB = new TreeMap<>();
	private final TreeMap<Long, Pedido> PEDIDOS_DB = new TreeMap<>();
	
	private DummyDB() {
		init();
	}
	
	public static DummyDB getInstance() {
		if(dummyDB == null) {
			dummyDB = new DummyDB();
		}
		return dummyDB;
	}
	
	public Map<Long, Producto> getProductosDB(){
		return PRODUCTOS_DB;
	}
	
	public Map<Long, Empleado> getEmpleadosDB(){
		return EMPLEADOS_DB;
	}
	
	public Map<Long, Pedido> getPedidosDB(){
		return PEDIDOS_DB;
	}
	
	// ***********************************************************************
	//
	// PRIVATE METHODS
	//
	// ***********************************************************************
	
	private void init() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha1 = null;
		Date fecha2 = null;
		Date fecha3 = null;
		Date fecha4 = null;
		Date fecha5 = null;
		
		try {
			fecha1 = sdf.parse("01/10/1919");
			fecha2 = sdf.parse("02/10/1919");
			fecha3 = sdf.parse("03/10/1919");
			fecha4 = sdf.parse("04/10/1919");
			fecha5 = sdf.parse("05/10/1919");
			
		} catch(Exception e) {
			
		}
		
		Producto producto1 = new Producto(10L,"Cafe", Categoria.CAFE, "Delicioso cafe colombiano.", 2.0, fecha1);
		Producto producto2 = new Producto(11L,"Pollo KimJon Un", Categoria.COMIDA, "Delicioso pollo con arroz al estilo coreano.", 16.0, fecha2);
		Producto producto3 = new Producto(12L,"Cafe cortado", Categoria.CAFE, "Delicioso cafe cortado.", 2.3, fecha3);
		Producto producto4 = new Producto(13L,"Cocacola Zero", Categoria.BEBIDA, "Lata de Cocacola Zero 33ml.", 2.7, fecha4);
		Producto producto5 = new Producto(14L,"Patatas Bravas", Categoria.TAPA, "Deliociosas patatas con salsa brava muy picante.", 8.0, fecha5);
		
		producto5.setDescatalogado(true);
		
		PRODUCTOS_DB.put(producto1.getCodigo(), producto1);
		PRODUCTOS_DB.put(producto2.getCodigo(), producto2);
		PRODUCTOS_DB.put(producto3.getCodigo(), producto3);
		PRODUCTOS_DB.put(producto4.getCodigo(), producto4);
		PRODUCTOS_DB.put(producto5.getCodigo(), producto5);
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		Empleado empleado3 = new Empleado();
		
		empleado1.setCodigo(100L);
		empleado1.setNombre("Honorio");
		empleado1.setApellido1("Martin");
		empleado1.setApellido2("Salvador");
		
		empleado2.setCodigo(101L);
		empleado2.setNombre("Pepin");
		empleado2.setApellido1("Gilvez");
		empleado2.setApellido2("Ridruejo");
		
		empleado3.setCodigo(102L);
		empleado3.setNombre("Carlota");
		empleado3.setApellido1("Cifuentes");
		empleado3.setApellido2("Merino");
		
		EMPLEADOS_DB.put(empleado1.getCodigo(), empleado1);
		EMPLEADOS_DB.put(empleado2.getCodigo(), empleado2);
		EMPLEADOS_DB.put(empleado3.getCodigo(), empleado3);
		
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Date fechaPedido1 = null;
		Date fechaPedido2 = null;
		Date fechaPedido3 = null;
		
		try {
			fechaPedido1 = sdf.parse("15/01/2023 11:23");
			fechaPedido2 = sdf.parse("15/01/2023 11:29");
			fechaPedido3 = sdf.parse("15/01/2023 12:03");
		} catch(Exception e) {
			
		}
		
		Pedido pedido1 = new Pedido();
		Pedido pedido2 = new Pedido();
		Pedido pedido3 = new Pedido();
		
		LineaPedido lineaPedido11 = new LineaPedido();
		LineaPedido lineaPedido21 = new LineaPedido();
		LineaPedido lineaPedido22 = new LineaPedido();
		LineaPedido lineaPedido31 = new LineaPedido();
		LineaPedido lineaPedido32 = new LineaPedido();
		LineaPedido lineaPedido33 = new LineaPedido();
		LineaPedido lineaPedido34 = new LineaPedido();
		
		lineaPedido11.setProducto(producto1);
		lineaPedido11.setCantidad(1);
		
		lineaPedido21.setProducto(producto3);
		lineaPedido21.setCantidad(2);
		
		lineaPedido22.setProducto(producto1);
		lineaPedido22.setCantidad(1);
		
		lineaPedido31.setProducto(producto2);
		lineaPedido31.setCantidad(1);
		
		lineaPedido32.setProducto(producto3);
		lineaPedido32.setCantidad(5);
		
		lineaPedido33.setProducto(producto4);
		lineaPedido33.setCantidad(2);
		
		lineaPedido34.setProducto(producto5);
		lineaPedido34.setCantidad(10);
		
		List<LineaPedido> lineasPedido1 = Arrays.asList(lineaPedido11);
		List<LineaPedido> lineasPedido2 = Arrays.asList(lineaPedido21, lineaPedido22);
		List<LineaPedido> lineasPedido3 = Arrays.asList(lineaPedido31, lineaPedido32, lineaPedido33, lineaPedido34);
		
		pedido1.setCodigo(1L);
		pedido1.setFechaHora(fechaPedido1);
		pedido1.setEmpleado(empleado3);
		pedido1.setEstado(Estado.ENTREGADO);
		pedido1.setObservaciones(null);
		pedido1.setLineas(lineasPedido1);
		
		pedido2.setCodigo(2L);
		pedido2.setFechaHora(fechaPedido2);
		pedido2.setEmpleado(empleado3);
		pedido2.setEstado(Estado.EN_PROCESO);
		pedido2.setObservaciones(null);
		pedido2.setLineas(lineasPedido2);
		
		pedido3.setCodigo(3L);
		pedido3.setFechaHora(fechaPedido3);
		pedido3.setEmpleado(empleado2);
		pedido3.setEstado(Estado.NUEVO);
		pedido3.setObservaciones("Las bebidas sin hielo.");
		pedido3.setLineas(lineasPedido3);
		
		PEDIDOS_DB.put(pedido1.getCodigo(), pedido1);
		PEDIDOS_DB.put(pedido2.getCodigo(), pedido2);
		PEDIDOS_DB.put(pedido3.getCodigo(), pedido3);
		
	}

}
