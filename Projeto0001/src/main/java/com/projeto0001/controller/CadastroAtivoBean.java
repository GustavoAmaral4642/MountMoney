package com.projeto0001.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ativo;
import com.projeto0001.service.CadastroAtivoService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAtivoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroAtivoService
	@Inject
	private CadastroAtivoService cadastroAtivoService;

	private Ativo ativo;

	// construtor
	public CadastroAtivoBean() {
		System.out.println("chamou aqui");
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		ativo = new Ativo();		
	}

	// iniciar coleções
	public void inicializar() {
		
	}

	public void salvar() {
		this.ativo = cadastroAtivoService.salvar(this.ativo);
		limpar();
		FacesUtil.addInfoMessage("Ativo gravado com sucesso!");
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	// se o id do ativo não for nulo, está editando
	public boolean isEditando() {
		return this.ativo.getId() != null;
	}
}
