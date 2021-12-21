package com.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.entidades.Libro;
import com.libreria.servicios.AutorServicio;
import com.libreria.servicios.EditorialServicio;
import com.libreria.servicios.LibroServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {

	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/ingresoLibro")
	public String nuevoL() {
		return "ingresoLibro.html";
	}
	
	@Autowired
	public LibroServicio libroServ;
	
	@PostMapping("/nuevoLibro")
	public String nuevoLibro(ModelMap modelo, @RequestParam String nombre, @RequestParam Integer anio, @RequestParam Long isbn, @RequestParam Integer ejemplares, @RequestParam String autor, @RequestParam String editorial) {
		try {
			libroServ.crearLibro(nombre, anio, ejemplares, isbn, autor, editorial);
			modelo.put("exito", "Registro completado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
			e.printStackTrace();
		}
		
		return "ingresoLibro.html";
	}
	
	@GetMapping("/listaLibro")
	public String listaL(ModelMap modelo) {
		List<Libro> libros = libroServ.listarTodos();
		
		modelo.addAttribute("listaL", libros);
		
		return "listaLibros.html";
	}
	
	public String estadoL() {
		return "";
	}
	
	@GetMapping("/modificarLibro/{id}")
	public String modificarL(@PathVariable String id, ModelMap modelo) {
		modelo.addAttribute("libro", libroServ.getOne(id));
		
		return "modificarLibro.html";
	}
	
	@PostMapping("/modificarL/{id}")
	public String mandarModificacion(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam Integer anio, @RequestParam Long isbn, @RequestParam Integer ejemplares, @RequestParam String autor, @RequestParam String editorial) {
		try {
			libroServ.modificarLibro(id, nombre, anio, ejemplares, isbn, autor, editorial);
			modelo.put("exito", "Registro completado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/modificarLibro/{id}";
	}
	
	@PostMapping("/libroEstado/{id}")
	public String cambiarEstadoLibro(@PathVariable String id) {
		try {
			libroServ.darBajaAlta(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/librosxAutor")
	public String busqAutor(@RequestParam String nombreAutor, ModelMap modelo) {
		List<Libro> libros = libroServ.busquedaAutor(nombreAutor);
		
		modelo.addAttribute("listaL", libros);
		
		return "listaLibros.html";
	}
	
}
