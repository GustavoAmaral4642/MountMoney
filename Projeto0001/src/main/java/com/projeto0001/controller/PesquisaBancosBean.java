package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Banco;
import com.projeto0001.repository.Bancos;
import com.projeto0001.repository.filter.BancoFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaBancosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//injetando classe bancos
	@Inject
	private Bancos bancos;

	private BancoFilter filtro; // variavel para usar campos no filtro
	private List<Banco> bancosFiltrados; // receber bancos para listagem
	
	public PesquisaBancosBean(){
		limpar();
	}
	
	public void limpar(){
		filtro = new BancoFilter();
		bancosFiltrados = new ArrayList<>();		
	}
	
	//metodo para excluir bancos
	public void excluir(Banco bancoSelecionado){
		//exclui banco do banco de dados
		bancos.remover(bancoSelecionado);
		//exclui bancos da lista
		bancosFiltrados.remove(bancoSelecionado);
		
		FacesUtil.addInfoMessage("Banco " + bancoSelecionado.getNomeBanco() + " excluído com sucesso!");
	}
	
	public void pesquisar(){
		bancosFiltrados = bancos.filtrados(filtro);		
	}

	public List<Banco> getBancosFiltrados() {
		return bancosFiltrados;
	}

	public BancoFilter getFiltro() {
		return filtro;
	}

}

