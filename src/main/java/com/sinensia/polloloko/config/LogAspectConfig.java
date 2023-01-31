package com.sinensia.polloloko.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspectConfig {

	private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
	
	@Before(value = "execution(* com.sinensia.polloloko.presentation.restcontrollers.*.*(..))")
	public void logPresentationLayer(JoinPoint joinPoint) {
		
		String clase = joinPoint.getTarget().getClass().getSimpleName();
		String metodo = joinPoint.getSignature().getName();
		
		logger.info("Invocado método {}() de la clase {}", metodo, clase);
		
	}
	
	// TODO crear el método para interceptar cualquier metodo de cualquier clase de la capa de business
	// TODO investigar como obtener la información de los argumentos que se están pasando
	// TODO incorporar esa información en el logg
}
