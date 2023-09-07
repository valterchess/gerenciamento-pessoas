package br.com.nesher.gerenciamentopessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nesher.gerenciamentopessoas.domain.entity.Saida;
import br.com.nesher.gerenciamentopessoas.domain.vo.EntradaVO;
import br.com.nesher.gerenciamentopessoas.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping("/entrar/{id}")
	public ResponseEntity<EntradaVO> entrada(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.novaEntradaFuncionario(id));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/sair/{id}")
	public ResponseEntity<Saida> saida(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.novaSaidaFuncionario(id));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}