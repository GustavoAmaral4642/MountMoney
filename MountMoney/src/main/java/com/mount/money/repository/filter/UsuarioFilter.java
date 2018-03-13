package com.mount.money.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Usuarios.java
public class UsuarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
