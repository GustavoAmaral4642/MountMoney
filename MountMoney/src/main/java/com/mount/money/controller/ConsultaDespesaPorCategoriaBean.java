package com.mount.money.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private BigDecimal vlrTotal;

	private List<DespesaPorCategoria> despesasPorCategorias;

	public ConsultaDespesaPorCategoriaBean() {
		limpar();
	}

	public void limpar() {
		despesasPorCategorias = new ArrayList<>();
		filtro = new DespesaFilter();
		vlrTotal = BigDecimal.ZERO;
	}

	@PostConstruct
	public void init() {
		Calendar c = Calendar.getInstance();
		Date d = new Date();
		d = c.getTime();

		c.set(2018, 6, 1, 0, 0);
		// seta primeiro dia do mês
		d = c.getTime();
		filtro.setDataDespesaDe(d);

		// seta ultimo dia do mês
		c.set(2018, 6, 31, 0, 0);
		d = c.getTime();
		filtro.setDataDespesaAte(d);

		pesquisar();
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		despesasPorCategorias = despesas.despesasPorCategoria(filtro);

		for(DespesaPorCategoria dsp : despesasPorCategorias){
			vlrTotal = vlrTotal.add(dsp.getValorDespesa());
		}
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

	public BigDecimal getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

}
