package br.com.nesher.gerenciamentopessoas.controller;

import java.util.List;
import java.util.logging.Logger;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nesher.gerenciamentopessoas.domain.entity.Endereco;
import br.com.nesher.gerenciamentopessoas.repository.EnderecoRepository;
import br.com.nesher.gerenciamentopessoas.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {
	private Logger logger = Logger.getLogger("arquivo");

	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@GetMapping
	public ResponseEntity<List<Endereco>> getAll() {
		return ResponseEntity.ok(enderecoRepository.findAll());
	}

	@GetMapping("/buscacep/{cep}")
	public ResponseEntity<Endereco> getByCep(@PathVariable String cep) {
		return ResponseEntity.ok(enderecoRepository.findByCep(cep));
	}

	@GetMapping("/buscaddd/{ddd}")
	public ResponseEntity<List<Endereco>> getAllByDdd(@PathVariable String ddd) {
		return ResponseEntity.ok(enderecoRepository.findAllByDdd(ddd));
	}

	@GetMapping("/buscauf/{uf}")
	public ResponseEntity<List<Endereco>> getAllByUf(@PathVariable String uf) {
		return ResponseEntity.ok(enderecoRepository.findAllByUf(uf));
	}

	// melhorar esse método
	@PostMapping("/add/{cep}/{num}")
	public ResponseEntity<Endereco> postEndereco(@RequestParam("cep") String cep, @RequestParam("numero") String num) {
		logger.info(cep);
		var endereco = enderecoService.getEndereco(cep);
		endereco.setNumero(num);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Endereco> putEndereco(@RequestBody Endereco endereco) {
		var present = enderecoRepository.findById(endereco.getId()).isPresent();
		if (present) {
			return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delEndereco(@PathVariable long id) {
		return enderecoRepository.findById(id).map(resposta -> {
			enderecoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}