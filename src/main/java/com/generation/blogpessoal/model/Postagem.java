package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //essa classe vai se tornar uma entidade do banco de dados (tabela)
@Table(name="tb_postagens") //nomeando a tabela no banco de dados
public class Postagem {

	@Id //tornar o campo uma chave primaria no banco
	@GeneratedValue(strategy=GenerationType.IDENTITY) //tornando a chave primaria auto increment 
	 private Long id;
	
	
	@NotBlank(message = "O atributo TITULO é obrigatório!") //validantion = validar nosso atributo para que ele nao deixe de ser passado (not null e nao vazio) 
	@Size(min = 5, max = 100, message = "O atributo TITULO deve ter no minimo 5 caracteres e no máximo 100!") //minimo e maximo caracteres a ser inserido 
	private String titulo;
	
	
	@NotBlank(message = "O atributo TEXTO é obrigatório")
	@Size(min = 10, max = 1000, message = "O atributo TEXTO deve ter no minimo 10 caracteres e no máximo 1000")
	private String texto;
	
	
	@UpdateTimestamp //sozinho ele vai entender o horario do banco de dados para colocar na postagem
	private LocalDateTime data;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public LocalDateTime getData() {
		return data;
	}


	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
