package com.mount.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrdemTotal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String siglaAtivo;
	private List<OrdemConsulta> odConsultas;

	public String getSiglaAtivo() {
		return siglaAtivo;
	}

	public void setSiglaAtivo(String siglaAtivo) {
		this.siglaAtivo = siglaAtivo;
	}

	public List<OrdemConsulta> getOdConsultas() {
		return odConsultas;
	}

	public void setOdConsultas(List<OrdemConsulta> odConsultas) {
		this.odConsultas = odConsultas;
	}

	public BigDecimal getLucroTotal()	{

		BigDecimal sum = BigDecimal.ZERO;

		for (OrdemConsulta t : odConsultas) {
			sum = sum.add(t.getVrLucro());
		}
		return sum;
	}
		
}
