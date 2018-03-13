package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.GrupoInvestidor;
import com.projeto0001.repository.GruposInvestidores;
import com.projeto0001.repository.filter.GrupoInvestidorFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaGruposInvestidoresBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GruposInvestidores gruposInvestidores;
		
	private GrupoInvestidorFilter filtro;
	private List<GrupoInvestidor> gruposInvestidoresFiltrados;

	public PesquisaGruposInvestidoresBean(){
		filtro = new GrupoInvestidorFilter();
	}
	
	//método para excluir grupos de investimento
	public void excluir(GrupoInvestidor grupoInvestidorSelecionado){
		//removendo do banco de dados
		gruposInvestidores.remover(grupoInvestidorSelecionado);
		
		//removendo do list
		gruposInvestidoresFiltrados.remove(grupoInvestidorSelecionado);
		
		FacesUtil.addInfoMessage("Grupo de investimento " + grupoInvestidorSelecionado.getNomeGrupo() + " excluído com sucesso!");
	}
	
	public void pesquisar(){
		gruposInvestidoresFiltrados = gruposInvestidores.filtrados(filtro);
	
	}
	
	public void limpar(){
		filtro = new GrupoInvestidorFilter();
		gruposInvestidoresFiltrados = new ArrayList<>();
	}
	
	public List<GrupoInvestidor> getGruposInvestidoresFiltrados() {
		return gruposInvestidoresFiltrados;
	}

	public GrupoInvestidorFilter getFiltro() {
		return filtro;
	}

	public void setGruposInvestidoresFiltrados(List<GrupoInvestidor> gruposInvestidoresFiltrados) {
		this.gruposInvestidoresFiltrados = gruposInvestidoresFiltrados;
	}
	
}
