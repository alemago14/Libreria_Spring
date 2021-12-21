package com.libreria.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.libreria.entidades.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, String>{

	@Query("SELECT l FROM Libro l WHERE l.titulo = :nombre")
	public Libro buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("SELECT l FROM Libro l WHERE l.id = :nombre")
	public Libro buscarPorId(@Param("nombre")String nombre);
	
	@Query("SELECT l FROM Libro l WHERE l.titulo = :nombre")
	public Optional<Libro> buscarPorNombreOp(@Param("nombre")String nombre);
	
	@Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:nombre%")
	public List<Libro> buscarPorAutor(@Param("nombre")String nombre);
}
