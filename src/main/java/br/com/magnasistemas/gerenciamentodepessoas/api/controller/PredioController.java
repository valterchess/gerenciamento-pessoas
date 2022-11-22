package br.com.magnasistemas.gerenciamentodepessoas.api.controller;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Predio;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.locations.PredioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/predios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PredioController {
    @Autowired
    private PredioRepository predioRepository;

    @GetMapping
    public ResponseEntity<List<Predio>> getAll(){
        return ResponseEntity.ok(predioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Predio> postPredio(@Valid @RequestBody Predio predio){
        return ResponseEntity.status(HttpStatus.CREATED).body(predioRepository.save(predio));
    }
    @PutMapping
    public ResponseEntity<Predio> putPredio(@RequestBody Predio predio){
        var present = predioRepository.findById(predio.getId()).isPresent();
        if (present){
            return ResponseEntity.status(HttpStatus.OK).body(predioRepository.save(predio));
        }
        else return ResponseEntity.notFound().build();
    }
}
