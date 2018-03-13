package com.projeto0001.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projeto0001.model.Endereco;

public class Enderecos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}

}
