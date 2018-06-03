package com.mount.money.controller;

import java.io.IOException;	 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ContaCorretora;
import com.mount.money.model.OrdemConsulta;
import com.mount.money.repository.Carteiras;
import com.mount.money.repository.ContasCorretoras;
import com.mount.money.repository.filter.CarteiraFilter;
import com.mount.money.service.CadastroCarteiraService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarteirasBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Carteiras carteiras;
	
	@Inject
	private ContasCorretoras contasCorretoras;

	@Inject
	private CadastroCarteiraService cadastroCarteiraService;
	
	private CarteiraFilter filtro; // variavel para usar campos no filtro
	
	private List<String> ativosFiltrados; 
	private List<ContaCorretora> todasContasCorretoras; 
	private List<OrdemConsulta> odsConsultas;
	
	public PesquisaCarteirasBean() {
		limpar();
	}

	// iniciar coleções
	public void inicializar() {
		todasContasCorretoras = contasCorretoras.todasContas();
	}

	public void limpar() {
		filtro = new CarteiraFilter();
		ativosFiltrados = new ArrayList<>(); // receber ordens listados
		todasContasCorretoras = new ArrayList<>(); // receber contas
		odsConsultas = new ArrayList<>();
	}

	// realizar consulta detalhada .
	public void consultaCarteira() throws IOException {
		
		try{
		ativosFiltrados = carteiras.somenteAtivos(filtro);
		} catch(RuntimeException e){
			FacesUtil.addErrorMessage("Problemas ao consultar! \n "
					+ "Erro: " + e.getMessage());
		}
		
		try{
		odsConsultas = cadastroCarteiraService.consultaPrecoMedio(ativosFiltrados, filtro);
		} catch(RuntimeException e){
			FacesUtil.addErrorMessage("Problemas ao consultar! \n "
					+ "Erro: " + e.getMessage());
		}
	}
	
	public List<ContaCorretora> getTodasContasCorretoras() {
		return todasContasCorretoras;
	}

	public CarteiraFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CarteiraFilter filtro) {
		this.filtro = filtro;
	}

	public List<OrdemConsulta> getOdsConsultas() {
		return odsConsultas;
	}
	
	
}
