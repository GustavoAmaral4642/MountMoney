package com.mount.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrdemConsulta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ativo;
	private Date dtMovimento;
	private Long qntMovimentada = new Long(1);
	private Long qntTotal = new Long(1); // usado somente em
											// CadastroCarteiraService.consultaPrecoMedio
	private TipoOrdem tpOrdem = TipoOrdem.COMPRA;
	private BigDecimal vrOperacao = BigDecimal.ZERO;
	private BigDecimal vrTotal = BigDecimal.ZERO;
	private BigDecimal vrMedio = BigDecimal.ZERO;
	private BigDecimal vrAtivo = BigDecimal.ZERO;
	private BigDecimal vrLucro = BigDecimal.ZERO;
	private BigDecimal vrBmfQnt = BigDecimal.ZERO;
	private BigDecimal vrPercentual = BigDecimal.ZERO;

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(Date dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public Long getQntMovimentada() {
		return qntMovimentada;
	}

	public void setQntMovimentada(Long qntMovimentada) {
		this.qntMovimentada = qntMovimentada;
	}

	public Long getQntTotal() {
		return qntTotal;
	}

	public void setQntTotal(Long qntTotal) {
		this.qntTotal = qntTotal;
	}

	public TipoOrdem getTpOrdem() {
		return tpOrdem;
	}

	public void setTpOrdem(TipoOrdem tpOrdem) {
		this.tpOrdem = tpOrdem;
	}

	public BigDecimal getVrOperacao() {
		return vrOperacao;
	}

	public void setVrOperacao(BigDecimal vrOperacao) {
		this.vrOperacao = vrOperacao;
	}

	public BigDecimal getVrTotal() {
		return vrTotal;
	}

	public void setVrTotal(BigDecimal vrTotal) {
		this.vrTotal = vrTotal;
	}

	public BigDecimal getVrMedio() {
		return vrMedio;
	}

	public void setVrMedio(BigDecimal vrMedio) {
		this.vrMedio = vrMedio;
	}

	public BigDecimal getVrAtivo() {
		return vrAtivo;
	}

	public void setVrAtivo(BigDecimal vrAtivo) {
		this.vrAtivo = vrAtivo;
	}

	public BigDecimal getVrLucro() {
		return vrLucro;
	}

	public void setVrLucro(BigDecimal vrLucro) {
		this.vrLucro = vrLucro;
	}

	public BigDecimal getVrBmfQnt() {
		return vrBmfQnt;
	}

	public void setVrBmfQnt(BigDecimal vrBmfQnt) {
		this.vrBmfQnt = vrBmfQnt;
	}

	public BigDecimal getVrPercentual() {
		return vrPercentual;
	}

	public void setVrPercentual(BigDecimal vrPercentual) {
		this.vrPercentual = vrPercentual;
	}

}
