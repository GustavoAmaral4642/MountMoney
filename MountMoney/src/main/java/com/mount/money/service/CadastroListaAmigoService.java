package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.ListaAmigo;
import com.mount.money.repository.ListasAmigos;
import com.mount.money.util.jpa.Transactional;

public class CadastroListaAmigoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ListasAmigos listas;

	// metodo para chamar o outro para salvar
	@Transactional
	public ListaAmigo salvar(ListaAmigo lista) {
		
		return listas.guardar(lista);
	}
}
