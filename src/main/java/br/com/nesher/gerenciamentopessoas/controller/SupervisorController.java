package br.com.nesher.gerenciamentopessoas.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nesher.gerenciamentopessoas.domain.entity.Funcionario;
import br.com.nesher.gerenciamentopessoas.domain.entity.SupervisorLogin;
import br.com.nesher.gerenciamentopessoas.domain.vo.FuncionarioVO;
import br.com.nesher.gerenciamentopessoas.repository.FuncionarioRepository;
import br.com.nesher.gerenciamentopessoas.repository.SupervisorRepository;
import br.com.nesher.gerenciamentopessoas.service.ArquivoService;
import br.com.nesher.gerenciamentopessoas.service.SupervisorService;

@RestController
@RequestMapping("/supervisor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private ArquivoService arquivoService;

	private Logger logger = Logger.getLogger("arquivo");

	@GetMapping("/equipe/{id}")
	public ResponseEntity<List<Funcionario>> getEquipe(@PathVariable long id) {
		return ResponseEntity.ok(supervisorRepository.findById(id).get().getFuncionario());
	}

	@PostMapping("/logar")
	public ResponseEntity<SupervisorLogin> login(@RequestBody SupervisorLogin supervisor) {
		return supervisorService.autenticarSupervisor(supervisor).map(resposta -> ResponseEntity.ok().body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> getFuncionarios() {
		return ResponseEntity.ok(funcionarioRepository.findAll());
	}

	@PostMapping("/funcionario")
	public ResponseEntity<Funcionario> postFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return supervisorService.cadastroFuncionario(funcionario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/funcionario/{codigo}")
	public ResponseEntity<FuncionarioVO> putFuncionario(@Valid @RequestBody Funcionario funcionario,
			@PathVariable String codigo) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(supervisorService.atualizarFuncionario(funcionario, codigo));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<?> delFuncionario(@PathVariable long id) {
		return funcionarioRepository.findById(id).map(resposta -> {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "/arquivo", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> postFotoFuncionario(@RequestPart("file") MultipartFile arquivo) {
		logger.info("Iniciando chamada do método");
		if (arquivo != null && !arquivo.getOriginalFilename().isEmpty()) {
			logger.info("Chamando método de upload do arquivo");
			arquivoService.write(arquivo);
			logger.info("Execução bem sucedida");
			return ResponseEntity.status(201).body("Arquivo Salvo");
		} else {
			logger.info("Falha na execução");
			return ResponseEntity.status(400).build();
		}
	}
}