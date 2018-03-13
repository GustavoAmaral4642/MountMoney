package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Ocorrencia;
import com.mount.money.model.TipoBanco;
import com.mount.money.repository.Bancos;
import com.mount.money.repository.filter.BancoFilter;
import com.mount.money.service.CadastroOcorrenciaService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaBancosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injetando classe bancos
	@Inject
	private Bancos bancos;
	
	@Inject
	private CadastroOcorrenciaService cadastroOcorrenciaService;

	private Ocorrencia ocorrencia;
	private BancoFilter filtro; // variavel para usar campos no filtro
	private List<Banco> bancosFiltrados; // receber bancos para listagem

	public PesquisaBancosBean() {
		limpar();
	}

	public void limpar() {
		filtro = new BancoFilter();
		bancosFiltrados = new ArrayList<>();
	}

	// metodo para excluir bancos
	public void excluir(Banco bancoSelecionado) {
		// exclui banco do banco de dados
		bancos.remover(bancoSelecionado);
		// exclui bancos da lista
		bancosFiltrados.remove(bancoSelecionado);

		// tratar e salvar ocorrencia
		this.ocorrencia = cadastroOcorrenciaService.logBancoD(bancoSelecionado);
		this.ocorrencia = cadastroOcorrenciaService.salvar(ocorrencia);

		FacesUtil.addInfoMessage("Banco " + bancoSelecionado.getNomeBanco() + " excluído com sucesso!");
	}

	public void pesquisar() {
		
		bancosFiltrados = bancos.filtrados(filtro);
	}

	public List<Banco> getBancosFiltrados() {
		return bancosFiltrados;
	}

	public BancoFilter getFiltro() {
		return filtro;
	}
	
	// retorna os valores da enumeracao status
	public TipoBanco[] getTipoBanco(){
		return TipoBanco.values();
	}

}
