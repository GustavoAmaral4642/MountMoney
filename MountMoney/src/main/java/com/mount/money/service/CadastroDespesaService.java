package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Despesa;
import com.mount.money.repository.Despesas;
import com.mount.money.util.jpa.Transactional;

public class CadastroDespesaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Despesas despesas;

	// metodo para chamar o outro para salvar
	@Transactional
	public Despesa salvar(Despesa despesa) {
		
		//grava a despesa
		return despesas.guardar(despesa);
	}
	
}