package com.libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.entidades.Libro;
import com.libreria.servicios.LibroServicio;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

	@Autowired
	private LibroServicio libroServicio;
	
	@GetMapping("/libro")
	public ResponseEntity<byte[]> portadaLibro(@RequestParam String id){
		try {
			Libro libro = libroServicio.getOne(id);
			if(libro.getFoto() == null) {
				throw new Exception("El libro no tiene portada");
			}
			
			byte[] foto = libro.getFoto().getContenido(); 
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			return new ResponseEntity<byte[]>(foto, headers , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
}
