package com.mount.money.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Categoria;
import com.mount.money.model.Ocorrencia;
import com.mount.money.service.CadastroCategoriaService;
import com.mount.money.util.jsf.FacesUtil;
import com.mount.money.service.CadastroOcorrenciaService;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCategoriaService cadastroCategoriaService;

	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;
	
	private Ocorrencia ocorrencia;

	private Categoria categoria;

	// construtor
	public CadastroCategoriaBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		categoria = new Categoria();
	}

	// iniciar coleções
	public void inicializar() {

	}

	public void salvar() {

		this.categoria = cadastroCategoriaService.salvar(this.categoria);

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logCategoriaI(categoria);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

		limpar();
		FacesUtil.addInfoMessage("Categoria gravada com sucesso!");
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	// se o id do categoria não for nulo, está editando
	public boolean isEditando() {
		return this.categoria.getId() != null;
	}

}
