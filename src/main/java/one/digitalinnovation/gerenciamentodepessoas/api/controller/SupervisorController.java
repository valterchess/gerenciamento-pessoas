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
    private SupervisorService gerenciadorService;
    @Autowired
    private SupervisorRepository gerenciadorRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Supervisor>> getAll(){
        return ResponseEntity.ok(gerenciadorRepository.findAll());
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> GetAllfuncionario(){
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @GetMapping("/equipe/{id}")
    public ResponseEntity<List<Funcionario>> GetAllEquipe(@PathVariable long id){
        return ResponseEntity.ok(gerenciadorRepository.findById(id).get().getFuncionario());
    }

    @PostMapping("/cadastrar/supervisor")
    public ResponseEntity<Supervisor> postGerenciador(@Valid @RequestBody Supervisor gerenciador){
        return gerenciadorService.cadastroGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
    @PostMapping("/cadastrar/funcionario")
    public ResponseEntity<Funcionario> postFuncionario(@Valid @RequestBody Funcionario funcionario){
        return gerenciadorService.cadastroFuncionario(funcionario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/logar")
    public ResponseEntity<SupervisorLogin> login(@RequestBody SupervisorLogin gerenciador){
        return gerenciadorService.autenticarGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/atualizar/supervisor")
    public ResponseEntity<Supervisor> putGerenciador(@Valid @RequestBody Supervisor gerenciador){
        return gerenciadorService.atualizarGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/atualizar/supervisor")
    public ResponseEntity<Funcionario> putFuncionario(@Valid @RequestBody Funcionario funcionario){
        return gerenciadorService.atualizarFuncionario(funcionario)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    //adicionar uma verificação do tipo de gerenciador na service
    // Se o gerenciador for do tipo "admin", autorizar. Se não, negar a requisição
    @DeleteMapping("/delete/supervisor/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return gerenciadorRepository.findById(id)
                .map(resposta -> {
                    gerenciadorRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    /***
     adicionar uma verificação na service. Se o gerenciador for do tipo "admin"
     ou o funcionário pertencer a equipe do responśavel, autorizar.
     Se não, negar a requisição
     */
    @DeleteMapping("/delete/funcionario/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable long id){
        return funcionarioRepository.findById(id)
                .map(resposta -> {
                    funcionarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}