package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Moeda.java
public class AtivoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siglaAtivo;
	private String nomeAtivo;

	public String getSiglaAtivo() {
		return siglaAtivo;
	}

	public void setSiglaAtivo(String siglaAtivo) {
		this.siglaAtivo = siglaAtivo;
	}
	
	public String getNomeAtivo() {
		return nomeAtivo;
	}

	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}

}
