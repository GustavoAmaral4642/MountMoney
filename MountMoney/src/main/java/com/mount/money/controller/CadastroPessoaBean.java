package com.mount.money.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Genero;
import com.mount.money.model.Ocorrencia;
import com.mount.money.model.Pessoa;
import com.mount.money.util.jsf.FacesUtil;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.service.CadastroPessoaService;

@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPessoaService cadastroPessoaService;

	/*@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;
	
	private Ocorrencia ocorrencia;
*/
	private Pessoa pessoa;

	// construtor
	public CadastroPessoaBean() {
		limpar();

	}

	// metodo para limpar os dados na tela
	public void limpar() {
		pessoa = new Pessoa();
		pessoa.setSexo(Genero.MASCULINO);
	}

	// iniciar coleções
	public void inicializar() {

	}

	public void salvar() {

		this.pessoa = cadastroPessoaService.salvar(this.pessoa);

		// tratar e salvar ocorrencia
		//this.ocorrencia = cadastroOcorrenciaService.logAtivoI(ativo);
		//this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

		limpar();
		FacesUtil.addInfoMessage("Pessoa gravada com sucesso!");
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	// se o id do pessoa não for nulo, está editando
	public boolean isEditando() {
		return this.pessoa.getId() != null;
	}

}
