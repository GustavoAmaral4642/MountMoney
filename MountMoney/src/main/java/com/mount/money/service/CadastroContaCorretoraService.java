package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.ContaCorretora;
import com.mount.money.repository.ContasCorretoras;
import com.mount.money.util.jpa.Transactional;

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
