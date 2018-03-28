package com.mount.money.repository.filter;

import java.io.Serializable;

// método usado para filtro do ListasAmigos.java
public class ListaAmigoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
