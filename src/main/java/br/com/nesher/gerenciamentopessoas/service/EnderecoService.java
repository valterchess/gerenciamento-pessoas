package br.com.nesher.gerenciamentopessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.nesher.gerenciamentopessoas.domain.entity.Endereco;
import reactor.core.publisher.Mono;

@Service
public class EnderecoService {

    @Autowired
    private WebClient webClient;

    public Endereco getEndereco(String cep) {
        Mono<Endereco> endereco = webClient
                .method(HttpMethod.GET)
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(Endereco.class);
        return endereco.block();
    }
}