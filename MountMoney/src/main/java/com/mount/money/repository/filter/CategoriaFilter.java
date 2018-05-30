package com.mount.money.repository.filter;

import java.io.Serializable;

public class CategoriaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siglaCategoria;
	private String nomeCategoria;

	public String getSiglaCategoria() {
		return siglaCategoria;
	}

	public void setSiglaCategoria(String siglaCategoria) {
		this.siglaCategoria = siglaCategoria;
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

}
