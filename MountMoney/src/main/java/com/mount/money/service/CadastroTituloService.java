package com.mount.money.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.mount.money.model.Titulo;
import com.mount.money.model.TituloParcela;
import com.mount.money.repository.Titulos;
import com.mount.money.util.jpa.Transactional;

public class CadastroTituloService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Titulos titulos;

	// metodo para chamar o outro para salvar
	@Transactional
	public Titulo salvar(Titulo titulo, List<TituloParcela> parcelas) {
		
		titulo.setTitulosParcelas(parcelas);
		
		//grava a titulo
		return titulos.guardar(titulo);
	}
	
}