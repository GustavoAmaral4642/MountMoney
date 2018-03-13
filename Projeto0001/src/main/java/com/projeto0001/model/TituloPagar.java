package com.projeto0001.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "titulo_pagar")
public class TituloPagar implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private StatusTituloPagar statusTituloPagar = StatusTituloPagar.PREVISTO;
	private Date dataCriacao;
	private Date dataPagamento;
	private ClienteFornecedor fornecedor;
	private FormaPagamento formaPagamento;
	private int quantidadeParcelas;
	private List<TituloPagarParcela> parcelas = new ArrayList<>();
	private String observacao;
	private BigDecimal valorTitulo = BigDecimal.ZERO;
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	private BigDecimal valorJuros = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private String historico;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ttp_status_titulo", nullable = false, length = 20)
	public StatusTituloPagar getStatusTituloPagar() {
		return statusTituloPagar;
	}

	public void setStatusTituloPagar(StatusTituloPagar statusTituloPagar) {
		this.statusTituloPagar = statusTituloPagar;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ttp_data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	public ClienteFornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ClienteFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ttp_forma_pagamento", length = 20, nullable = false)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@NotNull
	@Column(name = "ttp_quantidade_parcela", nullable = false)
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	@OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<TituloPagarParcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<TituloPagarParcela> parcelas) {
		this.parcelas = parcelas;
	}

	@Column(name = "ttp_titulo_observacao", columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@NotNull
	@Column(name = "ttp_valor_titulo", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorTitulo() {
		return valorTitulo;
	}

	public void setValorTitulo(BigDecimal valorTitulo) {
		this.valorTitulo = valorTitulo;
	}

	@NotNull
	@Column(name = "ttp_valor_desconto", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@NotNull
	@Column(name = "ttp_valor_juros", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}

	@NotNull
	@Column(name = "ttp_valor_total", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
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
		if (!(obj instanceof TituloPagar))
			return false;
		TituloPagar other = (TituloPagar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// recalcular o valor total
	public void recalcularValorTotal() {

		BigDecimal total = BigDecimal.ZERO;
		BigDecimal valorParcelaTitulo = BigDecimal.ZERO;
		BigDecimal valorParcelaJuros = BigDecimal.ZERO;
		BigDecimal valorParcelaDesconto = BigDecimal.ZERO;

		total = total.add(this.getValorJuros()).subtract(this.getValorDesconto());

		if (getParcelas().size() != 0) {
			// calcula valor das parcelas

			for (TituloPagarParcela parcela : this.getParcelas()) {
				valorParcelaTitulo = valorParcelaTitulo.add(parcela.getValorParcela());
				valorParcelaJuros = valorParcelaJuros.add(parcela.getJurosParcela());
				valorParcelaDesconto = valorParcelaDesconto.add(parcela.getDescontoParcela());
			}
		}

		this.setValorTitulo(valorParcelaTitulo);
		this.setValorJuros(valorParcelaJuros);
		this.setValorDesconto(valorParcelaDesconto);

		total = total.add(valorParcelaTitulo);
		total = total.add(valorParcelaJuros);
		total = total.subtract(valorParcelaDesconto);

		this.setValorTotal(total);
	}

	// um titulo novo, tem o id nulo
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	// um titulo existente é o contrário de isNovo
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	// verificar se o titulos é previsto.
	@Transient
	private boolean isPrevisto() {
		return StatusTituloPagar.PREVISTO.equals(this.getStatusTituloPagar());
	}

	// verificar se o titulo está em aberto.
	@Transient
	private boolean isEmAberto() {
		return StatusTituloPagar.EMABERTO.equals(this.getStatusTituloPagar());
	}

	// titulo pode ser pago
	@Transient
	public boolean isTituloPagarPago() {
		return StatusTituloPagar.PAGO.equals(this.getStatusTituloPagar());
	}

	// titulo pode ser alterado
	@Transient
	public boolean isAlteravel() {
		return this.isPrevisto();
	}

	// titulo não pode ser alterado
	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}

	// titulo pode ser pago
	@Transient
	public boolean isPagavel() {
		return this.isExistente() && this.isEmAberto();
	}

	// titulo não pode ser pago
	@Transient
	public boolean isNaoPagavel() {
		return !this.isPagavel();
	}

	// método testa se o valor total do titulo é negativo.
	@Transient
	public boolean isValorTotalNegativo() {
		return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
	}

	// método testa se o valor do titulo é zero.
	@Transient
	public boolean isValorTituloZero() {
		return this.getValorTitulo().compareTo(BigDecimal.ZERO) == 0;
	}

}
