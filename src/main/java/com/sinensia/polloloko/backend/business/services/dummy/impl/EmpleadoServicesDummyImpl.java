package com.sinensia.polloloko.backend.business.services.dummy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.services.EmpleadoServices;
import com.sinensia.polloloko.backend.integration.repositories.dummy.DummyDB;

@Service
@Primary
public class EmpleadoServicesDummyImpl implements EmpleadoServices{

	private final DummyDB dummyDB;
	
	public EmpleadoServicesDummyImpl() {
		dummyDB = DummyDB.getInstance();
	}
	
	@Override
	public Empleado create(Empleado empleado) {
		
		if(empleado.getCodigo() != null) {
			throw new IllegalStateException("El código de empleado deber ser null.");
		}
		
		TreeMap<Long, Empleado> empleadosDB = (TreeMap<Long, Empleado>) dummyDB.getEmpleadosDB();
		
		Long ultimoCodigo = empleadosDB.lastKey();
		Long nuevoCodigo = ++ultimoCodigo;
		
		empleado.setCodigo(nuevoCodigo);
		
		dummyDB.getEmpleadosDB().put(empleado.getCodigo(), empleado);
		
		return empleado;
	}

	@Override
	public Empleado read(Long codigo) {
		return dummyDB.getEmpleadosDB().get(codigo);
	}

	@Override
	public void update(Empleado empleado) {
		
		if(!dummyDB.getEmpleadosDB().containsKey(empleado.getCodigo())) {
			throw new IllegalStateException("No existe un empleado con código [" + empleado.getCodigo() +  "]");
		}
		
		dummyDB.getEmpleadosDB().replace(empleado.getCodigo(), empleado);
		
	}
	
	@Override
	public void delete(Long codigo) {
		
		if(!dummyDB.getEmpleadosDB().containsKey(codigo)) {
			throw new IllegalStateException("No existe un empleado con código [" + codigo + "]");
		}
		
		dummyDB.getEmpleadosDB().remove(codigo);
	}

	@Override
	public List<Empleado> getAll() {
		return new ArrayList<>(dummyDB.getEmpleadosDB().values());
	}

}
