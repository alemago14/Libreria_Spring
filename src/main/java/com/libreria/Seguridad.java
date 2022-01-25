package com.libreria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libreria.servicios.ClienteServicio;

public class Seguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	public ClienteServicio clienteServ;
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(clienteServ)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and()
			.authorizeRequests()
			.antMatchers("/css/", "/js/", "/img/")
			.permitAll()
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/logincheck")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/index")
			.permitAll()
			.and().logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.permitAll();
	}
}
