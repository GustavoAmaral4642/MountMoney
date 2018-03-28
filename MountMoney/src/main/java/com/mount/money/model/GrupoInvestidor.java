package com.mount.money.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_investidor")
public class GrupoInvestidor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeGrupo;
	private String descricao;
	private List<Usuario> usuarios = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grpinv_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "grpinv_nome_grupo", nullable = false, length = 80)
	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	@Column(name = "grpinv_descricao", length = 255)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "grpinv_usuario", joinColumns = @JoinColumn(name = "grpinv_id") , inverseJoinColumns = @JoinColumn(name = "usuario_id") )
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		if (!(obj instanceof GrupoInvestidor))
			return false;
		GrupoInvestidor other = (GrupoInvestidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
