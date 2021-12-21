package com.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.entidades.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{

	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	public Cliente buscarPorId(@Param("id")String id);
}
