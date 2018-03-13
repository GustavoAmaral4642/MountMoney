package com.projeto0001.repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projeto0001.model.TituloPagarParcela;

public class TitulosPagarParcela implements Serializable{

	private static final long serialVersionUID = 1L;

	// injeta entity manager
	@Inject
	private EntityManager manager;

	// buscar titulo por ID
	public TituloPagarParcela porId(Long id) {
		return this.manager.find(TituloPagarParcela.class, id);
	}
}
