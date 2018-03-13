package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.GrupoInvestidor;
import com.projeto0001.repository.GruposInvestidores;
import com.projeto0001.util.jpa.Transactional;

public class CadastroGrupoInvestidorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GruposInvestidores grupos;

	// metodo para chamar o outro para salvar
	@Transactional
	public GrupoInvestidor salvar(GrupoInvestidor grupo) {
		
		return grupos.guardar(grupo);
	}
}
