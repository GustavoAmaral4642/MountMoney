package com.projeto0001.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ocorrencia;
import com.projeto0001.repository.Ocorrencias;
import com.projeto0001.repository.filter.OcorrenciaFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaOcorrenciasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ocorrencias ocorrencias;
	private OcorrenciaFilter filtro; // variavel para usar campos no filtro

	private List<Ocorrencia> ocorrenciasFiltradas; // receber ocorrencias listadas
	
	public PesquisaOcorrenciasBean() {
		limpar();
	}

	// iniciar coleções
	public void inicializar() {
		
	}

	public void limpar() {
		filtro = new OcorrenciaFilter();
		ocorrenciasFiltradas = new ArrayList<>(); // receber ocorrencias listadas		
	}

	// realizar pesquisa da tela.
	public void pesquisar() {		
		ocorrenciasFiltradas = ocorrencias.filtradas(filtro);		
	}

	public Ocorrencias getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Ocorrencias ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public OcorrenciaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(OcorrenciaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Ocorrencia> getOcorrenciasFiltradas() {
		return ocorrenciasFiltradas;
	}

	public void setOcorrenciasFiltradas(List<Ocorrencia> ocorrenciasFiltradas) {
		this.ocorrenciasFiltradas = ocorrenciasFiltradas;
	}
	

}
