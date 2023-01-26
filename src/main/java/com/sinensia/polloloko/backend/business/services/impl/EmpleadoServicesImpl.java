package com.sinensia.polloloko.backend.business.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.services.EmpleadoServices;
import com.sinensia.polloloko.backend.integration.repositories.EmpleadoRepository;

@Service
@Primary
public class EmpleadoServicesImpl implements EmpleadoServices {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional
	public Empleado create(Empleado empleado) {
		
		if(empleado.getCodigo() != null) {
			throw new IllegalStateException("El código de empleado deber ser null.");
		}
		
		Empleado createdEmpleado = empleadoRepository.save(empleado);
		
		return createdEmpleado;
	}

	@Override
	public Empleado read(Long codigo) {
		return empleadoRepository.findById(codigo).orElse(null);
	}

	@Override
	public void update(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void delete(Long codigo) {
		empleadoRepository.deleteById(codigo);	
	}

	@Override
	public List<Empleado> getAll() {
		return empleadoRepository.findAll();
	}

}
