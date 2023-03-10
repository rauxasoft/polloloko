package com.sinensia.polloloko.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloloko.backend.business.model.Categoria;
import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.ProductoServices;
import com.sinensia.polloloko.presentation.config.PresentationException;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/categorias")
	public List<Categoria> getCategorias(){
		return productoServices.getCategorias();
	}
	
	@GetMapping
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
	
	@GetMapping("/{codigo}")
	public Producto getByCodigo(@PathVariable Long codigo) {
		
		Producto producto = productoServices.read(codigo);
		
		if(producto == null) {
			throw new PresentationException("No se encuentra el producto con c??digo [" + codigo + "]", HttpStatus.NOT_FOUND);
		}
		
		return producto;
	}
	
	@PostMapping
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
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Producto producto){
		
		try {
			productoServices.update(producto);
		} catch(Exception e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
		return ResponseEntity.accepted().build();
	}

}
