package com.sinensia.polloloko.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloloko.backend.model.Categoria;
import com.sinensia.polloloko.backend.model.Producto;
import com.sinensia.polloloko.backend.services.ProductoServices;
import com.sinensia.polloloko.presentation.config.MensajeError;
import com.sinensia.polloloko.presentation.config.PresentationException;

@RestController
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/productos")
	public List<Producto> getProductos(@RequestParam(required=false) Double min, 
									   @RequestParam(required=false) Double max,
									   @RequestParam(required=false) Categoria categoria){

		List<Producto> productos = null;
		
		if(min == null && max == null && categoria == null) {
			productos = productoServices.getAll();
		}
		
		if(categoria != null) {
			productos = productoServices.getByCategoria(categoria);
		}
		
		if(categoria == null && min != null && max != null) {
			productos = productoServices.getBetweenPriceRange(min, max);
		}
		
		return productos;
	}
	
	@GetMapping("/productos/{codigo}")
	public Producto getByCodigo(@PathVariable Long codigo) {
		
		Producto producto = productoServices.read(codigo);
		
		if(producto == null) {
			throw new PresentationException("No se encuentra el producto con código [" + codigo + "]", HttpStatus.NOT_FOUND);
		}
		
		return producto;
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> create(@RequestBody Producto producto, UriComponentsBuilder ucb){
		
		Producto createdProducto = null;
		
		try {
			createdProducto = productoServices.create(producto);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity
				.created(ucb.path("/productos/{codigo}").build(createdProducto.getCodigo()))
				.build(); 
	}
	
	@PutMapping("/productos")
	public ResponseEntity<?> update(@RequestBody Producto producto){
		
		try {
			productoServices.update(producto);
		} catch(Exception e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
		return ResponseEntity.accepted().build();
	}
	
	// ***********************************************************************
	//
	// Exception Handler
	//
	// ***********************************************************************
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception e) {
    	MensajeError mensajeError = new MensajeError(e.getMessage());
    	return new ResponseEntity<MensajeError>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(PresentationException.class)
    public ResponseEntity<?> presentationExceptionHandler(PresentationException e) {
    	MensajeError mensajeError = new MensajeError(e.getMessage());
    	return new ResponseEntity<MensajeError>(mensajeError, e.getHttpStatus());
    }
	
}
