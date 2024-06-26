package com.mount.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "titulo_parcela")
public class TituloParcela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Titulo titulo;
	private Banco banco;
	private Long numeroParcela;
	private Date dataVencimento;
	private Date dataPagamento;
	private BigDecimal valorParcela = BigDecimal.ZERO;
	private BigDecimal valorPagamento = BigDecimal.ZERO;
	private String historicoPagamento;
	private Despesa despesa;
	private MovimentoBanco movimento;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ttp_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "titulo_id")
	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	@ManyToOne
	@JoinColumn(name = "ttp_banco_id")
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Column(name = "ttp_numero_parcela")
	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ttp_data_vencimento", nullable = false)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ttp_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@NotNull
	@Column(name = "ttp_valor_parcela", precision = 10, scale = 2)
	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	@Column(name = "ttp_valor_pagamento", precision = 10, scale = 2)
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	@Column(name = "ttp_historico", columnDefinition = "text")
	public String getHistoricoPagamento() {
		return historicoPagamento;
	}

	public void setHistoricoPagamento(String historicoPagamento) {
		this.historicoPagamento = historicoPagamento;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ttp_despesa")
	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ttp_movimento_banco")
	public MovimentoBanco getMovimento() {
		return movimento;
	}

	public void setMovimento(MovimentoBanco movimento) {
		this.movimento = movimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TituloParcela))
			return false;
		TituloParcela other = (TituloParcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
