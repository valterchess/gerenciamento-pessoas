package one.digitalinnovation.gerenciamentodepessoas.controller;

import one.digitalinnovation.gerenciamentodepessoas.model.Funcionario;
import one.digitalinnovation.gerenciamentodepessoas.model.Gerenciador;
import one.digitalinnovation.gerenciamentodepessoas.model.GerenciadorLogin;
import one.digitalinnovation.gerenciamentodepessoas.repository.FuncionarioRepository;
import one.digitalinnovation.gerenciamentodepessoas.repository.GerenciadorRepository;
import one.digitalinnovation.gerenciamentodepessoas.service.GerenciadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerenciador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GerenciadorController {
    @Autowired
    private GerenciadorService gerenciadorService;
    @Autowired
    private GerenciadorRepository gerenciadorRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Gerenciador>> getAll(){
        return ResponseEntity.ok(gerenciadorRepository.findAll());
    }

    @GetMapping("/gerenciador/{funcionario}")
    public ResponseEntity<List<Funcionario>> GetAllfuncionario(){
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @PostMapping("/cadastrar/gerenciador")
    public ResponseEntity<Gerenciador> postGerenciador(@Valid @RequestBody Gerenciador gerenciador){
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
    public ResponseEntity<GerenciadorLogin> login(@RequestBody Optional<GerenciadorLogin> gerenciador){
        return gerenciadorService.autenticarGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Gerenciador> putGerenciador(@Valid @RequestBody Gerenciador gerenciador){
        return gerenciadorService.atualizarGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/atualizar/funcionario")
    public ResponseEntity<Funcionario> putFuncionario(@Valid @RequestBody Funcionario funcionario){
        return gerenciadorService.atualizarFuncionario(funcionario)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return gerenciadorRepository.findById(id)
                .map(resposta -> {
                    gerenciadorRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/funcionario/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable long id){
        return funcionarioRepository.findById(id)
                .map(resposta -> {
                    funcionarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}