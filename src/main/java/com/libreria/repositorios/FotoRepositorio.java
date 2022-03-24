package com.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.entidades.Foto;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>{

}
