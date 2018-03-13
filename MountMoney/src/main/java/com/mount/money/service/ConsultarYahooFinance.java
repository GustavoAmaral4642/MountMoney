package com.mount.money.service;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import com.mount.money.model.Ordem;
import com.mount.money.repository.Ativos;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class ConsultarYahooFinance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ativos ativos;

	private Stock stock;

	public List<Ordem> consultaYahooFinance(List<Ordem> ordens) throws IOException {

		for (Ordem ordem : ordens) {
			stock = YahooFinance.get(ordem.getAtivo().getSiglaAtivo() + ".sa");
			BigDecimal price = stock.getQuote().getPrice();
			ordem.setVrAtualAtivo(price);
		}

		return ordens;
	}

	public BigDecimal consultaAtivo(String ativo) throws IOException {

		BigDecimal vlrAtivo = BigDecimal.ZERO; 
		
		try {
			stock = YahooFinance.get(ativo.trim() + ".SA");
			vlrAtivo = stock.getQuote().getPrice();
		} catch (SocketTimeoutException ex) {
			new NegocioException("Tempo expirado para pesquisa na BMF. Por favor, tente novamente");
		}

		return vlrAtivo;
	}

}
