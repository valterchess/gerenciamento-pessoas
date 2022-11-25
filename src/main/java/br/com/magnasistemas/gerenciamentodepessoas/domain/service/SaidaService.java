package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Saida;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.SaidaRepository;

public class SaidaService {
	@Autowired
	private SaidaRepository saidaRepository;

	public Saida novaSaida(Funcionario funcionario) {
		Saida saida = new Saida();
		saida.setSaida(LocalDateTime.now(Clock.systemDefaultZone()));
		saida.setFuncionario(funcionario);
		return saidaRepository.save(saida);
	}
}
