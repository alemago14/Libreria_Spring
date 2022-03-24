package com.libreria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libreria.servicios.ClienteServicio;

@SpringBootApplication
public class Libreria1Application {

	public static void main(String[] args) {
		SpringApplication.run(Libreria1Application.class, args);
	}

}
