package com.mount.money.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Moeda.java
public class PessoaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String apelido;
	private String nome;

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
