package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Ativo;
import com.mount.money.model.Ocorrencia;
import com.mount.money.repository.Ativos;
import com.mount.money.repository.filter.AtivoFilter;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAtivosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injetando classe ativos
	@Inject
	private Ativos ativos;
	
	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;

	private Ocorrencia ocorrencia;
	
	private AtivoFilter filtro; // variavel para usar campos no filtro
	private List<Ativo> ativosFiltrados; // receber ativos para listagem

	public PesquisaAtivosBean() {
		limpar();
	}

	// metodo para excluir ativos
	public void excluir(Ativo ativoSelecionado) {
		// exclui ativo do banco de dados
		ativos.remover(ativoSelecionado);
		// exclui ativos da lista
		ativosFiltrados.remove(ativoSelecionado);

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logAtivoD(ativoSelecionado);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

		FacesUtil.addInfoMessage("Ativo " + ativoSelecionado.getSiglaAtivo() + " excluído com sucesso!");
	}

	public void limpar() {
		filtro = new AtivoFilter();
		ativosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		ativosFiltrados = ativos.filtrados(filtro);
	}

	public List<Ativo> getAtivosFiltrados() {
		return ativosFiltrados;
	}

	public AtivoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AtivoFilter filtro) {
		this.filtro = filtro;
	}

}
