package com.mount.money.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipo;
	private String descricao;
	private Date dtOcorrencia;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oc_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(max = 100)
	@Column(name = "oc_tipo", length = 100)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@NotBlank
	@Size(max = 255)
	@Column(name = "oc_descricao", nullable = false, length = 255)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "oc_data_ocorrencia", nullable = false)
	public Date getDtOcorrencia() {
		return dtOcorrencia;
	}

	public void setDtOcorrencia(Date dtOcorrencia) {
		this.dtOcorrencia = dtOcorrencia;
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
		if (!(obj instanceof Ocorrencia))
			return false;
		Ocorrencia other = (Ocorrencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
