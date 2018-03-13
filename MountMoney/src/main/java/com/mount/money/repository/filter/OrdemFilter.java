package com.mount.money.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.mount.money.model.ContaCorretora;
import com.mount.money.model.TipoOrdem;

// método usado para filtro do Ordem.java
public class OrdemFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dtMovimentoDe;
	private Date dtMovimentoAte;
	private TipoOrdem[] tpOrdem;
	private String siglaAtivo;
	private String nomeContaCorretora;

	public Date getDtMovimentoDe() {
		return dtMovimentoDe;
	}

	public void setDtMovimentoDe(Date dtMovimentoDe) {
		this.dtMovimentoDe = dtMovimentoDe;
	}

	public Date getDtMovimentoAte() {
		return dtMovimentoAte;
	}

	public void setDtMovimentoAte(Date dtMovimentoAte) {
		this.dtMovimentoAte = dtMovimentoAte;
	}

	public TipoOrdem[] getTpOrdem() {
		return tpOrdem;
	}

	public void setTpOrdem(TipoOrdem[] tpOrdem) {
		this.tpOrdem = tpOrdem;
	}

	public String getSiglaAtivo() {
		return siglaAtivo;
	}

	public void setSiglaAtivo(String siglaAtivo) {
		this.siglaAtivo = siglaAtivo;
	}

	public String getNomeContaCorretora() {
		return nomeContaCorretora;
	}

	public void setNomeContaCorretora(String nomeContaCorretora) {
		this.nomeContaCorretora = nomeContaCorretora;
	}

}
