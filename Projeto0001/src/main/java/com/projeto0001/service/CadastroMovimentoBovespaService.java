package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.MovimentoBovespa;
import com.projeto0001.repository.MovimentosBovespas;
import com.projeto0001.util.jpa.Transactional;

public class CadastroMovimentoBovespaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentosBovespas movimentos;

	// metodo para chamar o outro para salvar
	@Transactional
	public MovimentoBovespa salvar(MovimentoBovespa movimentoBovespa) {

		if(movimentoBovespa.getTipoMovimento() == null &&
				movimentoBovespa.getTipoMovimento().trim().equals(""))
		{
			throw new NegocioException("Selecione se o movimento é 'Entrada' ou 'Saída'");
		}
		//grava o movimento
		return movimentos.guardar(movimentoBovespa);
	}
	
}