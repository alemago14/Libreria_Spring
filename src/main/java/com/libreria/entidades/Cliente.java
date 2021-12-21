package com.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Cliente {
	
	//atributos
	@Id
	@GeneratedValue(generator= "uuid")
	@GenericGenerator(name= "uuid", strategy= "uuid2")
	private String id;
	
	private String nombre;
	private String apellido;
	private String clave;
	private Long dni;
	private String telefono;
	private Boolean alta;
	
	//constructores
	public Cliente(String id, String nombre, String apellido, Long dni, String telefono, Boolean alta) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.alta = alta;
	}

	public Cliente() {
	}

	//getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

}
