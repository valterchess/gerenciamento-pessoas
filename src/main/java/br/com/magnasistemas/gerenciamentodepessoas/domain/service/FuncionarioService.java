package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Saida;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EntradaService entradaService;
	
	@Autowired
	private SaidaService saidaService;

	public Entrada novaEntradaFuncionario(Long id) {
		return funcionarioRepository.findById(id).map(entradaService::novaEntrada)
				.orElseThrow(() -> new NullPointerException());
	}
	
	public Saida novaSaidaFuncionario(Long id) {
		return funcionarioRepository.findById(id).map(saidaService::novaSaida)
				.orElseThrow(() -> new NullPointerException());
	}
}