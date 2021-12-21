package com.libreria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libreria.entidades.Cliente;
import com.libreria.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {

	@Autowired
	public ClienteRepositorio clienteRepo;
	
	public void nuevoCliente(String nombre, String apellido, Long dni, String telefono, String clave) throws Exception {
		validar(nombre, apellido, dni, telefono);
		
		Cliente cliente = new Cliente();
		
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni);
		cliente.setClave(clave);
		
	}
	
	public void validar(String nombre, String apellido, Long dni, String telefono) throws Exception {
		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
		
		if (nombre == null || apellido.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
		
		if (nombre == null || telefono.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
	}
}
