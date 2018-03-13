package com.projeto0001.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.projeto0001.model.Banco;

// método usado para filtro do MovimentoBancario.java
public class MovimentoBancoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataMovimentoDe;
	private Date dataMovimentoAte;
	private String tipoMovimento;
	private Banco banco;

	public Date getDataMovimentoDe() {
		return dataMovimentoDe;
	}

	public void setDataMovimentoDe(Date dataMovimentoDe) {
		this.dataMovimentoDe = dataMovimentoDe;
	}

	public Date getDataMovimentoAte() {
		return dataMovimentoAte;
	}

	public void setDataMovimentoAte(Date dataMovimentoAte) {
		this.dataMovimentoAte = dataMovimentoAte;
	}
	
	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Banco getBanco() {		
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco= banco;
	}
}
