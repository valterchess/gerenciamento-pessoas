package br.com.nesher.gerenciamentopessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nesher.gerenciamentopessoas.domain.entity.Saida;
import br.com.nesher.gerenciamentopessoas.domain.vo.EntradaVO;
import br.com.nesher.gerenciamentopessoas.repository.FuncionarioRepository;

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