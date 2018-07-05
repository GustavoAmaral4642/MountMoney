package com.mount.money.model.util;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mount.money.model.Categoria;

public class DespesaPorCategoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private BigDecimal valorDespesa;
	private Long qntDespesa;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Long getQntDespesa() {
		return qntDespesa;
	}

	public void setQntDespesa(Long qntDespesa) {
		this.qntDespesa = qntDespesa;
	}

}
