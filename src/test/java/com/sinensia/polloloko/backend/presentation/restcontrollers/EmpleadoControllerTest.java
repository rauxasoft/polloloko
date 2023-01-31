package com.sinensia.polloloko.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.polloloko.backend.business.model.Empleado;
import com.sinensia.polloloko.backend.business.services.EmpleadoServices;
import com.sinensia.polloloko.presentation.config.MensajeError;
import com.sinensia.polloloko.presentation.restcontrollers.EmpleadoController;

@WebMvcTest(controllers=EmpleadoController.class)
public class EmpleadoControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@MockBean
	private EmpleadoServices empleadoServices;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void pedimos_todos_los_empleados() throws Exception {
		
		Empleado e1 = new Empleado();
		Empleado e2 = new Empleado();
		
		e1.setCodigo(100L);
		e1.setNombre("Pepín");
		e1.setApellido1("Gálvez");
		e1.setApellido2("Ridruejo");
		
		e2.setCodigo(101L);
		e2.setNombre("Carlota");
		e2.setApellido1("Cifuentes");
		e2.setApellido2("Merino");
		
		List<Empleado> empleados = Arrays.asList(e1, e2);
		
		when(empleadoServices.getAll()).thenReturn(empleados);
		
		MvcResult mvcResult = miniPostman.perform(get("/empleados").contentType("application/json"))
						.andExpect(status().isOk()).andReturn();
		
		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(empleados));
	
	}
	
	@Test
	public void creamos_empleado_ok() throws Exception {
		
		Empleado empleado1 = new Empleado();
		empleado1.setCodigo(null);
		empleado1.setNombre("Carlota");
		empleado1.setApellido1("Cifuentes");
		empleado1.setApellido2("Merino");
		
		Empleado empleado2 = new Empleado();
		empleado2.setCodigo(12L);
		empleado2.setNombre("Carlota");
		empleado2.setApellido1("Cifuentes");
		empleado2.setApellido2("Merino");
		
		when(empleadoServices.create(empleado1)).thenReturn(empleado2);
		
		String requestBody = objectMapper.writeValueAsString(empleado1);
		
		miniPostman.perform(post("/empleados").contentType("application/json").content(requestBody))
								.andExpect(status().isCreated())
								.andExpect(header().string("Location","http://localhost/empleados/12"));
									
	}
	
	@Test
	public void creamos_empleado_codigo_existente() throws Exception {
	
		Empleado empleado = new Empleado();
		empleado.setCodigo(500L);
	
		when(empleadoServices.create(empleado)).thenThrow(new IllegalStateException("El código de empleado deber ser null."));
		
		String requestBody = objectMapper.writeValueAsString(empleado);
		
		MvcResult mvcResult = miniPostman.perform(post("/empleados").contentType("application/json").content(requestBody))
								.andExpect(status().isBadRequest()).andReturn();
		
		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		MensajeError mensajeError = new MensajeError("El código de empleado deber ser null.");
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(mensajeError));
		
	}
	
}
