package com.mount.money.repository.filter;

import java.io.Serializable;

// método usado para filtro do Ordem.java
public class CarteiraFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siglaAtivo;
	private String nomeContaCorretora;

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
