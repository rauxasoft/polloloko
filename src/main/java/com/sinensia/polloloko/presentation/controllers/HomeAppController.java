package com.sinensia.polloloko.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/polloloko")
public class HomeAppController {

	@GetMapping
	public String home() {
		return "home";		
	}
}
