package com.projeto0001.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.TituloPagar;
import com.projeto0001.service.GerarTituloPagarService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@RequestScoped
public class GerarTituloPagarBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GerarTituloPagarService gerarTituloPagarService;
	
	@Inject
	@TituloPagarEdicao
	private TituloPagar titulo;
	
	// injetando algo que possibilita lançar um novo evento do tipo tituloPagarAlteradoEvent 
	@Inject
	private Event<TituloPagarAlteradoEvent> tituloPagarAlteradoEvent;
	
	// método para gerar titulo
	public void gerarTitulo(){
		
		try{
			this.titulo = this.gerarTituloPagarService.gerar(this.titulo);
			
			// lançar um evento CDI - Atualizar a página depois do titulo gerado
			this.tituloPagarAlteradoEvent.fire(new TituloPagarAlteradoEvent(this.titulo));
			
			FacesUtil.addInfoMessage("Titulo registrado com sucesso!");		
		} finally {
			
		}
		
	}
}
