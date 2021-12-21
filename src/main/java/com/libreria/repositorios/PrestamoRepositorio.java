package com.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.entidades.Prestamo;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String>{

	@Query("Select p FROM Prestamo p WHERE p.id = :nombre")
	public Prestamo buscarPorNombre(@Param("nombre")String nombre);
}
