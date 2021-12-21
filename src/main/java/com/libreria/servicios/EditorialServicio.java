package com.libreria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.entidades.Editorial;
import com.libreria.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
	
	@Autowired
	public EditorialRepositorio ediRepo;

	public void validar(String nombre) throws Exception {
		if(nombre == null || nombre.isEmpty()) {
			throw new Exception("El campo ingresado esta vacio");
		}
	}
	
	@Transactional
	public void crearEditorial(String nombre) throws Exception {
		validar(nombre);
		
		Optional<Editorial> resp = ediRepo.buscarPorNombre(nombre);
		if(resp.isPresent()) {
			throw new Exception("Esa editorial ya existe en la base de datos");
		}else {
			Editorial edi = new Editorial();
			edi.setNombre(nombre);
			edi.setAlta(true);
			
			ediRepo.save(edi);
			
		}
	}
	
	@Transactional
	public void modificarEditorial(String nombre, String id) throws Exception {
		validar(nombre);
		
		Optional<Editorial> resp = ediRepo.findById(id);
		if(resp.isPresent()) {
			Editorial edi = resp.get();
			edi.setNombre(nombre);
			
			ediRepo.save(edi);
		}else {
			throw new Exception("La editorial no existe en la base de datos");
		}
	}
	
	@Transactional
	public void darBajaAlta(String id) throws Exception {
		validar(id);
		
		Optional<Editorial> resp = ediRepo.findById(id);
		if(resp.isPresent()) {
			Editorial edi = resp.get();
			if(edi.getAlta()== true) {
				edi.setAlta(false);
				ediRepo.save(edi);
			}else {
				edi.setAlta(true);
				ediRepo.save(edi);
			}
		}else {
			throw new Exception("La editorial no existe en la base de datos");
		}
	}
	
	public Editorial buscarPorNombre(String nombre) throws Exception {
		Optional<Editorial> resp;
		try {
			do {
				resp = ediRepo.buscarPorNombre(nombre);
				if(!resp.isPresent()) {
					crearEditorial(nombre);
				}
			} while (!resp.isPresent());
			return resp.get();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
