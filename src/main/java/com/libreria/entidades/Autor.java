package com.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

	//atributos
	
		@Id
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(name = "uuid", strategy= "uuid2")
		private String id;
		private String nombre;
		private Boolean alta;
		
		
		//constructores
		public Autor(String id, String nombre, Boolean alta) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.alta = alta;
		}


		public Autor() {
			
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


		public Boolean getAlta() {
			return alta;
		}


		public void setAlta(Boolean alta) {
			this.alta = alta;
		}
}
