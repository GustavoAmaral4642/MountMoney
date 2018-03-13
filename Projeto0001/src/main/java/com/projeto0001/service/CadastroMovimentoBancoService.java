package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.MovimentoBanco;
import com.projeto0001.repository.MovimentosBancos;
import com.projeto0001.util.jpa.Transactional;

public class CadastroMovimentoBancoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentosBancos movimentos;

	// metodo para chamar o outro para salvar
	@Transactional
	public MovimentoBanco salvar(MovimentoBanco movimentoBanco) {

		if(movimentoBanco.getTipoMovimento() == null &&
			movimentoBanco.getTipoMovimento().trim().equals(""))
		{
			throw new NegocioException("Selecione se o movimento é 'Entrada' ou 'Saída'");
		}
		//grava o movimento
		return movimentos.guardar(movimentoBanco);
	}
	
}