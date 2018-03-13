package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Fornecedor.java
public class FornecedorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String razaoSocial;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}
