package com.sinensia.polloloko.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.polloloko.backend.business.model.Producto;
import com.sinensia.polloloko.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/polloloko")
public class ProductoAppController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/listado-productos")
	public ModelAndView getListaProductos() {
		
		List<Producto> productos = productoServices.getAll();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("listadoproductos");
		mav.addObject("productos", productos);
		
		return mav;
	}
	
}
