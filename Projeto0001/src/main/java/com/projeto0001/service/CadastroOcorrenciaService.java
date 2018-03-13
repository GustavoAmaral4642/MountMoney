package com.projeto0001.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import com.projeto0001.model.Ocorrencia;
import com.projeto0001.model.Ordem;
import com.projeto0001.repository.Ocorrencias;
import com.projeto0001.util.jpa.Transactional;

public class CadastroOcorrenciaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ocorrencias ocorrencias;

	// metodo para chamar o outro para salvar
	@Transactional
	public Ocorrencia salvar(Ocorrencia ocorrencia) {
		//grava o movimento
		return ocorrencias.guardar(ocorrencia);
	}
	
	// recebe ordem para inclusão de ocorrencias
	public Ocorrencia logOrdem(Ordem ordem){
		
		Ocorrencia ocorrencia = new Ocorrencia();
		
		ocorrencia.setDescricao("Inclusão de " + ordem.getTpOrdem().getDescricao() + " do ativo " 
				+ ordem.getAtivo().getSiglaAtivo() + ", quantidade em lotes de " + ordem.getQtMovimentada() + ", no valor de " 
				+ ordem.getVrTotal());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));
		
		return ocorrencia;
	}
}