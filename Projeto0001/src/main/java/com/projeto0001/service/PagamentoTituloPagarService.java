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
import com.projeto0001.util.jsf.FacesUtil;

public class PagamentoTituloPagarService implements Serializable{

	private static final long serialVersionUID = 1L;

	//criando repositorio do titulos a pagar
	@Inject
	private TitulosPagar titulos;

	@Transactional
	public TituloPagar salvar(TituloPagar titulo, TituloPagarParcela tituloParcela){
		
		int auxTest = 1;
		if(tituloParcela.isValorPagamentoMaiorParcela()){
			auxTest = 0;
			tituloParcela.setValorPagoParcela(BigDecimal.ZERO);
			throw new NegocioException("Valor do pagamento não pode ser maior que valor da parcela!");			
		} 
		
		if(tituloParcela.isValorPagamentoParcelaZero()){
			auxTest = 0;
			tituloParcela.setValorPagoParcela(BigDecimal.ZERO);
			throw new NegocioException("Por favor, informe o valor para pagamento!");			
		} 
		
		if(tituloParcela.isValorPagamentoMenorParcela()){
			tituloParcela.setDescontoParcela(tituloParcela.getValorParcela().subtract(tituloParcela.getValorPagoParcela()));
			FacesUtil.addInfoMessage("Valor para pagamento é menor que o valor da parcela. A diferença entrou como desconto.");			
		}
		
		if(tituloParcela.isDataPagamentoNaoInformada()){			
			tituloParcela.setDataPagamento(new Date());
		}		
		
		// se entrar em algum dos testes acima, não grava.
		if(auxTest == 0){
			return titulo;
		} else{
			titulo = this.titulos.guardar(titulo);
		}
		
		return titulo;
	}
	
}
