package com.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.entidades.Autor;
import com.libreria.repositorios.AutorRepositorio;

@Service
public class AutorServicio {
	
	@Autowired
	public AutorRepositorio autorRepo;
	
	@Transactional //anotacion para manejo de la base de datos con funcion que si ocurre una excepcion hace un rollback y no modifica la base de datos
	public void registrarAutor(String nombre) throws Exception {
		validar(nombre);
		Autor autor = new Autor();
		Optional<Autor> resp = autorRepo.buscarPorNombreOp(nombre);
		if(resp.isPresent()) {
			throw new Exception("El autor ya existe en la bd");
		}
		
		autor.setNombre(nombre);
		autor.setAlta(true);
		autor.setId("id");
		
		autorRepo.save(autor);
	}
	
	@Transactional
	public void modificarAutor(String id, String nombre) throws Exception {
		validar(nombre);
		
		Autor resp = autorRepo.buscarPorId(id);
		
		if(resp != null) {
			resp.setNombre(nombre);
			
			autorRepo.save(resp);
		}else {
			throw new Exception("El autor solicitado no existe en la base de datos");
		}
	}
	
	@Transactional
	public void darBajaAlta(String id) throws Exception {
		validar(id);
		
		Autor resp = autorRepo.buscarPorId(id);
		
		if(resp.getAlta() == true) {
			resp.setAlta(false);
			autorRepo.save(resp);
		}else {
			resp.setAlta(true);
			autorRepo.save(resp);
		}
	}
	
	public Autor buscar(String nombre) {
		Autor autor;
		if(autorRepo.buscarPorNombreOp(nombre).isPresent()) {
			autor = autorRepo.buscarPorNombreOp(nombre).get();
		}else{
			autor = new Autor();
			autor.setAlta(true);
			autor.setNombre(nombre);
			
			autorRepo.save(autor);
		}
		
		do {
			autor = autorRepo.buscarPorNombre(nombre);
		} while (autorRepo.buscarPorNombreOp(nombre).isPresent() == false);
		
		return autorRepo.buscarPorNombre(nombre);
	}
	
	public void validar(String nombre) throws Exception {
		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
	}
}
