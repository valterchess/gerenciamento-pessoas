package one.digitalinnovation.gerenciamentodepessoas.controller;

import one.digitalinnovation.gerenciamentodepessoas.model.Entrada;
import one.digitalinnovation.gerenciamentodepessoas.model.Funcionario;
import one.digitalinnovation.gerenciamentodepessoas.model.Saida;
import one.digitalinnovation.gerenciamentodepessoas.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/entrada/{id}")
    public ResponseEntity<?> entrada(@PathVariable long id){
            return funcionarioRepository.findById(id).map(resposta ->{
                Entrada entrada = new Entrada(LocalDate.now(Clock.systemDefaultZone()), resposta);
                resposta.setEntrada(entrada);
                return ResponseEntity.status(HttpStatus.OK).build();
            }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/saida/{id}")
    public ResponseEntity<?> saida(@PathVariable long id){
        return funcionarioRepository.findById(id).map(resposta ->{
            Saida saida = new Saida(LocalDate.now(Clock.systemDefaultZone()), resposta);
            resposta.setSaida(saida);
            return ResponseEntity.status(HttpStatus.OK).build();
        }).orElse(ResponseEntity.notFound().build());
    }
}