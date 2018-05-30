package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Categoria;
import com.mount.money.repository.Categorias;
import com.mount.money.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	// metodo para chamar o outro para salvar
	@Transactional
	public Categoria salvar(Categoria Categoria) {
		
		return categorias.guardar(Categoria);
	}
}
