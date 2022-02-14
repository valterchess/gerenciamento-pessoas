package one.digitalinnovation.gerenciamentodepessoas.api.controller;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Conjunto;
import one.digitalinnovation.gerenciamentodepessoas.domain.repository.locations.ConjuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conjuntos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConjuntoController {
    @Autowired
    private ConjuntoRepository conjuntoRepository;

    @GetMapping
    public ResponseEntity<List<Conjunto>> getAll(){
        return ResponseEntity.ok(conjuntoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Conjunto> postConjunto(@Valid @RequestBody Conjunto conjunto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conjuntoRepository.save(conjunto));
    }

    @PutMapping
    public ResponseEntity<Conjunto> putConjunto(@RequestBody Conjunto conjunto){
        var present = conjuntoRepository.findById(conjunto.getId()).isPresent();
        if (present){
            return ResponseEntity.status(HttpStatus.OK).body(conjuntoRepository.save(conjunto));
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delConjunuto(@PathVariable long id){
        var present = conjuntoRepository.findById(id).isPresent();
        if (present) {
            conjuntoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
