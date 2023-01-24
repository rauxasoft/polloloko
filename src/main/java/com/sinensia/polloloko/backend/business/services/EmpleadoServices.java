package com.sinensia.polloloko.backend.business.services;

import java.util.List;

import com.sinensia.polloloko.backend.business.model.Empleado;

public interface EmpleadoServices {

	/**
	 * El sistema calcula el codigo
	 * El empleado entrante deber tener codigo null. Si no el caso, se lanza excepción del tipo IllegalStateException
	 *
	 */
	Empleado create(Empleado empleado);	
	
	/**
	 * Si no se encuentra el empleado devuelve null
	 */
	Empleado read(Long codigo);			
	
	/**
	 * El empleado entrante debe tener un código existente en el sistema. 
	 * Si el código no existe lanza una excepci�n del tipo IllegalStateException
	 */
	void update(Empleado empleado);
	
	void delete(Long codigo);
	
	List<Empleado> getAll();
}
