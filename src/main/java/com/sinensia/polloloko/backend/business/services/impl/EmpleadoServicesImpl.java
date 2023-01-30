package com.sinensia.polloloko.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.services.EmpleadoServices;
import com.sinensia.polloloko.backend.integration.repositories.EmpleadoRepository;

@Service
public class EmpleadoServicesImpl implements EmpleadoServices {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional
	public Empleado create(Empleado empleado) {
		
		if(empleado.getCodigo() != null) {
			throw new IllegalStateException("El código de empleado deber ser null.");
		}
		
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado read(Long codigo) {
		
		Optional<Empleado> optional = empleadoRepository.findById(codigo);
		
		Empleado empleado = null;
		
		if(optional.isPresent()) {
			empleado = optional.get();
		}
	
		return empleado;
	}

	@Override
	@Transactional
	public void update(Empleado empleado) {
		
		boolean existe = empleadoRepository.existsById(empleado.getCodigo());
		
		if(!existe) {
			throw new IllegalStateException("No existe un empleado con el código [" + empleado.getCodigo() + "]");
		}
		
		empleadoRepository.save(empleado);
		
	}

	@Override
	@Transactional
	public void delete(Long codigo) {
		
		boolean existe = empleadoRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("No existe un empleado con el código [" + codigo + "]");
		}
		
		empleadoRepository.deleteById(codigo);	
	}

	@Override
	public List<Empleado> getAll() {
		return empleadoRepository.findAll();
	}

}
