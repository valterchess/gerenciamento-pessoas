package br.com.magnasistemas.gerenciamentodepessoas.api.controller;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Endereco;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.locations.EnderecoRepository;
import br.com.magnasistemas.gerenciamentodepessoas.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Endereco> postEndereco(@PathVariable String cep, @PathVariable String num){
        var endereco = enderecoService.getEndereco(cep);
        endereco.setNumero(num);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoRepository.save(endereco));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Endereco> putEndereco(@RequestBody Endereco endereco){
        var present = enderecoRepository.findById(endereco.getId()).isPresent();
        if (present){
            return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
        }
        else return ResponseEntity.notFound().build();
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