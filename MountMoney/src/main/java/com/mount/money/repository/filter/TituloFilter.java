package com.mount.money.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.mount.money.model.Banco;

// método usado para filtro do MovimentoBancario.java
public class TituloFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataEmissaoDe;
	private Date dataEmissaoAte;
	private Date dataPagamentoDe;
	private Date dataPagamentoAte;
	private Banco banco;
	private String historico;

	public Date getDataEmissaoDe() {
		return dataEmissaoDe;
	}

	public void setDataEmissaoDe(Date dataEmissaoDe) {
		this.dataEmissaoDe = dataEmissaoDe;
	}

	public Date getDataEmissaoAte() {
		return dataEmissaoAte;
	}

	public void setDataEmissaoAte(Date dataEmissaoAte) {
		this.dataEmissaoAte = dataEmissaoAte;
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
