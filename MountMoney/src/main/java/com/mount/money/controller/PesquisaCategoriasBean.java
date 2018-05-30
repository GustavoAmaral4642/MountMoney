package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Categoria;
import com.mount.money.model.Ocorrencia;
import com.mount.money.repository.Categorias;
import com.mount.money.repository.filter.CategoriaFilter;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;

	private Ocorrencia ocorrencia;
	
	private CategoriaFilter filtro;
	private List<Categoria> categoriasFiltradas; 

	public PesquisaCategoriasBean() {
		limpar();
	}

	// metodo para excluir categorias
	public void excluir(Categoria categoriaSelecionada) {
		
		categorias.remover(categoriaSelecionada);
		categoriasFiltradas.remove(categoriaSelecionada);

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logCategoriaD(categoriaSelecionada);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

		FacesUtil.addInfoMessage("Categoria " + categoriaSelecionada.getNomeCategoria() + " excluída com sucesso!");
	}

	public void limpar() {
		filtro = new CategoriaFilter();
		categoriasFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		categoriasFiltradas = categorias.filtrados(filtro);
	}

	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CategoriaFilter filtro) {
		this.filtro = filtro;
	}

}
