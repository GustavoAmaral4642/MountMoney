package com.mount.money.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nota_corretagem")
public class NotaCorretagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numeroNota;
	private Date dataPregao;
	private ContaCorretora contaCorretora;
	private String contaBancaria;
	// private ItemNota itemNota;
	private BigDecimal vlrLiquidoOperacoes = BigDecimal.ZERO;
	private BigDecimal vlrTaxaLiquidacao = BigDecimal.ZERO;
	private BigDecimal vlrTaxaRegistro = BigDecimal.ZERO;
	private BigDecimal vlrTotalCblc = BigDecimal.ZERO;
	private BigDecimal vlrTaxaTermoOpcoes = BigDecimal.ZERO;
	private BigDecimal vlrTaxaAna = BigDecimal.ZERO;
	private BigDecimal vlrTaxaEmolumentos = BigDecimal.ZERO;
	private BigDecimal vlrTotalBovespaSoma = BigDecimal.ZERO;
	private BigDecimal vlrCorretagem = BigDecimal.ZERO;
	private BigDecimal vlrIss = BigDecimal.ZERO;
	private BigDecimal vlrIrrf = BigDecimal.ZERO;
	private BigDecimal vlrOutras = BigDecimal.ZERO;
	private BigDecimal vlrTotalCorretagemDespesas = BigDecimal.ZERO;
	private BigDecimal vlrTotalLiquido = BigDecimal.ZERO;
	private List<Ordem> ordens = new ArrayList<>();
	private Usuario usuario;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nt_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nt_numero", length = 25)
	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nt_data", nullable = false)
	public Date getDataPregao() {
		return dataPregao;
	}

	public void setDataPregao(Date dataPregao) {
		this.dataPregao = dataPregao;
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

	@Column(name = "nt_contaBancaria", length = 25)
	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	@NotNull
	@Column(name = "nt_vlr_liquido_operacores", precision = 10, scale = 3)
	public BigDecimal getVlrLiquidoOperacoes() {
		return vlrLiquidoOperacoes;
	}

	public void setVlrLiquidoOperacoes(BigDecimal vlrLiquidoOperacoes) {
		this.vlrLiquidoOperacoes = vlrLiquidoOperacoes;
	}

	@NotNull
	@Column(name = "nt_vlr_taxa_liquidacao", precision = 10, scale = 3)
	public BigDecimal getVlrTaxaLiquidacao() {
		return vlrTaxaLiquidacao;
	}

	public void setVlrTaxaLiquidacao(BigDecimal vlrTaxaLiquidacao) {
		this.vlrTaxaLiquidacao = vlrTaxaLiquidacao;
	}

	@NotNull
	@Column(name = "nt_vlr_taxa_registro", precision = 10, scale = 3)
	public BigDecimal getVlrTaxaRegistro() {
		return vlrTaxaRegistro;
	}

	public void setVlrTaxaRegistro(BigDecimal vlrTaxaRegistro) {
		this.vlrTaxaRegistro = vlrTaxaRegistro;
	}

	@NotNull
	@Column(name = "nt_vlr_total_cblc", precision = 10, scale = 3)
	public BigDecimal getVlrTotalCblc() {
		return vlrTotalCblc;
	}

	public void setVlrTotalCblc(BigDecimal vlrTotalCblc) {
		this.vlrTotalCblc = vlrTotalCblc;
	}

	@NotNull
	@Column(name = "nt_vlr_taxa_termo_opcoes", precision = 10, scale = 3)
	public BigDecimal getVlrTaxaTermoOpcoes() {
		return vlrTaxaTermoOpcoes;
	}

	public void setVlrTaxaTermoOpcoes(BigDecimal vlrTaxaTermoOpcoes) {
		this.vlrTaxaTermoOpcoes = vlrTaxaTermoOpcoes;
	}

	@NotNull
	@Column(name = "nt_vlr_taxa_ana", precision = 10, scale = 3)
	public BigDecimal getVlrTaxaAna() {
		return vlrTaxaAna;
	}

	public void setVlrTaxaAna(BigDecimal vlrTaxaAna) {
		this.vlrTaxaAna = vlrTaxaAna;
	}

	@NotNull
	@Column(name = "nt_vlr_taxa_emolumentos", precision = 10, scale = 3)
	public BigDecimal getVlrTaxaEmolumentos() {
		return vlrTaxaEmolumentos;
	}

	public void setVlrTaxaEmolumentos(BigDecimal vlrTaxaEmolumentos) {
		this.vlrTaxaEmolumentos = vlrTaxaEmolumentos;
	}

	@NotNull
	@Column(name = "nt_vlr_total_bovespa_soma", precision = 10, scale = 3)
	public BigDecimal getVlrTotalBovespaSoma() {
		return vlrTotalBovespaSoma;
	}

	public void setVlrTotalBovespaSoma(BigDecimal vlrTotalBovespaSoma) {
		this.vlrTotalBovespaSoma = vlrTotalBovespaSoma;
	}

	@NotNull
	@Column(name = "nt_vlr_corretagem", precision = 10, scale = 3)
	public BigDecimal getVlrCorretagem() {
		return vlrCorretagem;
	}

	public void setVlrCorretagem(BigDecimal vlrCorretagem) {
		this.vlrCorretagem = vlrCorretagem;
	}

	@NotNull
	@Column(name = "nt_vlr_iss", precision = 10, scale = 3)
	public BigDecimal getVlrIss() {
		return vlrIss;
	}

	public void setVlrIss(BigDecimal vlrIss) {
		this.vlrIss = vlrIss;
	}

	@NotNull
	@Column(name = "nt_vlr_Irrf", precision = 10, scale = 3)
	public BigDecimal getVlrIrrf() {
		return vlrIrrf;
	}

	public void setVlrIrrf(BigDecimal vlrIrrf) {
		this.vlrIrrf = vlrIrrf;
	}

	@NotNull
	@Column(name = "nt_vlr_outras", precision = 10, scale = 3)
	public BigDecimal getVlrOutras() {
		return vlrOutras;
	}

	public void setVlrOutras(BigDecimal vlrOutras) {
		this.vlrOutras = vlrOutras;
	}

	@NotNull
	@Column(name = "nt_vlr_total_corretagem_despesas", precision = 10, scale = 3)
	public BigDecimal getVlrTotalCorretagemDespesas() {
		return vlrTotalCorretagemDespesas;
	}

	public void setVlrTotalCorretagemDespesas(BigDecimal vlrTotalCorretagemDespesas) {
		this.vlrTotalCorretagemDespesas = vlrTotalCorretagemDespesas;
	}

	@NotNull
	@Column(name = "nt_vlr_total_liquido", precision = 10, scale = 3)
	public BigDecimal getVlrTotalLiquido() {
		return vlrTotalLiquido;
	}

	public void setVlrTotalLiquido(BigDecimal vlrTotalLiquido) {
		this.vlrTotalLiquido = vlrTotalLiquido;
	}

	@NotNull
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "nota_ordem", joinColumns = @JoinColumn(name = "nota_id") , inverseJoinColumns = @JoinColumn(name = "ordem_id") )
	public List<Ordem> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<Ordem> ordens) {
		this.ordens = ordens;
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
		if (!(obj instanceof NotaCorretagem))
			return false;
		NotaCorretagem other = (NotaCorretagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
