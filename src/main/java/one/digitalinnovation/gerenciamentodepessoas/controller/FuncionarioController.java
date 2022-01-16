package one.digitalinnovation.gerenciamentodepessoas.controller;

import one.digitalinnovation.gerenciamentodepessoas.model.Entrada;
import one.digitalinnovation.gerenciamentodepessoas.model.Funcionario;
import one.digitalinnovation.gerenciamentodepessoas.model.Saida;
import one.digitalinnovation.gerenciamentodepessoas.repository.EntradaRepository;
import one.digitalinnovation.gerenciamentodepessoas.repository.FuncionarioRepository;
import one.digitalinnovation.gerenciamentodepessoas.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    @PostMapping("/entrada/{id}")
    public ResponseEntity<?> entrada(@PathVariable long id){
            return funcionarioRepository.findById(id).map(resposta ->{
                Entrada entrada = new Entrada(LocalDateTime.now(Clock.systemDefaultZone()), resposta);
                entrada.setFuncionario(resposta);
                return ResponseEntity.status(HttpStatus.OK).body(entradaRepository.save(entrada));
            }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/saida/{id}")
    public ResponseEntity<?> saida(@PathVariable long id){
        return funcionarioRepository.findById(id).map(resposta ->{
            Saida saida = new Saida(LocalDateTime.now(Clock.systemDefaultZone()), resposta);
            return ResponseEntity.status(HttpStatus.OK).body(saidaRepository.save(saida));
        }).orElse(ResponseEntity.notFound().build());
    }
}