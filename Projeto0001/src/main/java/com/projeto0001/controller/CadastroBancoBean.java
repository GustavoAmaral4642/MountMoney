package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Banco;
import com.projeto0001.model.TipoBanco;
import com.projeto0001.repository.Bancos;
import com.projeto0001.service.CadastroBancoService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroBancoService
	@Inject
	private CadastroBancoService cadastroBancoService;
	
	private Banco banco;

	// construtor
	public CadastroBancoBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		banco = new Banco();
	}

	// iniciar coleções
	public void inicializar() {
		banco.setTipoBanco(TipoBanco.FINANCEIRO);
	}

	public void salvar() {
		
		this.banco = cadastroBancoService.salvar(this.banco);
		limpar();
		FacesUtil.addInfoMessage("Banco gravado com sucesso!");
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	// se o id do banco não for nulo, está editando
	public boolean isEditando() {
		return this.banco.getId() != null;
	}

}
