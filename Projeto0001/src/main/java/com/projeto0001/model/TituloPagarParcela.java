package com.projeto0001.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "titulo_pagar_parcela")
public class TituloPagarParcela implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int numeroParcela;
	private BigDecimal valorParcela = BigDecimal.ZERO;
	private BigDecimal descontoParcela = BigDecimal.ZERO;
	private BigDecimal jurosParcela = BigDecimal.ZERO;
	private BigDecimal valorPagoParcela = BigDecimal.ZERO;
	private Date dataVencimento;
	private Date dataPagamento;
	private String historico;
	private TituloPagar titulo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "tpp_numero_parcela", nullable = false)
	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	@NotNull
	@Column(name = "tpp_valor_parcela", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getDescontoParcela() {
		return descontoParcela;
	}

	public void setDescontoParcela(BigDecimal descontoParcela) {
		this.descontoParcela = descontoParcela;
	}

	public BigDecimal getJurosParcela() {
		return jurosParcela;
	}

	public void setJurosParcela(BigDecimal jurosParcela) {
		this.jurosParcela = jurosParcela;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tpp_data_vencimento", nullable = false)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "tpp_historico", columnDefinition = "text")
	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	@ManyToOne
	@JoinColumn(name = "titulo_id", nullable = false)
	public TituloPagar getTitulo() {
		return titulo;
	}

	public void setTitulo(TituloPagar titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValorPagoParcela() {
		return valorPagoParcela;
	}

	public void setValorPagoParcela(BigDecimal valorPagoParcela) {
		this.valorPagoParcela = valorPagoParcela;
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
		if (!(obj instanceof TituloPagarParcela))
			return false;
		TituloPagarParcela other = (TituloPagarParcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// testar se a parcela já foi paga
	@Transient
	public boolean isParcelaPago() {
		return this.getValorPagoParcela().compareTo(BigDecimal.ZERO) == 1;
	}

	// método testa se o valor do pagamento da parcela é zero.
	@Transient
	public boolean isValorPagamentoParcelaZero() {
		return this.getValorPagoParcela().compareTo(BigDecimal.ZERO) == 0;
	}

	// método testa se o valor do pagamento é maior que o valor da parcela.
	@Transient
	public boolean isValorPagamentoMaiorParcela() {
		return this.getValorPagoParcela().compareTo(getValorParcela()) == 1;
	}

	// método testa se o valor do pagamento é menor que o valor da parcela.
	@Transient
	public boolean isValorPagamentoMenorParcela() {
		return this.getValorPagoParcela().compareTo(getValorParcela()) == -1;
	}

	// método testa se a data do pagamento da parcela não foi informada.
	@Transient
	public boolean isDataPagamentoNaoInformada() {
		return this.getDataPagamento() == null;
	}
}
