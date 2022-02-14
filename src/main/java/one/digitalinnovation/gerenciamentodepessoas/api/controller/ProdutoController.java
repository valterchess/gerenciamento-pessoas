package one.digitalinnovation.gerenciamentodepessoas.api.controller;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Produto;
import one.digitalinnovation.gerenciamentodepessoas.domain.repository.locations.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto){
        var present = produtoRepository.findById(produto.getId()).isPresent();
        if (present) {
            return ResponseEntity.ok().body(produtoRepository.save(produto));
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable long id){
        var present = produtoRepository.findById(id).isPresent();
        if (present) {
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
