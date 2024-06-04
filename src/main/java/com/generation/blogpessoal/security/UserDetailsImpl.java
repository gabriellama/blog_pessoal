package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

/*
 * objetivo é informar para o Security os dados de acesso da API
 */
public class UserDetailsImpl implements UserDetails {
	
	//versão da classe que estamos trabalhando
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	//classe seguranção que tras autorizações de acesso que usuario tem
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario user) {
		
		this.userName = user.getUsuario();//passei o valor do usuário (email de login) para a security
		this.password = user.getSenha();//passei o valor da senha do usuário para a security
		//para as tentativas de login a security já vai ter o dados para investigar se pode autorizar ou não login
		
	}
	
	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// autorizações de acesso do usuário
		return authorities;
	}

	@Override
	public String getPassword() {
		// retorna a senha do usuario
		return password;
	}

	@Override
	public String getUsername() {
		// retorna o usuário que esta tentando logar
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// se a conta não expirou ele acesso - true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// se a conta não esta bloqueada - true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// se a credencial não estiver expirada - true
		return true;
	}

	@Override
	public boolean isEnabled() {
		// se o usuário esta habilitado - true
		return true;
	}

	

}