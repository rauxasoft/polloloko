package com.sinensia.polloloko.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.services.EmpleadoServices;

@SpringBootTest
public class EmpleadoServicesImplIntegrationTest {

	@Autowired
	private EmpleadoServices empleadoServices;
	
	@Test
	public void obtenemos_empleado_a_partir_de_su_codigo() {
		
		Empleado empleado = empleadoServices.read(100L);
		
		assertNotNull(empleado);
		assertEquals(100L, empleado.getCodigo());
		assertEquals("Pepín", empleado.getNombre());
		
	}
}
