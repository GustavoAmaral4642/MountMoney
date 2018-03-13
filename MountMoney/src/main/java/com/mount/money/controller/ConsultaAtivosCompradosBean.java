package com.mount.money.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.OrdemTotal;
import com.mount.money.repository.Carteiras;
import com.mount.money.repository.filter.CarteiraFilter;
import com.mount.money.service.CadastroCarteiraService;

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
