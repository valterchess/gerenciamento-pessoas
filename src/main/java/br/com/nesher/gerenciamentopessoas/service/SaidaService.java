package br.com.nesher.gerenciamentopessoas.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nesher.gerenciamentopessoas.domain.entity.Funcionario;
import br.com.nesher.gerenciamentopessoas.domain.entity.Saida;
import br.com.nesher.gerenciamentopessoas.repository.SaidaRepository;

@Service
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
