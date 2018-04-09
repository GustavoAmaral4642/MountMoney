package com.mount.money.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.mount.money.model.Banco;

// método usado para filtro do MovimentoBancario.java
public class DespesaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataDespesaDe;
	private Date dataDespesaAte;
	private Date dataPagamentoDe;
	private Date dataPagamentoAte;
	private Banco banco;
	private String historico;

	public Date getDataDespesaDe() {
		return dataDespesaDe;
	}

	public void setDataDespesaDe(Date dataDespesaDe) {
		this.dataDespesaDe = dataDespesaDe;
	}

	public Date getDataDespesaAte() {
		return dataDespesaAte;
	}

	public void setDataDespesaAte(Date dataDespesaAte) {
		this.dataDespesaAte = dataDespesaAte;
	}

	public Date getDataPagamentoDe() {
		return dataPagamentoDe;
	}

	public void setDataPagamentoDe(Date dataPagamentoDe) {
		this.dataPagamentoDe = dataPagamentoDe;
	}

	public Date getDataPagamentoAte() {
		return dataPagamentoAte;
	}

	public void setDataPagamentoAte(Date dataPagamentoAte) {
		this.dataPagamentoAte = dataPagamentoAte;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}
}
