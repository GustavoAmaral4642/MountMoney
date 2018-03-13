package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.NotaCorretagem;
import com.projeto0001.repository.NotasCorretagens;
import com.projeto0001.security.Seguranca;
import com.projeto0001.util.jpa.Transactional;

public class CadastroNotaCorretagemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private NotasCorretagens notas;

	// metodo para chamar o outro para salvar
	@Transactional
	public NotaCorretagem salvar(NotaCorretagem nota) {
		
		return notas.guardar(nota);
		
	}
	
}
