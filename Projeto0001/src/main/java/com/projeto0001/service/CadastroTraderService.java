package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.Trader;
import com.projeto0001.repository.Traders;
import com.projeto0001.util.jpa.Transactional;

public class CadastroTraderService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Traders traders;

	// metodo para chamar o outro para salvar
	@Transactional
	public Trader salvar(Trader trader) {

		return traders.guardar(trader);
	}
}
