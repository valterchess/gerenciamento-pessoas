package br.com.nesher.gerenciamentopessoas.domain.vo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManagerFilterVO {
	
	@NotNull
	private Integer id;

}
