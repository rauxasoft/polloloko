package com.sinensia.polloloko.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.polloloko.backend.business.model.Coche;
import com.sinensia.polloloko.backend.integration.repositories.CocheRepository;

@RestController
@RequestMapping("/pruebas")
public class PruebasController {

	@Autowired
	private CocheRepository cocheRepository;
	
	@GetMapping("/coches")
	public List<Coche> getCoches(){
		return cocheRepository.findAll();
	}
}
