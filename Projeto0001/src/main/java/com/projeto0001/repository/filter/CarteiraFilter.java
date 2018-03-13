package com.projeto0001.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.projeto0001.model.Ativo;
import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.model.Usuario;

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
