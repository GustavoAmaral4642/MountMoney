package com.mount.money.model;

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
