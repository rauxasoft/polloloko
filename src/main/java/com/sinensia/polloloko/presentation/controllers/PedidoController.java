package com.sinensia.polloloko.presentation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloloko.backend.business.model.Estado;
import com.sinensia.polloloko.backend.business.model.Pedido;
import com.sinensia.polloloko.backend.business.services.PedidoServices;
import com.sinensia.polloloko.presentation.config.PresentationException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/estados")
	public List<Estado> getEstados(){
		return pedidoServices.getEstados();
	}
	
	@GetMapping("/{codigo}")
	public Pedido read(@PathVariable Long codigo) {
		
		Pedido pedido = pedidoServices.read(codigo);
		
		if(pedido == null) {
			throw new PresentationException("No existe el pedido con código " + codigo, HttpStatus.NOT_FOUND);
		}
		
		return pedido;
	}
	
	@Operation(description = "Esta es la descripción de la operación.", summary = "Este es el resumen..." )
	@GetMapping
	public List<Pedido> getPedidos(@Parameter(name = "desde", description = "<p>Sirve para...</p><ul><li>Esto</li><li>Lo otro</li></ul>")
								   @RequestParam(required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date desde,
								   @Parameter(name = "hasta", description = "Sierve para esto y para lo otro...")
								   @RequestParam(required=false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm") Date hasta,
								   @Parameter(name = "empleado", description = "Sierve para esto y para lo otro...")
								   @RequestParam(name="empleado", required=false) Long codigoEmpleado){
		
		if(desde == null && hasta == null && codigoEmpleado == null) {
			return pedidoServices.getAll();
		}
		
		if(desde != null && hasta != null && codigoEmpleado == null) {
			return pedidoServices.getBetweenFechas(desde, hasta);
		}
		
		if(desde == null && hasta == null && codigoEmpleado != null) {
			return pedidoServices.getByCodigoEmpleado(codigoEmpleado);
		}
		
		throw new PresentationException("Los combinación de parámetros de la petición no es correcta. ", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Pedido pedido, UriComponentsBuilder ucb) {
		
		try {
			
			pedido.setEstado(Estado.NUEVO);
			
			Long codigoPedido = pedidoServices.create(pedido);
		
			return ResponseEntity
					.created(ucb.path("/pedidos/{codigo}").build(codigoPedido))
					.build(); 
			
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
