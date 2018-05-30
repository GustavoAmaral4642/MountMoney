package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Despesa;
import com.mount.money.model.MovimentoBanco;
import com.mount.money.model.TituloParcela;
import com.mount.money.repository.Despesas;
import com.mount.money.security.Seguranca;
import com.mount.money.util.jpa.Transactional;
import com.mount.money.util.jsf.FacesUtil;

public class CadastroDespesaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Despesas despesas;

	private Seguranca segUsuario = new Seguranca();

	private MovimentoBanco movimento;

	// metodo para chamar o outro para salvar
	@Transactional
	public Despesa salvar(Despesa despesa) {

		movimento = new MovimentoBanco();

		try {
			movimento.setBanco(despesa.getBanco());
			movimento.setDataMovimento(despesa.getDataDespesa());
			movimento.setHistorico(despesa.getHistorico());
			movimento.setTipoMovimento("S");
			movimento.setValorMovimento(despesa.getValorDespesa());
			movimento.setUsuario(segUsuario.getUsuario());
			
			despesa.setMovimento(movimento);
			
		} catch (RuntimeException e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao tentar gravar a despesa. "
					+ "Por favor, entre em contato com o Administrador do Sistema!");
		}

		// grava a despesa
		return despesas.guardar(despesa);
	}

	public Despesa alimentaTituloParcela(TituloParcela p) {

		Despesa despesa = new Despesa();

		despesa.setDataDespesa(p.getDataPagamento());
		despesa.setHistorico(p.getHistoricoPagamento());
		despesa.setValorDespesa(p.getValorPagamento());
		despesa.setBanco(p.getBanco());
		despesa.setUsuario(segUsuario.getUsuario());

		return despesa;
	}

}