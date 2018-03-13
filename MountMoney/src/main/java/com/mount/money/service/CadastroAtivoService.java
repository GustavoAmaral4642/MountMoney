package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Ativo;
import com.mount.money.repository.Ativos;
import com.mount.money.util.jpa.Transactional;

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
