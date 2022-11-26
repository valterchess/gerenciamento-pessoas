package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.dto.contributors.EntradaVO;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Funcionario;
import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.EntradaRepository;

@Service
public class EntradaService {
	@Autowired
	private EntradaRepository entradaRepository;

	public EntradaVO novaEntrada(Funcionario funcionario) {
		Entrada entrada = new Entrada();
		entrada.setEntrada(LocalDateTime.now(Clock.systemDefaultZone()));
		entrada.setFuncionario(funcionario);
		Entrada entradaSalva = entradaRepository.save(entrada);
		EntradaVO entradaVO = converteEntradaEntityEmEntradaVO(entradaSalva);
		return entradaVO;
	}

	private EntradaVO converteEntradaEntityEmEntradaVO(Entrada entrada) {
		EntradaVO entradaVO = new EntradaVO();
		entradaVO.setId(entrada.getId());
		String horarioDeEntrada = formataDataDeEntrada(entrada.getEntrada());
		entradaVO.setEntrada(horarioDeEntrada);
		entradaVO.setNomeFuncionario(entrada.getFuncionario().getNome());
		entradaVO.setCredencialFuncionario(entrada.getFuncionario().getCredencial());
		return entradaVO;
	}

	private String formataDataDeEntrada(LocalDateTime entrada) {
		int ano = entrada.getYear();
		int mes = Integer.parseInt(entrada.getMonth().toString());
		int dia = entrada.getDayOfMonth();
		int horas = entrada.getHour();
		int minutos = entrada.getMinute();
		StringBuilder data = new StringBuilder();
		data.append(ano + "/" + mes + "/" + dia + "-" + horas + ":" + minutos);
		return data.toString();
	}
}