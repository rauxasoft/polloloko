package com.sinensia.polloloko.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.integration.repositories.EmpleadoRepository;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServicesImplTest {

	@Mock
	private EmpleadoRepository empleadoRepository;
	
	@InjectMocks
	private EmpleadoServicesImpl empleadoServices;
	
	@Test
	void creamos_empleado_con_codigo() {
		
		Empleado empleado = new Empleado();
		empleado.setCodigo(1000L);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.create(empleado);
		});
		
		assertEquals("El código de empleado deber ser null.", exception.getMessage());	
	}
	
	@Test
	void creamos_empleado_OK() {
		
		Empleado empleado = new Empleado();
		empleado.setCodigo(null);
		empleado.setNombre("Marta");
		empleado.setApellido1("Quesada");
		empleado.setApellido2("Beltrán");
		
		Empleado empleadoCreatedByRepository = new Empleado();
		empleadoCreatedByRepository.setCodigo(666L);
		empleadoCreatedByRepository.setNombre("Marta");
		empleadoCreatedByRepository.setApellido1("Quesada");
		empleadoCreatedByRepository.setApellido2("Beltrán");
		
		when(empleadoRepository.save(empleado)).thenReturn(empleadoCreatedByRepository);
		
		Empleado createdEmpleado = empleadoServices.create(empleado);
		
		assertNotNull(createdEmpleado);
		assertNotNull(createdEmpleado.getCodigo());
		assertEquals("Marta", createdEmpleado.getNombre());
		assertEquals("Quesada", createdEmpleado.getApellido1());
		assertEquals("Beltrán", createdEmpleado.getApellido2());
		
		verify(empleadoRepository, times(1)).save(empleado);
		
	}
	
	@Test
	public void solicitamos_empleado_con_codigo_existente() {
		
		Empleado empleado = new Empleado();
		empleado.setCodigo(11L);
		empleado.setNombre("Julio");
		empleado.setApellido1("Badimón");
		empleado.setApellido2("Deza");
		
		when(empleadoRepository.findById(11L)).thenReturn(Optional.of(empleado));
		
		Empleado empleadoFromService = empleadoServices.read(11L);
		
		assertNotNull(empleadoFromService);
		assertEquals(11L, empleadoFromService.getCodigo());
		assertEquals("Julio", empleadoFromService.getNombre());
		assertEquals("Badimón", empleadoFromService.getApellido1());
		assertEquals("Deza", empleadoFromService.getApellido2());
		
	}
	
	@Test
	public void solicitamos_empleado_con_codigo_inexistente() {
		
		when(empleadoRepository.findById(11L)).thenReturn(Optional.empty());
		
		Empleado empleadoFromService = empleadoServices.read(11L);
		
		assertNull(empleadoFromService);
		
	}
	
	@Test
	public void actualizamos_empleado_no_existente() {
		
		when(empleadoRepository.existsById(500L)).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			Empleado empleado = new Empleado();
			empleado.setCodigo(500L);
			empleadoServices.update(empleado);
		});
		
		assertEquals("No existe un empleado con el código [500]", exception.getMessage());	
	}
	
	@Test
	public void actualizamos_empleado_existente() {
		
		Empleado empleado = new Empleado();
		empleado.setCodigo(500L);
		
		when(empleadoRepository.existsById(500L)).thenReturn(true);
		
		empleadoServices.update(empleado);
		
		verify(empleadoRepository, times(1)).save(empleado);
		
	}
	
	@Test
	public void eliminamos_un_empleado_que_no_existe() {
		
		when(empleadoRepository.existsById(500L)).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empleadoServices.delete(500L);
		});
		
		assertEquals("No existe un empleado con el código [500]", exception.getMessage());	
	}
	
	@Test
	public void eliminamos_un_empleado_que_existe() {
		
		when(empleadoRepository.existsById(500L)).thenReturn(true);
		
		empleadoServices.delete(500L);
		
		verify(empleadoRepository, times(1)).deleteById(500L);
	}
	
	@Test
	public void pedimos_listado_de_todos_los_empleados() {
		
		Empleado e1 = new Empleado();
		Empleado e2 = new Empleado();
		Empleado e3 = new Empleado();
		e1.setCodigo(10L);
		e2.setCodigo(11L);
		e3.setCodigo(12L);
		
		List<Empleado> empleadosFromRepository = Arrays.asList(e1, e2, e3);
		
		when(empleadoRepository.findAll()).thenReturn(empleadosFromRepository);
		
		List<Empleado> empleados = empleadoServices.getAll();
		
		assertNotNull(empleados);
		assertEquals(3, empleados.size());
		assertTrue(empleados.containsAll(empleadosFromRepository));
	}
	
	
	
}
