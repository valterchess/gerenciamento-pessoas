package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Gerente;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.GerenteLogin;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Supervisor;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.GerenteRepository;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.SupervisorRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;

    public Optional<Gerente> cadastroGerente(Gerente gerente){
        if (gerenteRepository.findByEmail(gerente.getEmail()).isPresent()) {
            return Optional.empty();
        }
        gerente.setSenha(criptografaSenha(gerente.getSenha()));
        return Optional.of(gerenteRepository.save(gerente));
    }

    public Optional<Supervisor> cadastroSupervisor(Supervisor supervisor){
        if (supervisorRepository.findByEmail(supervisor.getEmail()).isPresent())
            return Optional.empty();
        supervisor.setSenha(criptografaSenha(supervisor.getSenha()));
        return Optional.of(supervisorRepository.save(supervisor));
    }

    public Optional<Gerente> atualizarGerente(Gerente gerente){
        if (gerenteRepository.findById(gerente.getId()).isPresent()){
            return compararGerente(gerente);
        }
        return Optional.empty();
    }

    public Optional<Supervisor> atualizarSupervisor(Supervisor supervisor){
        if (supervisorRepository.findById(supervisor.getId()).isPresent()){
            return compararSupervisor(supervisor);
        }
        return Optional.empty();
    }

    public Optional<GerenteLogin> autenticarGerente(GerenteLogin gerenteLogin){
        Optional<Gerente> gerenciador = gerenteRepository.findByEmail(gerenteLogin.getEmail());
        if(gerenciador.isPresent()){
            if(compararSenhas(gerenteLogin.getSenha(), gerenciador.get().getSenha())){
                String token = gerarBasicToken(gerenciador.get().getEmail(), gerenteLogin.getSenha());
                gerenteLogin.setId(gerenciador.get().getId());
                gerenteLogin.setNome(gerenciador.get().getNome());
                gerenteLogin.setSenha(gerenciador.get().getSenha());
                gerenteLogin.setToken(token);
                return Optional.of(gerenteLogin);
            }
        }
        return Optional.empty();
    }

    /*
     * Inicio dos métodos privados
     */

    private String gerarBasicToken(String usuario, String senha){
        String tokenBase = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(tokenBase64);
    }
    // Existe um erro aqui
    private Optional<Supervisor> compararSupervisor(Supervisor supervisor){
        var supervisorOp = supervisorRepository.findByEmail(supervisor.getEmail());
        var isPresent = supervisorOp.isPresent();
        if (isPresent && supervisor.getId() != supervisorOp.get().getId()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Supervisor já existe!", null);
        }
        supervisor.setSenha(criptografaSenha(supervisor.getSenha()));
        return Optional.of(supervisorRepository.save(supervisor));
    }

    private boolean compararSenhas(String senhaDigitada, String senhaDoBanco){
        var encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada,senhaDoBanco);
    }

    private Optional<Gerente> compararGerente(Gerente gerente){
        var gerenteOp = gerenteRepository.findByEmail(gerente.getEmail());
        var isPresent = gerenteOp.isPresent();
        if (isPresent && gerente.getId() != gerenteOp.get().getId()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Gerente já existe!", null);
        }
        gerente.setSenha(criptografaSenha(gerente.getSenha()));
        return Optional.of(gerenteRepository.save(gerente));
    }

    private String criptografaSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
}
