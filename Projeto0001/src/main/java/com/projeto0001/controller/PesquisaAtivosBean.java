package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ativo;
import com.projeto0001.repository.Ativos;
import com.projeto0001.repository.filter.AtivoFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAtivosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//injetando classe ativos
	@Inject
	private Ativos ativos;

	private AtivoFilter filtro; // variavel para usar campos no filtro
	private List<Ativo> ativosFiltrados; // receber ativos para listagem
	
	public PesquisaAtivosBean(){
		filtro = new AtivoFilter();
		ativosFiltrados = new ArrayList<>();
	}
	
	//metodo para excluir ativos
	public void excluir(Ativo ativoSelecionado){
		//exclui ativo do banco de dados
		ativos.remover(ativoSelecionado);
		//exclui ativos da lista
		ativosFiltrados.remove(ativoSelecionado);
		
		FacesUtil.addInfoMessage("Ativo " + ativoSelecionado.getSiglaAtivo() + " excluído com sucesso!");
	}
	
	public void limpar(){
		filtro = new AtivoFilter();
		ativosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		ativosFiltrados = ativos.filtrados(filtro);		
	}

	public List<Ativo> getAtivosFiltrados() {
		return ativosFiltrados;
	}

	public AtivoFilter getFiltro() {
		return filtro;
	}

}

