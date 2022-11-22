package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.locations.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
