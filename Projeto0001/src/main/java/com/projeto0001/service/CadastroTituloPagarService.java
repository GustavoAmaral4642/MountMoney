package com.projeto0001.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import com.projeto0001.model.StatusTituloPagar;
import com.projeto0001.model.TituloPagar;
import com.projeto0001.model.TituloPagarParcela;
import com.projeto0001.repository.TitulosPagar;
import com.projeto0001.util.jpa.Transactional;

public class CadastroTituloPagarService implements Serializable{

	private static final long serialVersionUID = 1L;

	//criando repositorio do titulos a pagar
	@Inject
	private TitulosPagar titulos;

	@Transactional
	public TituloPagar salvar(TituloPagar titulo){
		
		if(titulo.isValorTituloZero()){
			throw new NegocioException("Valor do titulo nâo pode ser 0.00");
		}
		
		// se o titulo for novo, atribui status e data
		if(titulo.isNovo()){
			titulo.setDataCriacao(new Date());
			titulo.setStatusTituloPagar(StatusTituloPagar.PREVISTO);
		}
		
		titulo.recalcularValorTotal();
		
		// testa se pode alterar pedido
		if(titulo.isNaoAlteravel()){
			throw new NegocioException("Titulo a pagar não pode ser alterado no status "
					+ titulo.getStatusTituloPagar().getDescricao() + ".");
		}
		
		// testa se o titulo tem parcelas
		if(titulo.getQuantidadeParcelas() == 0){
			throw new NegocioException("Mensagem 2: O titulo deve possuir pelo meno uma parcela.");
		}
		
		// testa se o valor do titulo está negativo
		if(titulo.isValorTotalNegativo()){
			throw new NegocioException("O valor total do titulo não pode ser negativo");
		}
		
		titulo = this.titulos.guardar(titulo);
		
		return titulo;
	}
	
}
