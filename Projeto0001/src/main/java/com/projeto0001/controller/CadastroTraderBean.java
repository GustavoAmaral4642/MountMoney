package com.projeto0001.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Trader;
import com.projeto0001.service.CadastroTraderService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTraderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroTraderService
	@Inject
	private CadastroTraderService cadastroTraderService;

	private Trader trader;

	// construtor
	public CadastroTraderBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		trader = new Trader();		
	}
	
	// iniciar coleções
	public void inicializar() {

	}

	public void salvar() {
		this.trader = cadastroTraderService.salvar(this.trader);
		limpar();
		FacesUtil.addInfoMessage("Trader gravado com sucesso!");
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
	// se o id do trader não for nulo, está editando
	public boolean isEditando() {
		return this.trader.getId() != null;
	}

}
