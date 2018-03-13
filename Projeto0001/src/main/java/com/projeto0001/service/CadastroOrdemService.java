package com.projeto0001.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.projeto0001.model.Ordem;
import com.projeto0001.model.OrdemConsulta;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Ordens;
import com.projeto0001.security.Seguranca;
import com.projeto0001.util.jpa.Transactional;

public class CadastroOrdemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ordens ordens;

	// metodo para chamar o outro para salvar
	@Transactional
	public Ordem salvar(Ordem ordem) {

		if (ordem.isValorCompraZero()) {
			throw new NegocioException("Informe o valor de compra!");
		}

		// grava
		return ordens.guardar(ordem);
	}

	// monta movimentos referente a ordens
	public Ordem ordemMovimento(Ordem ordem) {

		BigDecimal auxiliar = new BigDecimal(ordem.getQtMovimentada());

		ordem.getMovimento().setValorMovimento(ordem.getVrOperacao().multiply(auxiliar));
		ordem.getMovimento().setDataMovimento(ordem.getDtMovimento());
		ordem.getMovimento().setContaCorretora(ordem.getContaCorretora());
		ordem.getMovimento().setHistorico("Referente à ordem " + ordem.getNumeroOrdem());

		if (ordem.getTpOrdem().equals(TipoOrdem.COMPRA)) {
			ordem.getMovimento().setTipoMovimento("S");
		} else {
			ordem.getMovimento().setTipoMovimento("E");
		}

		return ordem;
	}

	// calcula o valor total das ordens
	public Ordem calcularValorTotal(Ordem ordem) {
		BigDecimal vrTotal = BigDecimal.ZERO;

		vrTotal = ordem.getVrOperacao().multiply(BigDecimal.valueOf(ordem.getQtMovimentada()));
		ordem.setVrTotal(vrTotal);

		return ordem;
	}

	// calcular impostos das ordens
	public Ordem calcularImpostos(Ordem ordem) {

		BigDecimal vrEmolumentos = BigDecimal.ZERO;
		BigDecimal vrTxLiquidacao = BigDecimal.ZERO;
		BigDecimal vrCorretagem = BigDecimal.ZERO;

		vrEmolumentos = ordem.getContaCorretora().getTaxaEmolumento().multiply(ordem.getVrTotal());
		vrTxLiquidacao = ordem.getContaCorretora().getTaxaLiquidacao().multiply(ordem.getVrTotal());
		vrCorretagem = ordem.getContaCorretora().getValorCorretagem();

		vrEmolumentos = vrEmolumentos.multiply(new BigDecimal("0.01"));
		vrTxLiquidacao = vrTxLiquidacao.multiply(new BigDecimal("0.01"));

		ordem.setVrEmolumentos(vrEmolumentos);
		ordem.setVrTaxaLiquidacao(vrTxLiquidacao);
		ordem.setVrCorretagem(vrCorretagem);

		return ordem;
	}
	
}