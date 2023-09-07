package br.com.nesher.gerenciamentopessoas.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.nesher.gerenciamentopessoas.domain.entity.Conjunto;
import br.com.nesher.gerenciamentopessoas.repository.ConjuntoRepository;

@RestController
@RequestMapping("/conjuntos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConjuntoController {
	
	@Autowired
	private ConjuntoRepository conjuntoRepository;

	@GetMapping
	public ResponseEntity<List<Conjunto>> getAll() {
		return ResponseEntity.ok(conjuntoRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Conjunto> postConjunto(@Valid @RequestBody Conjunto conjunto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(conjuntoRepository.save(conjunto));
	}

	@PutMapping
	public ResponseEntity<Conjunto> putConjunto(@RequestBody Conjunto conjunto) {
		var present = conjuntoRepository.findById(conjunto.getId()).isPresent();
		if (present) {
			return ResponseEntity.status(HttpStatus.OK).body(conjuntoRepository.save(conjunto));
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delConjunuto(@PathVariable long id) {
		var present = conjuntoRepository.findById(id).isPresent();
		if (present) {
			conjuntoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.notFound().build();
	}
}
