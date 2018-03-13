package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Trader.java
public class TraderFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeTrader;
	
	public String getNomeTrader() {
		return nomeTrader;
	}

	public void setNomeTrader(String nomeTrader) {
		this.nomeTrader = nomeTrader;
	}

}
