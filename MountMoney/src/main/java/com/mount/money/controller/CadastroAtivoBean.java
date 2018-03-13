package com.mount.money.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Ativo;
import com.mount.money.model.Ocorrencia;
import com.mount.money.service.CadastroAtivoService;
import com.mount.money.util.jsf.FacesUtil;
import com.mount.money.service.CadastroOcorrenciaService;

@Named
@ViewScoped
public class CadastroAtivoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroAtivoService
	@Inject
	private CadastroAtivoService cadastroAtivoService;

	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;
	
	private Ocorrencia ocorrencia;

	private Ativo ativo;

	// construtor
	public CadastroAtivoBean() {
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

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logAtivoI(ativo);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

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
