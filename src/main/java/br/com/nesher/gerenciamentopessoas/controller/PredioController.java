package br.com.nesher.gerenciamentopessoas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nesher.gerenciamentopessoas.domain.entity.Predio;
import br.com.nesher.gerenciamentopessoas.repository.PredioRepository;

@RestController
@RequestMapping("/predios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PredioController {
	@Autowired
	private PredioRepository predioRepository;

	@GetMapping
	public ResponseEntity<List<Predio>> getAll() {
		return ResponseEntity.ok(predioRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Predio> postPredio(@Valid @RequestBody Predio predio) {
		return ResponseEntity.status(HttpStatus.CREATED).body(predioRepository.save(predio));
	}

	@PutMapping
	public ResponseEntity<Predio> putPredio(@RequestBody Predio predio) {
		var present = predioRepository.findById(predio.getId()).isPresent();
		if (present) {
			return ResponseEntity.status(HttpStatus.OK).body(predioRepository.save(predio));
		} else
			return ResponseEntity.notFound().build();
	}
}
