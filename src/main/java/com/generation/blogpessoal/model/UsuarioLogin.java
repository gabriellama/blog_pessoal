package com.generation.blogpessoal.model;

/*
 * essa classe esta sendo criada apenas para que a api conseguir transitar as informações do usuário com o
 * token sem persistir no banco de dados o token
 */
public class UsuarioLogin {

	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String foto;
	private String token;//diferença entre usuário x usuarioLogin
	/*
	 * não persistir o token no banco
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}