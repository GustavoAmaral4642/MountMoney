package com.projeto0001.repository.filter;

import java.io.Serializable;
import java.util.Date;

// método usuado para filtro do Banco.java
public class NotaCorretagemFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroNota;
	private String nomeContaCorretora;
	private Date dtMovimentoDe;
	private Date dtMovimentoAte;

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getNomeContaCorretora() {
		return nomeContaCorretora;
	}

	public void setNomeContaCorretora(String nomeContaCorretora) {
		this.nomeContaCorretora = nomeContaCorretora;
	}

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

}
