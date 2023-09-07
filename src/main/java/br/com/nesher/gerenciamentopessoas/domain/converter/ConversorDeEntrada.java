package br.com.nesher.gerenciamentopessoas.domain.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.nesher.gerenciamentopessoas.domain.entity.Entrada;
import br.com.nesher.gerenciamentopessoas.domain.vo.EntradaVO;

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
