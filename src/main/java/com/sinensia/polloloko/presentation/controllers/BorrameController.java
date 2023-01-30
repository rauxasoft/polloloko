package com.sinensia.polloloko.presentation.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.LineaPedido;
import com.sinensia.polloloko.backend.business.model.Pedido;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.PedidoServices;
import com.sinensia.polloloko.backend.integration.repositories.PedidoRepository;

@RestController
public class BorrameController {

	@Autowired
	private PedidoServices pedidoServices;

	@GetMapping("/trigger3")
	public Object trigger3() {
		
		return pedidoServices.getUltimosNPedidosByEstado(Estado.EN_PROCESO, 1);
		//return pedidoServices.getUltimosNPedidosByEstado(Estado.NUEVO, 10);
		//return pedidoServices.getUltimosNPedidosByEstado(Estado.NUEVO, 10);
		//return pedidoServices.getUltimosNPedidosByEstado(Estado.NUEVO, 10);
	}
	
	@GetMapping("/trigger2")
	public Object trigger2() {
		
		Empleado empleado = new Empleado();
		empleado.setCodigo(101L);
		
		Producto p1 = new Producto();
		p1.setCodigo(21L);
		
		LineaPedido lp = new LineaPedido();
		lp.setProducto(p1);
		lp.setCantidad(5);
		
		Pedido pedido = new Pedido();
		pedido.setEmpleado(empleado);
		pedido.setFechaHora(new Date());
		pedido.setObservaciones("No hay observaciones...");
		pedido.setLineas(Arrays.asList(lp));
		
		Long codigo = pedidoServices.create(pedido);
		
		Pedido createdPedido = pedidoServices.read(codigo);
		System.out.println(createdPedido);
		
		pedidoServices.cancelar(codigo);
		
	/*	
		pedidoServices.iniciarProceso(codigo);
		createdPedido = pedidoServices.read(codigo);
		System.out.println(createdPedido);
		
		pedidoServices.ofrecerParaEntrega(codigo);
		createdPedido = pedidoServices.read(codigo);
		System.out.println(createdPedido);
		
		pedidoServices.entregar(codigo);
		createdPedido = pedidoServices.read(codigo);
		System.out.println(createdPedido);
	*/	
		return createdPedido;
	}
	
	@Autowired
	
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/trigger1")
	public Object trigger1() {
		
		//return productoRepository.getNumeroTotalProductosByCategoria(Categoria.CAFE);
		//return productoRepository.findByPrecioBetween(1.0, 1.8);
		//return productoRepository.findByFechaAltaBetween(new Date(0), new Date());
		//return productoRepository.getEstadisticaNumeroProductosByCategoria();
		  return pedidoRepository.findByEmpleadoCodigo(100L);	
	}
	
}
