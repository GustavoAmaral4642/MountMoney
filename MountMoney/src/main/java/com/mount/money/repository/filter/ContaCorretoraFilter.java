package com.mount.money.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Banco.java
public class ContaCorretoraFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeContaCorretora;
	private String numeroContaCorretora;
	private String tipoContaCorretora;

	public String getNomeContaCorretora() {
		return nomeContaCorretora;
	}

	public void setNomeContaCorretora(String nomeContaCorretora) {
		this.nomeContaCorretora = nomeContaCorretora;
	}

	public String getNumeroContaCorretora() {
		return numeroContaCorretora;
	}

	public void setNumeroContaCorretora(String numeroContaCorretora) {
		this.numeroContaCorretora = numeroContaCorretora;
	}

	public String getTipoContaCorretora() {
		return tipoContaCorretora;
	}

	public void setTipoContaCorretora(String tipoContaCorretora) {
		this.tipoContaCorretora = tipoContaCorretora;
	}
}
