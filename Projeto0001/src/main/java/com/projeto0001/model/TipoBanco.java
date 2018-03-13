package com.projeto0001.model;

public enum TipoBanco {

	FINANCEIRO("Financeiro"), 
	CAIXA("Caixa");

	private String descricao;

	private TipoBanco(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
