package com.projeto0001.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto0001.model.StatusTituloPagar;
import com.projeto0001.model.TituloPagar;
import com.projeto0001.repository.TitulosPagar;
import com.projeto0001.util.jpa.Transactional;

public class GerarTituloPagarService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroTituloPagarService cadastroTituloPagarService;
	
	@Inject
	private TitulosPagar titulos;
	
	// salva o titulo gerado
	@Transactional
	public TituloPagar gerar(TituloPagar titulo){
		
		titulo = this.cadastroTituloPagarService.salvar(titulo);
		
		// se não puder emitir o titulo
		/*if(titulo.isNaoEmissivel()){
			throw new NegocioException("Pedido não pode ser emitido com status " + 
					pedido.getStatus().getDescricao());
		}*/
		
		// trocar status do titulo
		titulo.setStatusTituloPagar(StatusTituloPagar.EMABERTO);
		
		// gravar o titulo
		titulo = this.titulos.guardar(titulo);
		
		// retornar titulo gravado
		return titulo;
	}
}
