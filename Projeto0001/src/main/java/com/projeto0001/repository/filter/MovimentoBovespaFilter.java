package com.projeto0001.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.projeto0001.model.ContaCorretora;

// método usado para filtro do MovimentoBancario.java
public class MovimentoBovespaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataMovimentoDe;
	private Date dataMovimentoAte;
	private String tipoMovimento;
	private ContaCorretora contaCorretora;

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

	public ContaCorretora getContaCorretora() {
		return contaCorretora;
	}

	public void setContaCorretora(ContaCorretora contaCorretora) {
		this.contaCorretora = contaCorretora;
	}

}
