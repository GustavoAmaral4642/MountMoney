package com.mount.money.repository.filter;

import java.io.Serializable;

import com.mount.money.model.TipoBanco;

// método usuado para filtro do Banco.java
public class BancoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeBanco;
	private String numeroConta;
	private TipoBanco[] tipoBanco;

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

	public void setTipoBanco(TipoBanco[] tipoBanco) {
		this.tipoBanco = tipoBanco;
	}

	public TipoBanco[] getTipoBanco() {
		return tipoBanco;
	}
}
