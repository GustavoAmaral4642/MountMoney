package com.projeto0001.repository.filter;

import java.io.Serializable;

import com.projeto0001.model.TipoBanco;

// método usuado para filtro do Banco.java
public class BancoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeBanco;
	private String numeroConta;
	private String tipoBanco;

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoBanco() {
		return tipoBanco;
	}

	public void setTipoBanco(String tipoBanco) {
		this.tipoBanco = tipoBanco;
	}
}
