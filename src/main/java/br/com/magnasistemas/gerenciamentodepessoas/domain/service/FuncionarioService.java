package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.dto.contributors.EntradaVO;
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

	public EntradaVO novaEntradaFuncionario(Long id) {
		return funcionarioRepository.findById(id)
				.map(entradaService::novaEntrada)
				.orElseThrow(NullPointerException::new);
	}

	public Saida novaSaidaFuncionario(Long id) {
		return funcionarioRepository.findById(id)
				.map(saidaService::novaSaida)
				.orElseThrow(NullPointerException::new);
	}
}