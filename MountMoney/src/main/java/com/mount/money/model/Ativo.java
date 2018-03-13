package com.mount.money.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ativo")
public class Ativo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String siglaAtivo;
	private String nomeAtivo;
	private List<Ordem> ordens;
	
	@Id
	@GeneratedValue	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 20)
	@Column(name = "at_sigla_ativo", nullable = false, length = 20)
	public String getSiglaAtivo() {
		return siglaAtivo;
	}

	public void setSiglaAtivo(String siglaAtivo) {
		this.siglaAtivo = siglaAtivo;
	}
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "at_nome_ativo", nullable = false, length = 100)
	public String getNomeAtivo() {
		return nomeAtivo;
	}

	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}
	
	@OneToMany(mappedBy="ativo")
	public List<Ordem> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<Ordem> ordens) {
		this.ordens = ordens;
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
		if (!(obj instanceof Ativo))
			return false;
		Ativo other = (Ativo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
