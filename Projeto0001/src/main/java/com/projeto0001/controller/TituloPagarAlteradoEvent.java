package com.projeto0001.controller;

import com.projeto0001.model.TituloPagar;

// essa classe recebe um evento do titulo a pagar
// e encapsula o pedido aqui 
// especificamente, serve para atualizar em tela o pedido depois de emitido
public class TituloPagarAlteradoEvent {

	private TituloPagar titulo;
	
	public TituloPagarAlteradoEvent(TituloPagar titulo){
		this.titulo = titulo;
	}

	public TituloPagar getTitulo() {
		return titulo;
	}
	
}
