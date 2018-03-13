package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.Contato;
import com.projeto0001.model.Endereco;
import com.projeto0001.repository.Clientes;
import com.projeto0001.util.jpa.Transactional;

public class CadastroClienteFornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	// metodo para chamar o outro para salvar
	@Transactional
	public ClienteFornecedor salvar(ClienteFornecedor cliente) {

		return clientes.guardar(cliente);
	}

	// verifica se o endereco está em branco
	public Endereco adicionaEndereco(Endereco endereco) {

		if (endereco.getLogradouro() == null || endereco.getLogradouro().trim() == "") {
			throw new NegocioException("`Logradouro(Nome da rua)` não pode estar vazio!");
		}

		return endereco;
	}

	// verifica se o contato está em branco
	public Contato adicionaContato(Contato contato) {

		if (contato.getNomeContato() == null || contato.getNomeContato().trim() == "") {
			throw new NegocioException("`Nome do contato` não pode estar vazio!");
		}

		return contato;
	}

}
