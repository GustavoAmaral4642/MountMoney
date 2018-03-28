package com.mount.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ordem")
public class Ordem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numeroOrdem;
	private ContaCorretora contaCorretora;
	private Date dtMovimento;
	private TipoOrdem tpOrdem;
	private Ativo ativo;
	private Long qtMovimentada;
	private BigDecimal vrOperacao = BigDecimal.ZERO;
	private MovimentoBovespa movimento;
	private BigDecimal vrTotal = BigDecimal.ZERO;
	private BigDecimal vrAtualAtivo = BigDecimal.ZERO;
	private BigDecimal vrCorretagem = BigDecimal.ZERO;
	private BigDecimal vrEmolumentos = BigDecimal.ZERO;
	private BigDecimal vrTaxaLiquidacao = BigDecimal.ZERO;
	private Usuario usuario;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orde_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "orde_num_ordem", length = 25)
	public String getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_conta_corretora")
	public ContaCorretora getContaCorretora() {
		return contaCorretora;
	}

	public void setContaCorretora(ContaCorretora contaCorretora) {
		this.contaCorretora = contaCorretora;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orde_data_movimento", nullable = false)
	public Date getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(Date dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "orde_tipo", nullable = false, length = 15)
	public TipoOrdem getTpOrdem() {
		return tpOrdem;
	}

	public void setTpOrdem(TipoOrdem tpOrdem) {
		this.tpOrdem = tpOrdem;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_ativo")
	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	@NotNull
	@Column(name = "orde_qtde")
	public Long getQtMovimentada() {
		return qtMovimentada;
	}

	public void setQtMovimentada(Long qtMovimentada) {
		this.qtMovimentada = qtMovimentada;
	}

	@NotNull
	@Column(name = "orde_vr_operacao", precision = 10, scale = 3)
	public BigDecimal getVrOperacao() {
		return vrOperacao;
	}

	public void setVrOperacao(BigDecimal vrOperacao) {
		this.vrOperacao = vrOperacao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orde_movimento_bancario")
	public MovimentoBovespa getMovimento() {
		return movimento;
	}

	public void setMovimento(MovimentoBovespa movimento) {
		this.movimento = movimento;
	}

	@NotNull
	@Column(name = "orde_vr_total", precision = 10, scale = 3)
	public BigDecimal getVrTotal() {
		return vrTotal;
	}

	public void setVrTotal(BigDecimal vrTotal) {
		this.vrTotal = vrTotal;
	}

	/* apenas para consultar o ativo na receita */
	public BigDecimal getVrAtualAtivo() {
		return vrAtualAtivo;
	}

	public void setVrAtualAtivo(BigDecimal vrAtualAtivo) {
		this.vrAtualAtivo = vrAtualAtivo;
	}

	@Column(name = "orde_vr_corretagem", precision = 10, scale = 2)
	public BigDecimal getVrCorretagem() {
		return vrCorretagem;
	}

	public void setVrCorretagem(BigDecimal vrCorretagem) {
		this.vrCorretagem = vrCorretagem;
	}

	@Column(name = "orde_vr_emolumentos", precision = 10, scale = 4)
	public BigDecimal getVrEmolumentos() {
		return vrEmolumentos;
	}

	public void setVrEmolumentos(BigDecimal vrEmolumentos) {
		this.vrEmolumentos = vrEmolumentos;
	}

	@Column(name = "orde_vr_tx_liquidacao", precision = 10, scale = 4)
	public BigDecimal getVrTaxaLiquidacao() {
		return vrTaxaLiquidacao;
	}

	public void setVrTaxaLiquidacao(BigDecimal vrTaxaLiquidacao) {
		this.vrTaxaLiquidacao = vrTaxaLiquidacao;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		if (!(obj instanceof Ordem))
			return false;
		Ordem other = (Ordem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// método testa se o valor do pedido é negativo.
	@Transient
	public boolean isValorCompraZero() {
		return this.vrOperacao.compareTo(BigDecimal.ZERO) == 0;
	}
}
