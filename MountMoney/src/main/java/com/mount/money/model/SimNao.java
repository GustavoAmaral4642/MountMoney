package com.mount.money.model;

public enum SimNao {

	SIM("Sim"), 
	NAO("Não");

	private String descricao;

	private SimNao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
