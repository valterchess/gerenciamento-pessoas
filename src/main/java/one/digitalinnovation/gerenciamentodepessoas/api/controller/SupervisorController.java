package one.digitalinnovation.gerenciamentodepessoas.api.controller;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.*;
import one.digitalinnovation.gerenciamentodepessoas.domain.repository.*;
import one.digitalinnovation.gerenciamentodepessoas.domain.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/equipe/{id}")
    public ResponseEntity<List<Funcionario>> equipe(@PathVariable long id){
        return ResponseEntity.ok(supervisorRepository.findById(id).get().getFuncionario());
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> funcionarios(){
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @PostMapping("/post/funcionario")
    public ResponseEntity<Funcionario> postFuncionario(@Valid @RequestBody Funcionario funcionario){
        return supervisorService.cadastroFuncionario(funcionario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
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