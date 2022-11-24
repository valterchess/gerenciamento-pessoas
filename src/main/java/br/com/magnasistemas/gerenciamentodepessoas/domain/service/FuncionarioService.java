package br.com.magnasistemas.gerenciamentodepessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.gerenciamentodepessoas.domain.repository.contributors.EntradaRepository;
import br.com.magnasistemas.gerenciamentodepessoas.domain.vo.contributors.EntradaVO;

@Service
public class FuncionarioService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	
	public EntradaVO adicionaEntrada() {
		
		return null;
	}

}
