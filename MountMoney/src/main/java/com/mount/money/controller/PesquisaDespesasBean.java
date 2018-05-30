package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Despesa;
import com.mount.money.repository.Bancos;
import com.mount.money.repository.Despesas;
import com.mount.money.repository.filter.DespesaFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDespesasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Despesas despesas;

	@Inject
	private Bancos bancos;

	private DespesaFilter filtro; 
	private List<Despesa> despesasFiltradas; 

	private List<Banco> todosBancos = new ArrayList<>();

	public PesquisaDespesasBean() {
		limpar();
	}

	public void excluir(Despesa despesaSelecionada) {
		
		despesas.remover(despesaSelecionada);
		
		despesasFiltradas.remove(despesaSelecionada);

		FacesUtil.addInfoMessage("Despesa de valor R$ " + despesaSelecionada.getValorDespesa() + " excluída com sucesso.");
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();
	}

	public void limpar() {
		filtro = new DespesaFilter();
		despesasFiltradas = new ArrayList<>();
		
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		despesasFiltradas = despesas.filtrados(filtro);
	}

	public List<Despesa> getDespesasFiltradas() {
		return despesasFiltradas;
	}

	public DespesaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DespesaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Banco> getTodosBancos() {
		return todosBancos;
	}

}
