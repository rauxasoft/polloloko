package com.sinensia.polloloko.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinensia.polloloko.backend.business.services.PedidoServices;
import com.sinensia.polloloko.backend.integration.repositories.PedidoRepository;

@RestController
public class BorrameController {

	@Autowired
	private PedidoServices pedidoServices;
	
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
	
	@GetMapping("/trigger2")
	public Object trigger2() {
		
		//return productoServices.getNumeroTotalProductosByCategoria(Categoria.CAFE);
		//return productoServices.getBetweenPriceRange(1.0, 1.8);
		//return productoServices.getBetweenDates(new Date(0), new Date());
		//return productoServices.getEstadisticaNumeroProductosByCategoria();
		//return productoServices.getEstadisticaPrecioMedioByCategoria();
		//       productoServices.incrementarPrecios(Categoria.COMIDA, 5.0);
		
		return pedidoServices.getByCodigoEmpleado(100L);
	}
}
