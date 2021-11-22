package one.digitalinnovation.gerenciamentodepessoas.controller;

import one.digitalinnovation.gerenciamentodepessoas.model.CadastroFuncionario;
import one.digitalinnovation.gerenciamentodepessoas.model.CadastroGerenciador;
import one.digitalinnovation.gerenciamentodepessoas.model.GerenciadorLogin;
import one.digitalinnovation.gerenciamentodepessoas.repository.CadastroFuncionarioRepository;
import one.digitalinnovation.gerenciamentodepessoas.repository.CadastroGerenciadorRepository;
import one.digitalinnovation.gerenciamentodepessoas.service.CadastroGerenciadorService;
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
public class CadastroGerenciadorController {
    @Autowired
    private CadastroGerenciadorService gerenciadorService;
    @Autowired
    private CadastroGerenciadorRepository gerenciadorRepository;
    @Autowired
    private CadastroFuncionarioRepository funcionarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<CadastroGerenciador>> getAll(){
        return ResponseEntity.ok(gerenciadorRepository.findAll());
    }

    @GetMapping("/gerenciador/{funcionario}")
    public ResponseEntity<List<CadastroFuncionario>> GetAllfuncionario(){
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    @PostMapping("/cadastrar/gerenciador")
    public ResponseEntity<CadastroGerenciador> postGerenciador(@Valid @RequestBody CadastroGerenciador gerenciador){
        return gerenciadorService.cadastroGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
    @PostMapping("/cadastrar/funcionario")
    public ResponseEntity<CadastroFuncionario> postFuncionario(@Valid @RequestBody CadastroFuncionario funcionario){
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
    public ResponseEntity<CadastroGerenciador> putGerenciador(@Valid @RequestBody CadastroGerenciador gerenciador){
        return gerenciadorService.atualizarGerenciador(gerenciador)
                .map(resposta -> ResponseEntity.ok().body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/atualizar/funcionario")
    public ResponseEntity<CadastroFuncionario> putFuncionario(@Valid @RequestBody CadastroFuncionario funcionario){
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