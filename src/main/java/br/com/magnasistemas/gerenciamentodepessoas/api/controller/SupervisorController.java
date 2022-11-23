package br.com.magnasistemas.gerenciamentodepessoas.api.controller;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.SupervisorLogin;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.FuncionarioRepository;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.SupervisorRepository;
import br.com.magnasistemas.gerenciamentodepessoas.domain.service.ArquivoService;
import br.com.magnasistemas.gerenciamentodepessoas.domain.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

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
    public ResponseEntity<List<Funcionario>> getEquipe(@PathVariable long id){
        return ResponseEntity.ok(supervisorRepository.findById(id).get().getFuncionario());
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> getFuncionarios(){
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @PostMapping("/post/funcionario")
    public ResponseEntity<Funcionario> postFuncionario(@Valid @RequestBody Funcionario funcionario){
        return supervisorService.cadastroFuncionario(funcionario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
    
    @PostMapping(value = "/arquivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> postFotoFuncionario(@RequestPart("file") MultipartFile arquivo) {
    	logger.info("Iniciando chamada do método");
    	if(arquivo != null && !arquivo.getOriginalFilename().isEmpty()) {
    		logger.info("Chamando método de upload do arquivo");
    		arquivoService.write(arquivo);
    		logger.info("Execução bem sucedida");
    		return ResponseEntity.status(201).body("Arquivo Salvo"); 
    	}
    	else {
    		logger.info("Falha na execução");
    		return ResponseEntity.status(400).build();
    	}
    }

    @PostMapping("/logar")
    public ResponseEntity<SupervisorLogin> login(@RequestBody SupervisorLogin supervisor){
        return supervisorService.autenticarSupervisor(supervisor)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/put/funcionario")
    public ResponseEntity<Funcionario> putFuncionario(@Valid @RequestBody Funcionario funcionario){
        return supervisorService.atualizarFuncionario(funcionario)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/del/funcionario/{id}")
    public ResponseEntity<?> delFuncionario(@PathVariable long id){
        return funcionarioRepository.findById(id)
                .map(resposta -> {
                    funcionarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}