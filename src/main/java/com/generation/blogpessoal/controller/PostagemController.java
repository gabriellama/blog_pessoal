package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController // anotação que diz para a spring que essa é uma controladora de rotas e acesso de metodos
@RequestMapping("/postagens") //rota para chegar nessa classe "insomnia"
@CrossOrigin(origins = "*", allowedHeaders = "*") //liberar o acesso a outras maquinas /allowedHeaders - liberar passagem 
public class PostagemController {

	@Autowired //injeçaõ de dependencias - instanciar a classe PostagemRepository 
	private PostagemRepository postagemRepository;
	
	
	@GetMapping //define o verbo http que atende esse metodo
	public ResponseEntity<List<Postagem>> getAll() {
		//responsiveentity - classe 
		return ResponseEntity.ok(postagemRepository.findAll());
		
		
	}
	
}
