package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ContaCorretora;
import com.mount.money.model.NotaCorretagem;
import com.mount.money.model.Ordem;
import com.mount.money.repository.ContasCorretoras;
import com.mount.money.repository.NotasCorretagens;
import com.mount.money.repository.Ordens;
import com.mount.money.service.CadastroNotaCorretagemService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroNotaCorretagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroNotaCorretagemService
	@Inject
	private CadastroNotaCorretagemService cadastroNotaCorretagemService;

	@Inject
	private ContasCorretoras contasCorretoras;

	@Inject
	private Ordens ordens;

	@Inject
	private NotasCorretagens notas;

	private NotaCorretagem nota;
	private Ordem ordem;
	private Ordem ordemSelecionada;

	private List<Ordem> ordensTodas;
	private List<Ordem> incluidas;

	private List<ContaCorretora> todasContasCorretoras = new ArrayList<>();

	private boolean carregaNotas = false;
	
	// construtor
	public CadastroNotaCorretagemBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		nota = new NotaCorretagem();
		ordem = new Ordem();
		ordemSelecionada = new Ordem();
		incluidas = new ArrayList<>();
	}

	// iniciar coleções
	public void inicializar() {
		todasContasCorretoras = contasCorretoras.todasContas();

		// se não for atualização de página, carrega listagem
		if (FacesUtil.isNotPostback()) {
			ordensTodas = ordens.todasOrdens();

			// para edição de registros
			if (nota.getNumeroNota() != null) {
				carregarOrdensNotas();
			}
		}
	}

	// metodo é chamado para carregar listagem das ordens na edição de registros
	public void carregarOrdensNotas() {
		
		nota = notas.carregarOrdens(nota);
		incluidas = nota.getOrdens();
		
		carregaNotas = true;
	}

	public void salvar() {

		// adiciona as ordens na nota de corretagem
		if (incluidas.size() > 0 && carregaNotas == false) {
			this.nota.getOrdens().addAll(incluidas);
		}

		this.nota = cadastroNotaCorretagemService.salvar(this.nota);
		limpar();
		FacesUtil.addInfoMessage("Nota de Corretagem gravada com sucesso!");
		
		carregaNotas = false;
	}

	// metodo para adicionar uma ordem dentro da table do cadastro de nota
	public void adicionaOrdem() {
		boolean valida = true;

		// percorre o array de incluidas, para verificar se um determinada
		// ordem já foi selecionada
		if (incluidas.size() > 0 && incluidas.contains(ordem)) {
			valida = false;
		}

		// se "não foi incluída", se ordem não for vazio ou descricao não
		// estiver preenchido com espaços.
		if (ordem == null || ordem.getNumeroOrdem().trim().equals("")) {
			FacesUtil.addErrorMessage("Por favor, selecine uma Ordem!");
		} else if (valida) {
			incluidas.add(ordem);
		} else {
			FacesUtil.addErrorMessage(ordem.getNumeroOrdem() + " já foi adicionada");
		}

	}

	// metodo para excluir uma ordem dentro da table do cadastro de nota
	public void removeOrdem() {

		boolean valida = true;

		// percorre o array de incluidas, para excluir
		if (incluidas.size() > 0 && incluidas.contains(ordemSelecionada)) {
			valida = false;
		}

		// se valida for falso, remove ordem
		if (!valida) {
			incluidas.remove(ordemSelecionada);
			FacesUtil.addInfoMessage(ordemSelecionada.getNumeroOrdem() + " removida da lista!");
		}
	}

	public NotaCorretagem getNota() {
		return nota;
	}

	public void setNota(NotaCorretagem nota) {
		this.nota = nota;
	}

	public List<ContaCorretora> getTodasContasCorretoras() {
		return todasContasCorretoras;
	}

	public Ordem getOrdem() {
		return ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public List<Ordem> getOrdensTodas() {
		return ordensTodas;
	}

	public void setOrdensTodas(List<Ordem> ordensTodas) {
		this.ordensTodas = ordensTodas;
	}

	public List<Ordem> getIncluidas() {
		return incluidas;
	}

	public void setIncluidas(List<Ordem> incluidas) {
		this.incluidas = incluidas;
	}

	public Ordem getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(Ordem ordemSelecionada) {
		this.ordemSelecionada = ordemSelecionada;
	}

	// se o id do banco não for nulo, está editando
	public boolean isEditando() {
		return this.nota.getId() != null;
	}

}
