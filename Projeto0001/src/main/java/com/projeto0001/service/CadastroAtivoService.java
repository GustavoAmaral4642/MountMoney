package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.Ativo;
import com.projeto0001.repository.Ativos;
import com.projeto0001.util.jpa.Transactional;

public class CadastroAtivoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ativos ativos;

	// metodo para chamar o outro para salvar
	@Transactional
	public Ativo salvar(Ativo ativo) {
		
		return ativos.guardar(ativo);
	}
}
