package com.libreria.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

	@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
	public Optional<Editorial> buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("SELECT e FROM Editorial e WHERE e.id = :id")
	public Editorial buscarPorId(@Param("id")String id);
}
