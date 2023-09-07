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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nesher.gerenciamentopessoas.domain.dto.ManagerDTO;
import br.com.nesher.gerenciamentopessoas.domain.entity.Funcionario;
import br.com.nesher.gerenciamentopessoas.domain.entity.Gerente;
import br.com.nesher.gerenciamentopessoas.domain.entity.GerenteLogin;
import br.com.nesher.gerenciamentopessoas.domain.entity.Supervisor;
import br.com.nesher.gerenciamentopessoas.domain.vo.FuncionarioVO;
import br.com.nesher.gerenciamentopessoas.domain.vo.ManagerFilterVO;
import br.com.nesher.gerenciamentopessoas.repository.FuncionarioRepository;
import br.com.nesher.gerenciamentopessoas.repository.GerenteRepository;
import br.com.nesher.gerenciamentopessoas.repository.SupervisorRepository;
import br.com.nesher.gerenciamentopessoas.service.GerenteService;
import br.com.nesher.gerenciamentopessoas.service.SupervisorService;

@RestController
@RequestMapping("/gerentes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GerenteController {

	@Autowired
	private GerenteService gerenteService;
	@Autowired
	private SupervisorService supervisorService;
	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private GerenteRepository gerenteRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ManagerDTO>> findManagers(final ManagerFilterVO filter) {
		return gerenteService.findManagersByFilter(filter);
	}

	@GetMapping("/supervisores")
	public ResponseEntity<List<Supervisor>> getSupervisores() {
		return ResponseEntity.ok(supervisorRepository.findAll());
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> getfuncionarios() {
		return ResponseEntity.ok(funcionarioRepository.findAll());
	}

	@GetMapping("/equipes/{id}")
	public ResponseEntity<List<Supervisor>> getEquipe(@PathVariable long id) {
		return ResponseEntity.ok(gerenteRepository.findById(id).get().getSupervisor());
	}

	@GetMapping("/subequipe/{id}")
	public ResponseEntity<List<Funcionario>> getSub(@PathVariable long id) {
		return ResponseEntity.ok(supervisorRepository.findById(id).get().getFuncionario());
	}

	@PostMapping("/cadastrar/gerente")
	public ResponseEntity<Gerente> postGerente(@Valid @RequestBody Gerente gerente) {
		return gerenteService.cadastroGerente(gerente)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/cadastrar/supervisor")
	public ResponseEntity<Supervisor> postSupervisor(@Valid @RequestBody Supervisor supervisor) {
		return gerenteService.cadastroSupervisor(supervisor)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/cadastrar/operacional")
	public ResponseEntity<Funcionario> postFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return supervisorService.cadastroFuncionario(funcionario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/logar")
	public ResponseEntity<GerenteLogin> login(@RequestBody GerenteLogin gerenteLogin) {
		return gerenteService.autenticarGerente(gerenteLogin).map(resposta -> ResponseEntity.ok().body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualizar/gerente")
	public ResponseEntity<Gerente> putGerente(@Valid @RequestBody Gerente gerente) {
		return gerenteService.atualizarGerente(gerente).map(resposta -> ResponseEntity.ok().body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Existe um erro aqui e na Service
	@PutMapping("/put/supervisor")
	public ResponseEntity<Supervisor> putSupervisor(@Valid @RequestBody Supervisor supervisor) {
		return gerenteService.atualizarSupervisor(supervisor).map(resposta -> ResponseEntity.ok().body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PutMapping("/put/funcionario/{codigo}")
	public ResponseEntity<FuncionarioVO> putFuncionario(@Valid @RequestBody Funcionario funcionario, String codigo) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(supervisorService.atualizarFuncionario(funcionario, codigo));
		} catch (NullPointerException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/del/gerente/{id}")
	public ResponseEntity<?> delGerente(@PathVariable long id) {
		return gerenteRepository.findById(id).map(resposta -> {
			gerenteRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/del/supervisor/{id}")
	public ResponseEntity<?> delSupervisor(@PathVariable long id) {
		return supervisorRepository.findById(id).map(resposta -> {
			supervisorRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/del/funcionario/{id}")
	public ResponseEntity<?> delFuncionario(@PathVariable long id) {
		return funcionarioRepository.findById(id).map(resposta -> {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}