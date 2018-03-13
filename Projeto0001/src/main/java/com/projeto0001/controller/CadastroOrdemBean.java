package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ativo;
import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.MovimentoBovespa;
import com.projeto0001.model.Ocorrencia;
import com.projeto0001.model.Ordem;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Ativos;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.service.CadastroOcorrenciaService;
import com.projeto0001.service.CadastroOrdemService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroOrdemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroOrdemService
	@Inject
	private CadastroOrdemService cadastroOrdemService;

	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;
	
	// injetando classe ativos
	@Inject
	private Ativos ativos;
	
	@Inject
	private ContasCorretoras contasCorretoras;
	
	private Ordem ordem;
	
	private Ocorrencia ocorrencia;
		
	// carregar os ativos e contas de corretoras na tela
	private List<Ativo> todosAtivos = new ArrayList<>();
	private List<ContaCorretora> todasContasCorretoras = new ArrayList<>();
	
	// construtor
	public CadastroOrdemBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		ordem = new Ordem();
		ordem.setTpOrdem(TipoOrdem.COMPRA);
		ordem.setMovimento(new MovimentoBovespa());			
		ocorrencia = new Ocorrencia();
	}

	// iniciar coleções
	public void inicializar() {
		todosAtivos = ativos.todosAtivos();
		todasContasCorretoras = contasCorretoras.todasContas();
	}

	public void salvar() {
		
		// alimenta Ordem
		this.ordem = cadastroOrdemService.calcularValorTotal(ordem);
		this.ordem = cadastroOrdemService.ordemMovimento(ordem);
		this.ordem = cadastroOrdemService.calcularImpostos(ordem);
		this.ordem = cadastroOrdemService.salvar(this.ordem);
		
		//tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logOrdem(ordem);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);
		
		limpar();
		FacesUtil.addInfoMessage("Ordem registrada com sucesso!");
	}
	
	public Ordem getOrdem() {
		return ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public List<Ativo> getTodosAtivos() {
		return todosAtivos;
	}

	public List<ContaCorretora> getTodasContasCorretoras() {
		return todasContasCorretoras;
	}
	
	// se o id da ordem não for nulo, está editando
	public boolean isEditando() {
		return this.ordem.getId() != null;
	}
}