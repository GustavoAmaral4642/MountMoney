package com.projeto0001.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Banco;
import com.projeto0001.model.MovimentoBanco;
import com.projeto0001.repository.Bancos;
import com.projeto0001.repository.MovimentosBancos;
import com.projeto0001.repository.filter.MovimentoBancoFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMovimentosBancosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentosBancos movimentos;

	@Inject
	private Bancos bancos;

	private MovimentoBancoFilter filtro; // variavel para usar campos no filtro
	private List<MovimentoBanco> movimentosFiltrados; // receber movimentos
														// listados

	// carregar os contas de corretoras que tem movimento na tela
	private List<Banco> todosBancos = new ArrayList<>();

	private BigDecimal valoresDebitados;
	private BigDecimal valoresCreditados;
	private BigDecimal valoresTotais;

	public PesquisaMovimentosBancosBean() {
		limpar();
	}

	// método para excluir movimentos
	public void excluir(MovimentoBanco movimentoSelecionado) {
		// exclui movimento selecionado do BD
		movimentos.remover(movimentoSelecionado);
		// exclui movimento da listagem da tela
		movimentosFiltrados.remove(movimentoSelecionado);

		FacesUtil.addInfoMessage("Movimento de valor 'R$ " + movimentoSelecionado.getValorMovimento() + "', do banco '"
				+ movimentoSelecionado.getBanco().getNomeBanco() + " excluído com sucesso.");
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();
	}

	public void limpar() {
		filtro = new MovimentoBancoFilter();
		movimentosFiltrados = new ArrayList<>();
		valoresDebitados = new BigDecimal(0);
		valoresCreditados = new BigDecimal(0);
		valoresTotais = new BigDecimal(0);
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		movimentosFiltrados = movimentos.filtrados(filtro);
		calcularTotais();
	}

	// método para fazer calculo dos totais
	public void calcularTotais() {

		valoresDebitados = new BigDecimal(0);
		valoresCreditados = new BigDecimal(0);
		valoresTotais = new BigDecimal(0);
		// se o filtro não estiver vazio
		if (!movimentosFiltrados.isEmpty()) {

			for (MovimentoBanco calc : movimentosFiltrados) {

				// faz a saída e entrada de dinheiro
				if (calc.getTipoMovimento().equals("S")) {
					valoresDebitados = valoresDebitados.subtract(calc.getValorMovimento());
					valoresTotais = valoresTotais.subtract(calc.getValorMovimento());
				} else if (calc.getTipoMovimento().equals("E")) {
					valoresCreditados = valoresCreditados.add(calc.getValorMovimento());
					valoresTotais = valoresTotais.add(calc.getValorMovimento());
				}
			}
		}
	}

	public List<MovimentoBanco> getMovimentosFiltrados() {
		return movimentosFiltrados;
	}

	public MovimentoBancoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(MovimentoBancoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Banco> getTodosBancos() {
		return todosBancos;
	}

	public BigDecimal getValoresDebitados() {
		return valoresDebitados;
	}

	public void setValoresDebitados(BigDecimal valoresDebitados) {
		this.valoresDebitados = valoresDebitados;
	}

	public BigDecimal getValoresCreditados() {
		return valoresCreditados;
	}

	public void setValoresCreditados(BigDecimal valoresCreditados) {
		this.valoresCreditados = valoresCreditados;
	}

	public BigDecimal getValoresTotais() {
		return valoresTotais;
	}

	public void setValoresTotais(BigDecimal valoresTotais) {
		this.valoresTotais = valoresTotais;
	}
}
