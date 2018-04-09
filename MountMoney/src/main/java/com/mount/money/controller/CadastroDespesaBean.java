package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Despesa;
import com.mount.money.repository.Bancos;
import com.mount.money.service.CadastroDespesaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroDespesaService
	@Inject
	private CadastroDespesaService cadastroDespesaService;

	// injetando classe bancos
	@Inject
	private Bancos bancos;
	
	private Despesa despesa;

	private Banco banco;
	
	// carregar os bancos na tela
	private List<Banco> todosBancos = new ArrayList<>();

	// construtor
	public CadastroDespesaBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		despesa = new Despesa();		
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();		
	}

	public void salvar() {
		this.despesa = cadastroDespesaService.salvar(this.despesa);
		limpar();
		FacesUtil.addInfoMessage("Despesa registrada com sucesso!");
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<Banco> getTodosBancos() {
		return todosBancos;
	}


	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	// se o id do despesa não for nulo, está editando
	public boolean isEditando() {
		return this.despesa.getId() != null;
	}
}