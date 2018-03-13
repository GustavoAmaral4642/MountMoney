package com.mount.money.repository.filter;

import java.io.Serializable;
import java.util.Date;

// método usado para filtro do Ordem.java
public class OcorrenciaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dtOcorrenciaDe;
	private Date dtOcorrenciaAte;
	private String descricao;
	
	public Date getDtOcorrenciaDe() {
		return dtOcorrenciaDe;
	}

	public void setDtOcorrenciaDe(Date dtOcorrenciaDe) {
		this.dtOcorrenciaDe = dtOcorrenciaDe;
	}

	public Date getDtOcorrenciaAte() {
		return dtOcorrenciaAte;
	}

	public void setDtOcorrenciaAte(Date dtOcorrenciaAte) {
		this.dtOcorrenciaAte = dtOcorrenciaAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
