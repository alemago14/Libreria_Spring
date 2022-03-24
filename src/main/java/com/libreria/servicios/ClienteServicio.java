package com.libreria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.libreria.entidades.Cliente;
import com.libreria.enumeraciones.Rol;
import com.libreria.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio implements UserDetailsService{

	@Autowired
	public ClienteRepositorio clienteRepo;
	
	@Transactional
	public void nuevoCliente(String nombre, String apellido, Long dni, String telefono, String clave, String mail, String clave2) throws Exception {
		validar(nombre, apellido, dni, telefono);
		Optional<Cliente> resp = clienteRepo.buscarPorMail(mail);
		if (resp.isPresent()) {
			throw new Exception("Este mail ya se encuentra registrado");
		} else {
			Cliente cliente = new Cliente();
			
			if (!clave.equals(clave2)) {
				throw new Exception("Las claves no coinciden");
			}
			
			
			clienteRepo.save(cliente);
			
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setDni(dni);
			cliente.setMail(mail);
			cliente.setRol(Rol.USUARIO);
			
			String encriptada = new BCryptPasswordEncoder().encode(clave);
			cliente.setClave(encriptada);
			
			clienteRepo.save(cliente);
		}
		
	}
	
	public void modificar(String id, String nombre, String apellido, Long dni, String telefono, String clave, String mail, String clave2) throws Exception {
		validar(nombre, apellido, dni, telefono);
		
		Optional resp = clienteRepo.findById(id);
		if (resp.isPresent()) {
			Cliente cliente = (Cliente) resp.get();
			
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setMail(mail);
			cliente.setDni(dni);
			cliente.setTelefono(telefono);
			String encriptada = new BCryptPasswordEncoder().encode(clave);
			cliente.setClave(encriptada);
			
			clienteRepo.save(cliente);
		} else {
			throw new Exception("El id es incorrecto");
		}
	}
	
	public Cliente getOne(String id) {
		Cliente cliente = clienteRepo.getById(id);
		return cliente;
	}
	
	public void validar(String nombre, String apellido, Long dni, String telefono) throws Exception {
		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
		
		if (nombre == null || apellido.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
		
		if (nombre == null || telefono.isEmpty()) {
			throw new Exception("El nombre esta vacio o no es valido");
		}
	}
	
	
	public UserDetails loadUserByUsername(String mail) {
		Optional<Cliente> resp = clienteRepo.buscarPorMail(mail);
		if(resp.isPresent()) {
			Cliente cliente = resp.get();
			List<GrantedAuthority> permisos = new ArrayList<>();
			
			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+cliente.getRol());
			permisos.add(p1);
			
			//esto me permite guardar el usuario logueado para usarlo mas tarde
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("usuariossession", cliente);
			
			User user = new User(cliente.getMail(), cliente.getClave(), permisos);
			return user;
		}
		
		return null;}
}
