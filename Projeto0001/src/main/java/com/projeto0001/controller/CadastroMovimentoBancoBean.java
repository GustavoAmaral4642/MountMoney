package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Banco;
import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.MovimentoBanco;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Bancos;
import com.projeto0001.service.CadastroMovimentoBancoService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMovimentoBancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroMovimentoBancarioService
	@Inject
	private CadastroMovimentoBancoService cadastroMovimentoBancoService;

	// injetando classe bancos
	@Inject
	private Bancos bancos;

	private MovimentoBanco movimentoBanco;

	private Banco banco;
	
	// carregar os bancos na tela
	private List<Banco> todosBancos = new ArrayList<>();

	// construtor
	public CadastroMovimentoBancoBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		movimentoBanco = new MovimentoBanco();
		movimentoBanco.setTipoMovimento("E");
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();		
	}

	public void salvar() {
		this.movimentoBanco = cadastroMovimentoBancoService.salvar(this.movimentoBanco);
		limpar();
		FacesUtil.addInfoMessage("Movimento registrado com sucesso!");
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


	public MovimentoBanco getMovimentoBanco() {
		return movimentoBanco;
	}

	public void setMovimentoBanco(MovimentoBanco movimentoBanco) {
		this.movimentoBanco = movimentoBanco;
	}

	// se o id do movimento não for nulo, está editando
	public boolean isEditando() {
		return this.movimentoBanco.getId() != null;
	}
}