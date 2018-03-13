package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.Banco;
import com.projeto0001.repository.Bancos;
import com.projeto0001.util.jpa.Transactional;

public class CadastroBancoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Bancos bancos;

	// metodo para chamar o outro para salvar
	@Transactional
	public Banco salvar(Banco banco) {

		// Teste para tipos de banco caixa
		if (banco.getTipoBanco().getDescricao().toUpperCase().equals("CAIXA")) {
			// grava o banco
			return bancos.guardar(banco);
		}

		// Teste para tipos de banco financeiro
		if (banco.getTipoBanco().getDescricao().toUpperCase().equals("FINANCEIRO")) {

			Banco bancoContaExistente = bancos.porNumeroConta(banco.getNomeBanco(), banco.getNumeroAgencia(),
					banco.getNumeroConta(), banco.getTipoBanco());

			// testa contas iguais
			if (bancoContaExistente != null && !bancoContaExistente.equals(banco)) {
				throw new NegocioException("Número de conta já cadastrado");
			}
		
			// grava o banco
			return bancos.guardar(banco);
		}

		return null;
	}	

}
