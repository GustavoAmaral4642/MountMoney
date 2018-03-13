package com.projeto0001.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="conta_corretora")
public class ContaCorretora implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private TipoContaCorretora tipoContaCorretora;
	private String nomeContaCorretora;
	private String numeroContaCorretora;
	private BigDecimal taxaEmolumento = BigDecimal.ZERO;;
	private BigDecimal taxaLiquidacao = BigDecimal.ZERO;;
	private BigDecimal valorCorretagem = BigDecimal.ZERO;;
	private BigDecimal valorCustodia = BigDecimal.ZERO;;
	private List<Ordem> ordens;
	private List<NotaCorretagem> notasCorretagens;
	private Usuario usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ccor_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="ccor_tipo_conta", nullable = false, length = 40)
	public TipoContaCorretora getTipoContaCorretora() {
		return tipoContaCorretora;
	}

	public void setTipoContaCorretora(TipoContaCorretora tipoContaCorretora) {
		this.tipoContaCorretora = tipoContaCorretora;
	}
	
	@NotBlank
	@Size(max = 255)
	@Column(name = "ccor_nome_conta", nullable = false, length = 255)
	public String getNomeContaCorretora() {
		return nomeContaCorretora;
	}

	public void setNomeContaCorretora(String nomeContaCorretora) {
		this.nomeContaCorretora = nomeContaCorretora;
	}

	@Size(max = 60)
	@Column(name = "ccor_numero_conta", length = 60)
	public String getNumeroContaCorretora() {
		return numeroContaCorretora;
	}

	public void setNumeroContaCorretora(String numeroContaCorretora) {
		this.numeroContaCorretora = numeroContaCorretora;
	}

	@NotNull
	@Column(name = "ccor_taxa_emolumento", precision = 10, scale = 4)
	public BigDecimal getTaxaEmolumento() {
		return taxaEmolumento;
	}

	public void setTaxaEmolumento(BigDecimal taxaEmolumento) {
		this.taxaEmolumento = taxaEmolumento;
	}

	@NotNull
	@Column(name = "ccor_taxa_liquidacao", precision = 10, scale = 4)
	public BigDecimal getTaxaLiquidacao() {
		return taxaLiquidacao;
	}

	public void setTaxaLiquidacao(BigDecimal taxaLiquidacao) {
		this.taxaLiquidacao = taxaLiquidacao;
	}

	@NotNull
	@Column(name = "ccor_valor_corretagem", precision = 10, scale = 2)
	public BigDecimal getValorCorretagem() {
		return valorCorretagem;
	}

	public void setValorCorretagem(BigDecimal valorCorretagem) {
		this.valorCorretagem = valorCorretagem;
	}
	
	@NotNull
	@Column(name = "ccor_valor_custodia", precision = 10, scale = 2)
	public BigDecimal getValorCustodia() {
		return valorCustodia;
	}

	public void setValorCustodia(BigDecimal valorCustodia) {
		this.valorCustodia = valorCustodia;
	}

	@OneToMany(mappedBy="contaCorretora")	
	public List<Ordem> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<Ordem> ordens) {
		this.ordens = ordens;
	}

	@OneToMany(mappedBy="contaCorretora")	
	public List<NotaCorretagem> getNotasCorretagens() {
		return notasCorretagens;
	}

	public void setNotasCorretagens(List<NotaCorretagem> notasCorretagens) {
		this.notasCorretagens = notasCorretagens;
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
		if (!(obj instanceof ContaCorretora))
			return false;
		ContaCorretora other = (ContaCorretora) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
