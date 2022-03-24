package com.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Foto;
import com.libreria.entidades.Libro;
import com.libreria.repositorios.AutorRepositorio;
import com.libreria.repositorios.EditorialRepositorio;
import com.libreria.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

	@Autowired
	public LibroRepositorio libroRepo;
	
	@Autowired
	public AutorRepositorio autorRepo;
	
	@Autowired
	public EditorialRepositorio ediRepo;
	
	public AutorServicio autorServ;
	public EditorialServicio ediServ;
	
	@Autowired
	public FotoServicio fotoServicio;
	
	@Transactional
	public void crearLibro(MultipartFile archivo, String nombre, Integer anio, Integer ejemplares, Long isbn, String nombreAutor, String nombreEditorial) throws Exception {
		validarTodo(nombre, anio, ejemplares, isbn, nombreAutor, nombreEditorial);
		
		//Optional<Libro> resp = libroRepo.buscarPorNombreOp(nombre);
		Libro libro = new Libro();
		libroRepo.save(libro);
		
		libro.setTitulo(nombre);
		libro.setAlta(true);
		libro.setAnio(anio);
		libro.setEjemplares(ejemplares);
		libro.setEjemplaresRestantes(ejemplares);
		libro.setEjemplaresPrestados(0);
		libro.setIsbn(isbn);
		
		Foto foto = fotoServicio.guardar(archivo);
		libro.setFoto(foto);
		
		libroRepo.save(libro);
		Autor autor;
		if(autorRepo.buscarPorNombreOp(nombreAutor).isPresent()) {
			autor = autorRepo.buscarPorNombreOp(nombreAutor).get();
		}else{
			autor = new Autor();
			autor.setAlta(true);
			autor.setNombre(nombreAutor);
			
			autorRepo.save(autor);
		}
		
		
		libro.setAutor(autor);
		libroRepo.save(libro);
		
		Editorial edi;
		if(ediRepo.buscarPorNombre(nombreEditorial).isPresent()) {
			edi = ediRepo.buscarPorNombre(nombreEditorial).get();
		}else {
			edi = new Editorial();
			
			edi.setAlta(true);
			edi.setNombre(nombreEditorial);
			
			ediRepo.save(edi);
		}
		
		libro.setEditorial(edi);
		libroRepo.save(libro);
	}
	
	@Transactional
	public void modificarLibro(MultipartFile archivo, String id, String nombre, Integer anio, Integer ejemplares, Long isbn, String nombreAutor, String nombreEditorial) throws Exception {
		validarTodo(nombre, anio, ejemplares, isbn, nombreAutor, nombreEditorial);
		
		Optional<Libro> respLi = libroRepo.findById(id);
		if(respLi.isPresent()) {
			Libro libro = respLi.get();
			libro.setTitulo(nombre);
			libro.setAnio(anio);
			libro.setEjemplares(ejemplares);
			libro.setEjemplaresPrestados(0);
			libro.setEjemplaresRestantes(ejemplares);
			libro.setIsbn(isbn);
			
			String idFoto = null;
			if(libro.getFoto() != null) {
				idFoto = libro.getFoto().getId();
			}
			
			Foto foto = fotoServicio.actualizarFoto(idFoto, archivo);
			libro.setFoto(foto);
			
			libroRepo.save(libro);
			Autor autor;
			if(autorRepo.buscarPorNombreOp(nombreAutor).isPresent()) {
				autor = autorRepo.buscarPorNombreOp(nombreAutor).get();
			}else{
				autor = new Autor();
				autor.setAlta(true);
				autor.setNombre(nombreAutor);
				
				autorRepo.save(autor);
			}
			
			libro.setAutor(autor);
			libroRepo.save(libro);
			
			Editorial edi;
			if(ediRepo.buscarPorNombre(nombreEditorial).isPresent()) {
				edi = ediRepo.buscarPorNombre(nombreEditorial).get();
			}else {
				edi = new Editorial();
				
				edi.setAlta(true);
				edi.setNombre(nombreEditorial);
				
				ediRepo.save(edi);
			}
			
			libro.setEditorial(edi);
			libroRepo.save(libro);
		}else {
			throw new Exception("El libro no existe en la base de datos");
		}
	}
	
	@Transactional
	public void darBajaAlta(String id) {
		Libro libro = libroRepo.buscarPorId(id);
		if(libro.getAlta()==true) {
			libro.setAlta(false);
			libroRepo.save(libro);
		}else {
			libro.setAlta(true);
			libroRepo.save(libro);
		}
	}
	
	public List<Libro> listarTodos(){
		return libroRepo.findAll();
	}
	
	public void validarTodo(String nombre, Integer anio, Integer ejemplares, Long isbn, String nombreAutor, String nombreEditorial) throws Exception {
		if(nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre esta vacio");
		}
		
		if(nombreAutor == null || nombre.isEmpty()) {
			throw new Exception("El nombre del autor esta vacio");
		}
		
		if(nombreEditorial == null || nombre.isEmpty()) {
			throw new Exception("El nombre de la editorial esta vacio");
		}
		
		if(anio == null) {
			throw new Exception("El anio esta vacio");
		}
		
		if(ejemplares == null) {
			throw new Exception("La cantidad de ejemplares esta vacia");
		}
		
		if(isbn == null) {
			throw new Exception("El isbn esta vacio");
		}
	}
	
	public Libro getOne(String id) {
		return libroRepo.getById(id);
	}
	
	public List<Libro> busquedaAutor(String nombre){
		return libroRepo.buscarPorAutor(nombre);
	}
}
