package com.generation.blogpessoal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//ericle@email.com 
	//SELECT * FROM tb_usuario WHERE usuario = "ericle@email.com"
	public Optional<Usuario> findByUsuario(String usuario);
	

}