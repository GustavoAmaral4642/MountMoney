package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Usuarios.java
public class GrupoInvestidorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeGrupo;
	
	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

}
