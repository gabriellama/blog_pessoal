package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		//find by id - SELECT * FROM tb_postagens WHERE id = 1
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resposta-> ResponseEntity.status(HttpStatus.OK)
				.body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		postagemRepository.deleteById(id);
	}
	
	@ManyToOne // criar o relacionamento de que muitas postagens pode pertencer a um tema
	@JsonIgnoreProperties("postagem") // ignorando as postagens na lista de tema, pra não dar um loop infinito
	private Tema tema;
}
