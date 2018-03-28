package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import com.mount.money.model.Ocorrencia;
import com.mount.money.model.Pessoa;
import com.mount.money.repository.Pessoas;
import com.mount.money.repository.filter.PessoaFilter;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;
	/*
	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;

	private Ocorrencia ocorrencia;
	*/
	private PessoaFilter filtro; // variavel para usar campos no filtro
	private List<Pessoa> pessoasFiltradas; // receber ativos para listagem

	public PesquisaPessoasBean() {
		limpar();
	}

	// metodo para excluir pessoas
	public void excluir(Pessoa pessoaSelecionada) {
		// exclui pessoa do banco de dados
		pessoas.remover(pessoaSelecionada);
		// exclui pessoas da lista
		pessoasFiltradas.remove(pessoaSelecionada);
/*
		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logAtivoD(ativoSelecionado);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);
*/
		FacesUtil.addInfoMessage("Pessoa " + pessoaSelecionada.getNome() + " excluída com sucesso!");
	}

	public void limpar() {
		filtro = new PessoaFilter();
		pessoasFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		pessoasFiltradas = pessoas.filtradas(filtro);
	}

	public List<Pessoa> getPessoasFiltradas() {
		return pessoasFiltradas;
	}

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PessoaFilter filtro) {
		this.filtro = filtro;
	}

}
