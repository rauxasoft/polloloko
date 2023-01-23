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
import com.sinensia.polloloko.presentation.config.MensajeError;

@RestController
public class EmpleadoController {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	@GetMapping("/empleados")
	public List<Empleado> getAll(){
		return empleadoServices.getAll();
	}
	
	@GetMapping("/empleados/{codigo}")
	public ResponseEntity<?> read(@PathVariable Long codigo) {
		
		Empleado empleado = empleadoServices.read(codigo);
		
		if(empleado == null) {
			
			MensajeError error = new MensajeError("No existe el empleado con código " + codigo);
			
			return new ResponseEntity<MensajeError>(error, HttpStatus.NOT_FOUND);
		
		}
	
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
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
			
		} catch(Exception e) {
			
			MensajeError error = new MensajeError(e.getMessage());
			
			return new ResponseEntity<MensajeError>(error, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/empleados")
	public ResponseEntity<?> update(@RequestBody Empleado empleado) {
		
		try {
			empleadoServices.update(empleado);
		} catch(Exception e) {
			MensajeError error = new MensajeError(e.getMessage());
			return new ResponseEntity<MensajeError>(error, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.noContent().build();
	}

}
