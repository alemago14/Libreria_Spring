package com.libreria.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.entidades.Cliente;
import com.libreria.servicios.ClienteServicio;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO')")
@RequestMapping("/usuario")
public class ClienteControlador {
	
	@Autowired
	private ClienteServicio clienteServicio;

	@GetMapping("/modificacion/{id}")
	public String modificarUsuario(@PathVariable String id, ModelMap modelo, HttpSession session) {
		Cliente login = (Cliente) session.getAttribute(id);
		
		if(login == null || !login.getId().equals(id)) {
			return "redirect:/inicio";
		}
		
		modelo.addAttribute("usuario", clienteServicio.getOne(id));
		return "modificarUsuario.html";
	}
	
	@PostMapping("/modificarP/{id}")
	public String modificar(ModelMap modelo, HttpSession session, @RequestParam(required= true) String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String telefono, @RequestParam Long dni, @RequestParam String clave, @RequestParam String clave2, @PathVariable String id) {
		Cliente cliente = null;
		try {
			clienteServicio.modificar(id, nombre, apellido, dni, telefono, clave, mail, clave2);
			modelo.put("exito", "Se modifico el perfil con exito");
			cliente = clienteServicio.getOne(id);
			session.setAttribute("usuariossession", cliente);
			
			return "inicio.html";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			e.printStackTrace();
			return "redirect:/modificacion/{id}";
		}
		
		
	}
	
}
