package br.com.nesher.gerenciamentopessoas.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.nesher.gerenciamentopessoas.domain.entity.Endereco;
import br.com.nesher.gerenciamentopessoas.domain.entity.Funcionario;
import br.com.nesher.gerenciamentopessoas.domain.entity.Predio;
import br.com.nesher.gerenciamentopessoas.domain.entity.Supervisor;
import br.com.nesher.gerenciamentopessoas.domain.entity.SupervisorLogin;
import br.com.nesher.gerenciamentopessoas.domain.vo.FuncionarioVO;
import br.com.nesher.gerenciamentopessoas.repository.FuncionarioRepository;
import br.com.nesher.gerenciamentopessoas.repository.SupervisorRepository;

@Service
public class SupervisorService {

	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Optional<Funcionario> cadastroFuncionario(Funcionario funcionario) {
		constroiFuncionario(funcionario);
		if (funcionarioRepository.findByCredencial(funcionario.getCredencial()).isPresent())
			return Optional.empty();
		return Optional.of(funcionarioRepository.save(funcionario));
	}

	public FuncionarioVO atualizarFuncionario(Funcionario funcionario, String codigo) {
		return funcionarioRepository.findByCredencial(codigo)
		.map(funcionarioDB 
				-> salvaNovosDados(funcionario, funcionarioDB))
		.orElseThrow(NullPointerException::new);
	}

	public Optional<SupervisorLogin> autenticarSupervisor(SupervisorLogin supervisorLogin) {
		Optional<Supervisor> gerenciador = supervisorRepository.findByEmail(supervisorLogin.getEmail());
		if (gerenciador.isPresent()) {
			if (compararSenhas(supervisorLogin.getSenha(), gerenciador.get().getSenha())) {
				String token = gerarBasicToken(gerenciador.get().getEmail(), supervisorLogin.getSenha());
				supervisorLogin.setId(gerenciador.get().getId());
				supervisorLogin.setNome(gerenciador.get().getNome());
				supervisorLogin.setSenha(gerenciador.get().getSenha());
				supervisorLogin.setToken(token);
				return Optional.of(supervisorLogin);
			}
		}
		return Optional.empty();
	}

	/*
	 * Inicio dos métodos privados
	 */

	private String gerarBasicToken(String usuario, String senha) {
		String tokenBase = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(StandardCharsets.US_ASCII));
		return "Basic " + new String(tokenBase64);
	}

	private Optional<Funcionario> compararFuncionario(Funcionario funcionario) {
		Optional<Funcionario> funcionarioOp = funcionarioRepository.findByCredencial(funcionario.getCredencial());
		var isPresent = funcionarioOp.isPresent();
		if (isPresent && funcionario.getId() != funcionarioOp.get().getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Funcionario já existe!", null);
		}
		return Optional.of(funcionarioRepository.save(funcionario));
	}

	private void constroiFuncionario(Funcionario funcionario) {
		setor(funcionario);
		String credencial = geraCredencial(funcionario);
		System.out.println(credencial);
		funcionario.setCredencial(credencial);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) {
		var encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaDoBanco);
	}

	private String geraCredencial(Funcionario funcionario) {
		String random = String.valueOf((int) (Math.random() * 99));
		return "S-" + funcionario.getPredio() + ":" + funcionario.getNome().toLowerCase() + "-" + random;
	}

	private void setor(Funcionario funcionario) {
		Optional<Supervisor> gerenciador = supervisorRepository.findById(funcionario.getSupervisor().getId());
		Predio setor = gerenciador.get().getPredio();
		funcionario.setPredio(setor);
	}

	private FuncionarioVO salvaNovosDados(Funcionario funcionario, Funcionario funcionarioDB) {
		funcionario.setId(funcionarioDB.getId());
		funcionario = funcionarioRepository.save(funcionario);
		
		// separar trecho de código
		FuncionarioVO funcionarioVO = new FuncionarioVO();
		Endereco enderecoTrabalho = funcionario.getPredio().getConjunto().getEndereco();
		
		funcionarioVO.setId(funcionario.getId());
		funcionarioVO.setCredencial(funcionario.getCredencial());
		funcionarioVO.setEntrada(funcionario.getEntrada());
		funcionarioVO.setNome(funcionario.getNome());
		funcionarioVO.setNomeSupervisor(funcionario.getSupervisor().getNome());
		funcionarioVO.setLocalTrabalho(enderecoTrabalho);
		// transformar em saida VO
		funcionarioVO.setSaida(funcionario.getSaida());
		return funcionarioVO;
	}
}