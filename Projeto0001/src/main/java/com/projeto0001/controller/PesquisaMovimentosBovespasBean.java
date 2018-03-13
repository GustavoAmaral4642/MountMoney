package com.projeto0001.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.MovimentoBovespa;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.repository.MovimentosBovespas;
import com.projeto0001.repository.filter.MovimentoBovespaFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMovimentosBovespasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentosBovespas movimentos;

	@Inject
	private ContasCorretoras contasCorretoras;
	
	private MovimentoBovespaFilter filtro; // variavel para usar campos no filtro
	private List<MovimentoBovespa> movimentosFiltrados; // receber movimentos
															// listados
	//carregar os contas de corretoras que tem movimento na tela
	private List<ContaCorretora> todasContasCorretoras = new ArrayList<>();
	
	private BigDecimal valoresDebitados;
	private BigDecimal valoresCreditados;
	private BigDecimal valoresTotais;
	
	public PesquisaMovimentosBovespasBean() {
		limpar();
	}

	// método para excluir movimentos
	public void excluir(MovimentoBovespa movimentoSelecionado) {
		// exclui movimento selecionado do BD
		movimentos.remover(movimentoSelecionado);
		// exclui movimento da listagem da tela
		movimentosFiltrados.remove(movimentoSelecionado);

		FacesUtil.addInfoMessage("Movimento de valor 'R$ " + movimentoSelecionado.getValorMovimento() + "', da conta de corretora '"
				+ movimentoSelecionado.getContaCorretora().getNomeContaCorretora() + "excluído com sucesso.");
	}

	// iniciar coleções
	public void inicializar() {		
		todasContasCorretoras = contasCorretoras.todasContas();
	}

	public void limpar(){
		filtro = new MovimentoBovespaFilter();
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
	public void calcularTotais(){
		
		valoresDebitados = new BigDecimal(0);
		valoresCreditados = new BigDecimal(0);
		valoresTotais = new BigDecimal(0);
		// se o filtro não estiver vazio
		if(!movimentosFiltrados.isEmpty()){
			
			for(MovimentoBovespa calc : movimentosFiltrados){
			
				// faz a saída e entrada de dinheiro
				if(calc.getTipoMovimento().equals("S")){
					valoresDebitados = valoresDebitados.subtract(calc.getValorMovimento());
					valoresTotais = valoresTotais.subtract(calc.getValorMovimento());
				} else if (calc.getTipoMovimento().equals("E")){
					valoresCreditados = valoresCreditados.add(calc.getValorMovimento());
					valoresTotais = valoresTotais.add(calc.getValorMovimento());
				}	
			}
		}
	}

	public List<MovimentoBovespa> getMovimentosFiltrados() {
		return movimentosFiltrados;
	}

	public MovimentoBovespaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(MovimentoBovespaFilter filtro) {
		this.filtro = filtro;
	}

	public List<ContaCorretora> getTodasContasCorretoras() {
		return todasContasCorretoras;
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
