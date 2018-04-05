package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Pessoa;
import com.mount.money.repository.Pessoas;
import com.mount.money.util.jpa.Transactional;

public class CadastroPessoaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;

	// metodo para chamar o outro para salvar
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {

		return pessoas.guardar(pessoa);
	}

}
