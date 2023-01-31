package com.sinensia.polloloko.presentation.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.polloloko.backend.business.model.Categoria;
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
	
	@GetMapping("/alta-producto")
	public ModelAndView getFichaProducto(ModelAndView modelAndView) throws InterruptedException {
		return new ModelAndView("formulario-alta-producto", "producto", new Producto());
	}
	
	@PostMapping("/create-producto")
	public String createProducto(@ModelAttribute("producto") Producto producto, BindingResult result) {
	
		if (result.hasErrors()) {
			System.out.println(result.toString());
			
            return "error";
        }
		
		producto.setFechaAlta(new Date());
		
        productoServices.create(producto);
        
        return "redirect:listado-productos";
       
	}
	
	@GetMapping("/ficha-producto")
	public ModelAndView getFichaProducto(@RequestParam Long codigo, ModelAndView modelAndView) {

		Producto producto = productoServices.read(codigo);
		
		modelAndView.setViewName("ficha-producto");
		modelAndView.addObject("producto", producto);
		
		return modelAndView;
	}
	
	@GetMapping("/estadisticas")
	public ModelAndView getPaginaEstadistica(ModelAndView modelAndView) {

		long numeroProductos = productoServices.getNumeroTotalProductos();
		Map<Categoria, Integer> estadistica1 = productoServices.getEstadisticaNumeroProductosByCategoria();
		Map<Categoria, Double> estadistica2 = productoServices.getEstadisticaPrecioMedioByCategoria();
		
		modelAndView.setViewName("estadisticaproductos");
		
		modelAndView.addObject("numeroProductos", numeroProductos);
		modelAndView.addObject("estadistica1", estadistica1);
		modelAndView.addObject("estadistica2", estadistica2);
		
		return modelAndView;
	}
	
}
