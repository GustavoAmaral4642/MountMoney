package com.mount.money.model;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "movimento_bovespa")
public class MovimentoBovespa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataMovimento;
	private String tipoMovimento = "E";
	private ContaCorretora contaCorretora;
	private BigDecimal valorMovimento = BigDecimal.ZERO;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mvbmf_data_movimento", nullable = false)
	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	@NotBlank
	@Column(name = "mvbmf_tipo_movimento", nullable = false, length = 20)
	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	@ManyToOne
	@JoinColumn(name = "mvbmf_contaCorretora_id")
	public ContaCorretora getContaCorretora() {
		return contaCorretora;
	}

	public void setContaCorretora(ContaCorretora contaCorretora) {
		this.contaCorretora = contaCorretora;
	}

	@NotNull
	@Column(name = "mvbmf_valor_movimento", precision = 10, scale = 2)
	public BigDecimal getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(BigDecimal valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	@Column(name = "mvbmf_historico", columnDefinition = "text")
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
		if (getClass() != obj.getClass())
			return false;
		MovimentoBovespa other = (MovimentoBovespa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
