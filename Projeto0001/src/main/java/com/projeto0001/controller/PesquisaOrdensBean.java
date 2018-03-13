package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.Ativo;
import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.Ordem;
import com.projeto0001.model.OrdemConsulta;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Ativos;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.repository.Ordens;
import com.projeto0001.repository.filter.OrdemFilter;
import com.projeto0001.service.ConsultarYahooFinance;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaOrdensBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ordens ordens;

	@Inject
	private Ativos ativos;

	@Inject
	private ContasCorretoras contasCorretoras;

	@Inject
	private ConsultarYahooFinance c;

	private OrdemFilter filtro; // variavel para usar campos no filtro

	private List<Ordem> ordensFiltradas; // receber ordens listados
	// ativosListados recebe os dados dos ativos para consulta especial
	private List<Ativo> todosAtivos, 
						ativosFiltrados; // receber ativos
	private List<ContaCorretora> todasContasCorretoras; // receber contas
	private List<OrdemConsulta> odsConsultas;
	
	public PesquisaOrdensBean() {
		limpar();
	}

	// método para excluir ordens
	public void excluir(Ordem ordemSelecionada) {
		// exclui ordem selecionado do BD
		ordens.remover(ordemSelecionada);

		// exclui ordem da listagem da tela
		ordensFiltradas.remove(ordemSelecionada);

		FacesUtil.addInfoMessage("Ordem de valor nº " + ordemSelecionada.getId() + " excluída com sucesso.");
	}

	// iniciar coleções
	public void inicializar() {
		todosAtivos = ativos.todosAtivos();
		todasContasCorretoras = contasCorretoras.todasContas();
	}

	public void limpar() {
		filtro = new OrdemFilter();
		ordensFiltradas = new ArrayList<>(); // receber ordens listados
		todosAtivos = new ArrayList<>(); // receber ativos
		ativosFiltrados = new ArrayList<>();
		todasContasCorretoras = new ArrayList<>(); // receber contas
		odsConsultas = new ArrayList<>();
	}

	// realizar pesquisa da tela.
	public void pesquisar() {
		ordensFiltradas = ordens.filtradas(filtro);
	}
	// retorna os valores da enumeracao status
	public TipoOrdem[] getTipoOrdem() {
		return TipoOrdem.values();
	}

	public OrdemFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(OrdemFilter filtro) {
		this.filtro = filtro;
	}

	public List<Ordem> getOrdensFiltradas() {
		return ordensFiltradas;
	}

	public void setOrdensFiltradas(List<Ordem> ordensFiltradas) {
		this.ordensFiltradas = ordensFiltradas;
	}

	public List<Ativo> getAtivosFiltrados() {
		return ativosFiltrados;
	}

	public Ordens getOrdens() {
		return ordens;
	}

	public ContasCorretoras getContasCorretoras() {
		return contasCorretoras;
	}

	public List<Ativo> getTodosAtivos() {
		return todosAtivos;
	}

	public List<ContaCorretora> getTodasContasCorretoras() {
		return todasContasCorretoras;
	}

	public Ativos getAtivos() {
		return ativos;
	}

	public void setAtivos(Ativos ativos) {
		this.ativos = ativos;
	}

	public void setOrdens(Ordens ordens) {
		this.ordens = ordens;
	}

	public void setContasCorretoras(ContasCorretoras contasCorretoras) {
		this.contasCorretoras = contasCorretoras;
	}

	public void setTodosAtivos(List<Ativo> todosAtivos) {
		this.todosAtivos = todosAtivos;
	}

	public List<OrdemConsulta> getOdsConsultas() {
		return odsConsultas;
	}

}
