package br.com.nesher.gerenciamentopessoas.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nesher.gerenciamentopessoas.domain.converter.ConversorDeEntrada;
import br.com.nesher.gerenciamentopessoas.domain.entity.Entrada;
import br.com.nesher.gerenciamentopessoas.domain.entity.Funcionario;
import br.com.nesher.gerenciamentopessoas.domain.vo.EntradaVO;
import br.com.nesher.gerenciamentopessoas.repository.EntradaRepository;

@Service
public class EntradaService {
	@Autowired
	private EntradaRepository entradaRepository;
	

	public EntradaVO novaEntrada(Funcionario funcionario) {
		Entrada entrada = new Entrada();
		entrada.setEntrada(LocalDateTime.now(Clock.systemDefaultZone()));
		entrada.setFuncionario(funcionario);
		Entrada entradaSalva = entradaRepository.save(entrada);
		EntradaVO entradaVO = ConversorDeEntrada.converteEntradaEntityEmEntradaVO(entradaSalva);
		return entradaVO;
	}

}