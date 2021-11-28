package one.digitalinnovation.gerenciamentodepessoas.controller;

import one.digitalinnovation.gerenciamentodepessoas.repository.CadastroFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CadastroFuncionarioController {
    @Autowired
    private CadastroFuncionarioRepository funcionarioRepository;

    @PostMapping("/entrada/{id}")
    public ResponseEntity<?> entrada(@PathVariable long id){
            return funcionarioRepository.findById(id).map(resposta ->{
                resposta.setEntrada(LocalDate.now(Clock.systemDefaultZone()));
                return ResponseEntity.status(HttpStatus.OK).build();
            }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/saida/{id}")
    public ResponseEntity<?> saida(@PathVariable long id){
        return funcionarioRepository.findById(id).map(resposta ->{
            resposta.setSaida(LocalDate.now(Clock.systemDefaultZone()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }).orElse(ResponseEntity.notFound().build());
    }
}