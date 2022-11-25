package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.EntradaRepository;

@Service
public class EntradaService {
	@Autowired
	private EntradaRepository entradaRepository;
	
	public Entrada novaEntrada(Funcionario funcionario) {
		Entrada entrada = new Entrada();
		entrada.setEntrada(LocalDateTime.now(Clock.systemDefaultZone()));
		entrada.setFuncionario(funcionario);
		return entradaRepository.save(entrada);
	}
}