package one.digitalinnovation.gerenciamentodepessoas.api.controller;

import one.digitalinnovation.gerenciamentodepessoas.domain.model.locations.Endereco;
import one.digitalinnovation.gerenciamentodepessoas.domain.repository.locations.EnderecoRepository;
import one.digitalinnovation.gerenciamentodepessoas.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll(){
        return ResponseEntity.ok(enderecoRepository.findAll());
    }
    @GetMapping("/buscacep/{cep}")
    public ResponseEntity<Endereco> getByCep(@PathVariable String cep){
        return ResponseEntity.ok(enderecoRepository.findByCep(cep));
    }
    @GetMapping("/buscaddd/{ddd}")
    public ResponseEntity<List<Endereco>> getAllByDdd(@PathVariable String ddd){
        return ResponseEntity.ok(enderecoRepository.findAllByDdd(ddd));
    }
    @GetMapping("/buscauf/{uf}")
    public ResponseEntity<List<Endereco>> getAllByUf(@PathVariable String uf){
        return ResponseEntity.ok(enderecoRepository.findAllByUf(uf));
    }
    //melhorar esse método
    @PostMapping("/add/{cep}/{num}")
    public ResponseEntity<Endereco> postEndereco(@PathVariable String cep, String num){
        var endereco = enderecoService.getEndereco(cep);
        endereco.setNumero(num);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoRepository.save(endereco));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Endereco> putEndereco(@RequestBody Endereco endereco){
        if (enderecoRepository.findById(endereco.getId()).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delEndereco(@PathVariable long id){
        return enderecoRepository.findById(id)
                .map(resposta -> {
                    enderecoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}