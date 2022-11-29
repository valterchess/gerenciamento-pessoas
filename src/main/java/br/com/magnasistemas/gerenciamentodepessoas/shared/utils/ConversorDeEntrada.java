package br.com.magnasistemas.gerenciamentodepessoas.shared.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.magnasistemas.gerenciamentodepessoas.domain.dto.contributors.EntradaVO;
import br.com.magnasistemas.gerenciamentodepessoas.domain.model.contributors.Entrada;

@Component
public abstract class ConversorDeEntrada {
	
	public static EntradaVO converteEntradaEntityEmEntradaVO(Entrada entrada) {
		EntradaVO entradaVO = new EntradaVO();
		entradaVO.setId(entrada.getId());
		String horarioDeEntrada = formataDataDeEntrada(entrada.getEntrada());
		entradaVO.setEntrada(horarioDeEntrada);
		entradaVO.setNomeFuncionario(entrada.getFuncionario().getNome());
		entradaVO.setCredencialFuncionario(entrada.getFuncionario().getCredencial());
		return entradaVO;
	}
	
	private static String formataDataDeEntrada(LocalDateTime entrada) {
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
