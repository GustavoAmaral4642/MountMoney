package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Titulo;
import com.mount.money.repository.Titulos;
import com.mount.money.repository.filter.TituloFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaTitulosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Titulos titulos;

	private TituloFilter filtro; 
	private List<Titulo> titulosFiltrados; 

	public PesquisaTitulosBean() {
		limpar();
	}

	public void excluir(Titulo tituloSelecionado) {
		
		titulos.remover(tituloSelecionado);
		
		titulosFiltrados.remove(tituloSelecionado);

		FacesUtil.addInfoMessage("Título "+ tituloSelecionado.getHistorico() +" excluído com sucesso.");
	}

	// iniciar coleções
	public void inicializar() {
		
	}

	public void limpar() {
		filtro = new TituloFilter();
		titulosFiltrados = new ArrayList<>();
		
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		titulosFiltrados = titulos.filtrados(filtro);
	}

	public List<Titulo> getTitulosFiltrados() {
		return titulosFiltrados;
	}

	public TituloFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TituloFilter filtro) {
		this.filtro = filtro;
	}

}
