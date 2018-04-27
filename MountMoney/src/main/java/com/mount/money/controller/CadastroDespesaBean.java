package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Despesa;
import com.mount.money.model.MovimentoBanco;
import com.mount.money.repository.Bancos;
import com.mount.money.service.CadastroDespesaService;
import com.mount.money.service.CadastroMovimentoBancoService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroDespesaService
	@Inject
	private CadastroDespesaService cadastroDespesaService;

	@Inject
	private CadastroMovimentoBancoService cadastroMovimentoService;

	// injetando classe bancos
	@Inject
	private Bancos bancos;

	private Despesa despesa;
	private Banco banco;
	private MovimentoBanco movimento;

	// carregar os bancos na tela
	private List<Banco> todosBancos = new ArrayList<>();

	// construtor
	public CadastroDespesaBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		despesa = new Despesa();
		movimento = new MovimentoBanco();
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();
	}

	public void salvar() {

		try {
			this.despesa = cadastroDespesaService.salvar(this.despesa);
			
			movimento.setBanco(despesa.getBanco());
			movimento.setDataMovimento(despesa.getDataDespesa());
			movimento.setHistorico(despesa.getHistorico());
			movimento.setTipoMovimento("S");
			movimento.setValorMovimento(despesa.getValorDespesa());
			
			this.movimento = cadastroMovimentoService.salvar(this.movimento);
			
			limpar();
			FacesUtil.addInfoMessage("Despesa registrada com sucesso!");
			
		} catch (RuntimeException e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao tentar gravar a despesa. "
					+ "Por favor, entre em contato com o Administrador do Sistema!");
		}

		
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