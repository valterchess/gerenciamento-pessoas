package one.digitalinnovation.gerenciamentodepessoas.service;

import one.digitalinnovation.gerenciamentodepessoas.model.*;
import one.digitalinnovation.gerenciamentodepessoas.repository.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class CadastroGerenciadorService {

    @Autowired
    private CadastroGerenciadorRepository gerenciadorRepository;
    @Autowired
    private CadastroFuncionarioRepository funcionarioRepository;

    public Optional<CadastroGerenciador> cadastroGerenciador(CadastroGerenciador gerenciador){
        if (gerenciadorRepository.findByUsuario(gerenciador.getUsuario()).isPresent())
            return Optional.empty();
        gerenciador.setSenha(criptografaSenha(gerenciador.getSenha()));
        return Optional.of(gerenciadorRepository.save(gerenciador));
    }

    public Optional<CadastroFuncionario> cadastroFuncionario(CadastroFuncionario funcionario){
        if (funcionarioRepository.findByCpf(funcionario.getCpf()).isPresent())
            return Optional.empty();
        return Optional.of(funcionarioRepository.save(funcionario));
    }

    public Optional<CadastroGerenciador> atualizarGerenciador(CadastroGerenciador gerenciador){
        if (gerenciadorRepository.findById(gerenciador.getId()).isPresent()){
            return compararGerenciador(gerenciador);
        }
        return Optional.empty();
    }

    public Optional<CadastroFuncionario> atualizarFuncionario(CadastroFuncionario funcionario) {
        if (gerenciadorRepository.findById(funcionario.getId()).isPresent()){
            return compararFuncionario(funcionario);
        }
        return Optional.empty();
    }


    public Optional<GerenciadorLogin> autenticarGerenciador(Optional<GerenciadorLogin> gerenciadorLogin){
        Optional<CadastroGerenciador> gerenciador = gerenciadorRepository.findByUsuario(gerenciadorLogin.get().getUsuario());
        if(gerenciador.isPresent()){
            if(compararSenhas(gerenciadorLogin.get().getSenha(), gerenciador.get().getSenha())){
                String token = gerarBasicToken(gerenciador.get().getUsuario(), gerenciadorLogin.get().getSenha());
                gerenciadorLogin.get().setId(gerenciador.get().getId());
                gerenciadorLogin.get().setNome(gerenciador.get().getNome());
                gerenciadorLogin.get().setSenha(gerenciador.get().getSenha());
                gerenciadorLogin.get().setToken(token);
                return gerenciadorLogin;
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

    private String criptografaSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private Optional<CadastroGerenciador> compararGerenciador(CadastroGerenciador gerenciador){
        var gerenciadorOp = gerenciadorRepository.findByUsuario(gerenciador.getUsuario());
        var isPresent = gerenciadorOp.isPresent();
        if (isPresent && gerenciador.getId() != gerenciadorOp.get().getId()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Gerenciador já existe!", null);
        }
        gerenciador.setSenha(criptografaSenha(gerenciador.getSenha()));
        return Optional.of(gerenciadorRepository.save(gerenciador));
    }

    private Optional<CadastroFuncionario> compararFuncionario(CadastroFuncionario funcionario) {
        Optional<CadastroFuncionario> funcionarioOp = funcionarioRepository.findByCpf(funcionario.getCpf());
        var isPresent = funcionarioOp.isPresent();
        if (isPresent && funcionario.getCpf() != funcionarioOp.get().getId()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Funcionario já existe!", null);
        }
        return Optional.of(funcionarioRepository.save(funcionario));
    }

    private boolean compararSenhas(String senhaDigitada, String senhaDoBanco){
        var encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada,senhaDoBanco);
    }

}

