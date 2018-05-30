package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.MovimentoBanco;
import com.mount.money.model.TituloParcela;
import com.mount.money.repository.MovimentosBancos;
import com.mount.money.security.Seguranca;
import com.mount.money.util.jpa.Transactional;

public class CadastroMovimentoBancoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentosBancos movimentos;

	private Seguranca segUsuario = new Seguranca();
	
	// metodo para chamar o outro para salvar
	@Transactional
	public MovimentoBanco salvar(MovimentoBanco movimentoBanco) {

		if(movimentoBanco.getTipoMovimento() == null &&
			movimentoBanco.getTipoMovimento().trim().equals(""))
		{
			throw new NegocioException("Selecione se o movimento é 'Entrada' ou 'Saída'");
		}
		//grava o movimento
		return movimentos.guardar(movimentoBanco);
	}

	public MovimentoBanco alimentaTituloParcela(TituloParcela p) {
		MovimentoBanco movimento = new MovimentoBanco();
		
		movimento.setBanco(p.getBanco());
		movimento.setDataMovimento(p.getDataPagamento());
		movimento.setHistorico("Titulo Pago: " + p.getHistoricoPagamento());
		movimento.setValorMovimento(p.getValorPagamento());
		movimento.setTipoMovimento("S");
		movimento.setUsuario(segUsuario.getUsuario());
		
		return movimento;
	}
	
}