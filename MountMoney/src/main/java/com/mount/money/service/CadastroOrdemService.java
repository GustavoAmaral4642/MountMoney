package com.mount.money.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import com.mount.money.model.Ordem;
import com.mount.money.model.TipoOrdem;
import com.mount.money.repository.Ordens;
import com.mount.money.util.jpa.Transactional;

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

	// pesquisar se a ordem já foi cadastrada
	public Ordem pesquisaOrdemCadastrada(Ordem ordem) {

		ordem = ordens.porNumero(ordem.getNumeroOrdem());

		if (ordem != null) {
			throw new NegocioException("Já existe uma ordem com esta numeração cadastrada!");
		}

		return ordem;
	}

}