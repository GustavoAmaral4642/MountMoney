package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ListaAmigo;
import com.mount.money.model.Pessoa;
import com.mount.money.repository.Pessoas;
import com.mount.money.repository.filter.PessoaFilter;
import com.mount.money.service.CadastroListaAmigoService;

@Named
@ViewScoped
public class ListaAmigosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroListaAmigoService cadastroListaAmigoService;

	@Inject
	private Pessoas pessoas;
	/*
	 * @Inject private CadastroOcorrenciaService cadastroOcorrenciaService;
	 * 
	 * private Ocorrencia ocorrencia;
	 */

	private List<Pessoa> listasFiltrados;
	
	private ListaAmigo lista;
	
	private PessoaFilter filtro;
	
	// construtor
	public ListaAmigosBean() {
		limpar();

	}

	// metodo para limpar os dados na tela
	public void limpar() {
		lista = new ListaAmigo();
		filtro = new PessoaFilter();
		listasFiltrados = new ArrayList<>();	
	}

	// iniciar coleções
	public void inicializar() {
	
	}

	public void enviarConvite() {

	}

	public void pesquisar(){
		listasFiltrados = pessoas.filtradas(filtro);
	}
	
	public ListaAmigo getListaAmigo() {
		return lista;
	}

	public void setListaAmigo(ListaAmigo lista) {
		this.lista = lista;
	}

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PessoaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getListasFiltrados() {
		return listasFiltrados;
	}

	public void setListasFiltrados(List<Pessoa> listasFiltrados) {
		this.listasFiltrados = listasFiltrados;
	}

	// se o id da lista de amigo não for nulo, está editando
	public boolean isEditando() {
		return this.lista.getId() != null;
	}

}
