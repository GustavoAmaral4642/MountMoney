package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Cliente.java
public class EnderecoClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logradouro;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}
