package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.mount.money.model.Banco;
import com.mount.money.model.Categoria;
import com.mount.money.model.Despesa;
import com.mount.money.repository.Bancos;
import com.mount.money.repository.Categorias;
import com.mount.money.repository.Despesas;
import com.mount.money.repository.filter.DespesaFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaGraficosBarrasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Despesas despesas;
	
	@Inject
	private Categorias categorias;
	
	private BarChartModel barModel;

	private DespesaFilter filtro;
	private List<Despesa> despesasFiltradas;
	private List<Categoria> categoriasFiltradas;
	
	public ConsultaGraficosBarrasBean() {
		limpar();		
	}

	public void limpar() {
		filtro = new DespesaFilter();
		despesasFiltradas = new ArrayList<>();
		categoriasFiltradas = new ArrayList<>();
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		despesasFiltradas = despesas.todasDespesas();
	}

	public List<Despesa> getDespesasFiltradas() {
		return despesasFiltradas;
	}

	public DespesaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DespesaFilter filtro) {
		this.filtro = filtro;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}
	
	@PostConstruct
	private void init() {
		createBarModel();
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		
		categoriasFiltradas = categorias.todosCategorias();
		
		for(Categoria c : categoriasFiltradas){
			ChartSeries a = new ChartSeries();
			a.setLabel(c.getNomeCategoria());
			a.set("2004", 120);
			model.addSeries(a);
		}

		return model;
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Despesas por Categoria");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Categorias");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("R$");
		yAxis.setMin(0);
		yAxis.setMax(1000);
	}
}
