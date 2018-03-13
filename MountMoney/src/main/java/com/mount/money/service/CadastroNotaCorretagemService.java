package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.NotaCorretagem;
import com.mount.money.repository.NotasCorretagens;
import com.mount.money.security.Seguranca;
import com.mount.money.util.jpa.Transactional;

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
