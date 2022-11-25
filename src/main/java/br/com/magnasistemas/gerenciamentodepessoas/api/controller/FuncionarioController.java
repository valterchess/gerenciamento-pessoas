package br.com.magnasistemas.gerenciamentodepessoas.api.controller;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Saida;
import br.com.magnasistemas.gerenciamentodepessoas.domain.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping("/entrada/{id}")
	public ResponseEntity<Entrada> entrada(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.novaEntradaFuncionario(id));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(400).build();
		}
	}

	@PostMapping("/saida/{id}")
	public ResponseEntity<Saida> saida(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.novaSaidaFuncionario(id));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(400).build();
		}
	}
}