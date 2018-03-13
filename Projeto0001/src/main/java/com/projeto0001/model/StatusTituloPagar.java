package com.projeto0001.model;

public enum StatusTituloPagar {

	PREVISTO("Previsto"),
	EMABERTO("Em aberto"), 
	VENCIDO("Vencido"), 
	PAGO("Pago"),
	CANCELADO("Cancelado");
	
	private String descricao;

	private StatusTituloPagar(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}

