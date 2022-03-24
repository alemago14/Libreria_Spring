package com.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.servicios.ClienteServicio;


@Controller
@RequestMapping("/")
public class PortalControlador {
	
	@Autowired
	private ClienteServicio clienteServicio;

	@GetMapping("/")
	public String index(@RequestParam(required=false) String logout, ModelMap modelo) {
		if(logout!=null) {
			modelo.put("logout", "Has salido correctamente");
			}
		
		return "index.html";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(required=false) String error, ModelMap modelo) {
		if(error!=null) {
		modelo.put("error", "Clave o usuario invalido");
		}
		return "login.html";
	}
	
	@GetMapping("/registro")
	public String resgistro() {
		return "registro.html";
	}
	
	@PostMapping("/registrar")
	public String registrar(ModelMap modelo, @RequestParam(required= true) String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String telefono, @RequestParam Long dni, @RequestParam String clave, @RequestParam String clave2) {
		try {
			clienteServicio.nuevoCliente(nombre, apellido, dni, telefono, clave, mail, clave2);
			modelo.put("exito", "Registro completado con exito");
		} catch (Exception e) {
			// TODO: handle exception
			modelo.put("error", e.getMessage());
			e.printStackTrace();
		}
		
		return "registro.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO')")
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio.html";
	}
	
}
