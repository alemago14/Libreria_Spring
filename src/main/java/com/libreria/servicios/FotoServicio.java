package com.libreria.servicios;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.libreria.entidades.Foto;
import com.libreria.repositorios.FotoRepositorio;

@Service
public class FotoServicio {
	
	@Autowired
	private FotoRepositorio fotoRepositorio;

	@Transactional
	public Foto guardar(MultipartFile archivo) throws IOException {
		if (archivo != null) {
			Foto foto = new Foto();
			foto.setMime(archivo.getContentType());
			foto.setNombre(archivo.getName());
			foto.setContenido(archivo.getBytes());
			
			return fotoRepositorio.save(foto);
		} else {
			return null;
		}
	}
	
	@Transactional
	public Foto actualizarFoto(String id, MultipartFile archivo) {
		if (archivo != null) {
			Foto foto = new Foto();
			
			if(id != null) {
				Optional<Foto> resp = fotoRepositorio.findById(id);
				if(resp.isPresent()) {
					foto = resp.get();
				}
				
				foto.setMime(archivo.getContentType());
				foto.setNombre(archivo.getName());
				try {
					foto.setContenido(archivo.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			return fotoRepositorio.save(foto);
		} else {
			return null;
		}
	}
}
