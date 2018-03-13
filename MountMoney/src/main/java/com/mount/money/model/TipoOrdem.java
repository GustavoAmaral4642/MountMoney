package com.mount.money.model;

public enum TipoOrdem {

	COMPRA("Compra"), 
	VENDA("Venda");

	private String descricao;

	private TipoOrdem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
