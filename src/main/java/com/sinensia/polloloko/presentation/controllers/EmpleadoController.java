package com.sinensia.polloloko.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.polloloko.backend.model.Empleado;
import com.sinensia.polloloko.backend.services.EmpleadoServices;
import com.sinensia.polloloko.presentation.config.PresentationException;

@RestController
public class EmpleadoController {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	@GetMapping("/empleados")
	public List<Empleado> getAll(){
		return empleadoServices.getAll();
	}
	
	@GetMapping("/empleados/{codigo}")
	public Empleado read(@PathVariable Long codigo) {
		
		Empleado empleado = empleadoServices.read(codigo);
		
		if(empleado == null) {
			throw new PresentationException("No existe el empleado con código " + codigo, HttpStatus.NOT_FOUND);
		}
	
		return empleado;
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> crear(@RequestBody Empleado empleado, UriComponentsBuilder ucb) {
		
		Empleado createdEmpleado = null;
		
		try {
			
			createdEmpleado = empleadoServices.create(empleado);
			Long codigo = createdEmpleado.getCodigo();
			
			return ResponseEntity
					.created(ucb.path("/empleados/{codigo}").build(codigo))
					.build(); 
			
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/empleados")
	public ResponseEntity<?> update(@RequestBody Empleado empleado) {
		
		try {
			empleadoServices.update(empleado);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

}
