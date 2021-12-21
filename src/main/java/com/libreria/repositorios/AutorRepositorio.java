package com.libreria.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{

	@Query("Select a FROM Autor a WHERE a.nombre = :nombre")
	public Autor buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("SELECT a FROM Autor a WHERE a.id = :id")
	public Autor buscarPorId(@Param("id")String id);
	
	@Query("Select a FROM Autor a WHERE a.nombre = :nombre")
	public Optional<Autor> buscarPorNombreOp(@Param("nombre")String nombre);
	
	@Query("Select a FROM Autor a WHERE a.nombre = :nombre")
	public List<Autor> buscarPorNombreLista(@Param("nombre")String nombre);
}
