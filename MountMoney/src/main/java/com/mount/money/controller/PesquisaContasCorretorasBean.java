package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ContaCorretora;
import com.mount.money.repository.ContasCorretoras;
import com.mount.money.repository.filter.ContaCorretoraFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaContasCorretorasBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//injetando classe contasCorretoras
	@Inject
	private ContasCorretoras contas;

	private ContaCorretoraFilter filtro; // variavel para usar campos no filtro
	private List<ContaCorretora> contasFiltradas; // receber contas para listagem
	
	public PesquisaContasCorretorasBean(){
		limpar();
	}
	
	public void limpar(){
		filtro = new ContaCorretoraFilter();
		contasFiltradas = new ArrayList<>();
	}
	
	//metodo para excluir contas
	public void excluir(ContaCorretora contaSelecionada){
		//exclui contaCorretora do banco de dados
		contas.remover(contaSelecionada);
		//exclui contas de corretoras da lista
		contasFiltradas.remove(contaSelecionada);
		
		FacesUtil.addInfoMessage("Conta da corretora " + contaSelecionada.getNomeContaCorretora() + " excluída com sucesso!");
	}
	
	public void pesquisar(){
		contasFiltradas = contas.filtrados(filtro);		
	}

	public ContasCorretoras getContas() {
		return contas;
	}

	public void setContas(ContasCorretoras contas) {
		this.contas = contas;
	}

	public ContaCorretoraFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ContaCorretoraFilter filtro) {
		this.filtro = filtro;
	}

	public List<ContaCorretora> getContasFiltradas() {
		return contasFiltradas;
	}

	public void setContasFiltradas(List<ContaCorretora> contasFiltradas) {
		this.contasFiltradas = contasFiltradas;
	}

}

