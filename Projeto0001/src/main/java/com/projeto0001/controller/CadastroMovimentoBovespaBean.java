package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.MovimentoBovespa;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.service.CadastroMovimentoBovespaService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMovimentoBovespaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroMovimentoBovespaService
	@Inject
	private CadastroMovimentoBovespaService cadastroMovimentoBovespaService;

	// injetando classe ContasCorretoras
	@Inject
	private ContasCorretoras contasCorretoras;

	private MovimentoBovespa movimentoBovespa;

	// carregar os bancos na tela
	private List<ContaCorretora> todasContas = new ArrayList<>();

	// construtor
	public CadastroMovimentoBovespaBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		movimentoBovespa = new MovimentoBovespa();
	}

	// iniciar coleções
	public void inicializar() {
		todasContas = contasCorretoras.todasContas();		
	}

	public void salvar() {
		this.movimentoBovespa = cadastroMovimentoBovespaService.salvar(this.movimentoBovespa);
		limpar();
		FacesUtil.addInfoMessage("Movimento registrado com sucesso!");
	}

	public List<ContaCorretora> getTodasContas() {
		return todasContas;
	}

	public MovimentoBovespa getMovimentoBovespa() {
		return movimentoBovespa;
	}

	public void setMovimentoBovespa(MovimentoBovespa movimentoBovespa) {
		this.movimentoBovespa = movimentoBovespa;
	}

	// se o id do movimento não for nulo, está editando
	public boolean isEditando() {
		return this.movimentoBovespa.getId() != null;
	}
}