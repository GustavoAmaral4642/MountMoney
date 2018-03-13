package com.mount.money.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Ocorrencia;
import com.mount.money.model.TipoBanco;
import com.mount.money.service.CadastroBancoService;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroBancoService
	@Inject
	private CadastroBancoService cadastroBancoService;

	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;

	private Ocorrencia ocorrencia;
	private Banco banco;

	// construtor
	public CadastroBancoBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		banco = new Banco();
		banco.setTipoBanco(TipoBanco.FINANCEIRO);
	}

	// iniciar coleções
	public void inicializar() {
		
	}

	public void salvar() {

		this.banco = cadastroBancoService.salvar(this.banco);

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logBancoI(banco);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

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
