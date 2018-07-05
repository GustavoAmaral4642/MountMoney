package com.mount.money.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.util.DespesaPorCategoria;
import com.mount.money.repository.Despesas;
import com.mount.money.repository.filter.DespesaFilter;

@Named
@ViewScoped
public class ConsultaDespesaPorCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Despesas despesas;

	private DespesaFilter filtro;

	private List<DespesaPorCategoria> despesasPorCategorias;

	public ConsultaDespesaPorCategoriaBean() {
		limpar();
	}

	public void limpar() {
		despesasPorCategorias = new ArrayList<>();
		
		filtro = new DespesaFilter();
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		filtro.setDataDespesaDe(c.getTime());
		
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		filtro.setDataDespesaAte(c.getTime());
	}

	@PostConstruct
	public void init() {
		pesquisar();
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		despesasPorCategorias = despesas.despesasPorCategoria(filtro);
	}

	public List<DespesaPorCategoria> getDespesasPorCategorias() {
		return despesasPorCategorias;
	}

	public DespesaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DespesaFilter filtro) {
		this.filtro = filtro;
	}

	
	
}
