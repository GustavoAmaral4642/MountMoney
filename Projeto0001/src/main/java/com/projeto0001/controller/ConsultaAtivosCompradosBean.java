package com.projeto0001.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ordem;
import com.projeto0001.model.OrdemConsulta;
import com.projeto0001.model.OrdemTotal;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Carteiras;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.repository.Ordens;
import com.projeto0001.repository.filter.CarteiraFilter;
import com.projeto0001.repository.filter.OrdemFilter;
import com.projeto0001.service.CadastroCarteiraService;

@Named
@ViewScoped
public class ConsultaAtivosCompradosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Carteiras carteiras;

	@Inject
	private CadastroCarteiraService cadastroCarteiraService;

	private CarteiraFilter filtro; // variavel para usar campos no filtro

	private List<String> ativosFiltrados;
	private List<OrdemTotal> odsTotais;

	public ConsultaAtivosCompradosBean() {
		limpar();
	}

	// iniciar coleções
	public void inicializar() {

	}

	public void limpar() {
		filtro = new CarteiraFilter();
		ativosFiltrados = new ArrayList<>(); // receber ordens listados
		odsTotais = new ArrayList<>();
	}

	// realizar consulta detalhada .
	public void consultaCarteira() throws IOException {

		ativosFiltrados = carteiras.somenteAtivos(filtro);
		odsTotais = cadastroCarteiraService.consultaPrecoLucro(ativosFiltrados, filtro);
	}

	public List<OrdemTotal> getOdsTotais() {
		return odsTotais;
	}

	public CarteiraFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CarteiraFilter filtro) {
		this.filtro = filtro;
	}

}
