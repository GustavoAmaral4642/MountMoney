package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Banco;
import com.projeto0001.model.Trader;
import com.projeto0001.repository.Bancos;
import com.projeto0001.repository.Traders;
import com.projeto0001.repository.filter.BancoFilter;
import com.projeto0001.repository.filter.TraderFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaTradersBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//injetando classe bancos
	@Inject
	private Traders traders;
	
	private TraderFilter filtro; // variavel para usar campos no filtro
	private List<Trader> tradersFiltrados; // receber traders para listagem
	
	private Trader traderSelecionado; // recebe trader selecionado
	
	public PesquisaTradersBean(){
		filtro = new TraderFilter();
		tradersFiltrados = new ArrayList<>();
	}
	
	//metodo para excluir traders
	public void excluir(){
		//exclui trader do banco de dados
		traders.remover(traderSelecionado);
		//exclui traders da lista
		tradersFiltrados.remove(traderSelecionado);
		
		FacesUtil.addInfoMessage("Trader " + traderSelecionado.getNomeTrader() + " excluído com sucesso!");
	}
	
	public void pesquisar(){
		tradersFiltrados = traders.filtrados(filtro);		
	}

	public List<Trader> getTradersFiltrados() {
		return tradersFiltrados;
	}

	public TraderFilter getFiltro() {
		return filtro;
	}

	public Trader getTraderSelecionado() {
		return traderSelecionado;
	}

	public void setTraderSelecionado(Trader traderSelecionado) {
		this.traderSelecionado = traderSelecionado;
	}
}

