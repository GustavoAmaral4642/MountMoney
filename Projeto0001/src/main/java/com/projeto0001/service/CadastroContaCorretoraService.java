package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.ContaCorretora;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.security.Seguranca;
import com.projeto0001.util.jpa.Transactional;

public class CadastroContaCorretoraService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContasCorretoras contas;

	// metodo para chamar o outro para salvar
	@Transactional
	public ContaCorretora salvar(ContaCorretora contaCorretora) {

		return contas.guardar(contaCorretora);
	}
}
