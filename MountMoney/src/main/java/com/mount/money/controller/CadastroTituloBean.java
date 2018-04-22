package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Titulo;
import com.mount.money.model.TituloParcela;
import com.mount.money.repository.Bancos;
import com.mount.money.service.CadastroTituloService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTituloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroTituloService
	@Inject
	private CadastroTituloService cadastroTituloService;

	// injetando classe bancos
	@Inject
	private Bancos bancos;

	private List<TituloParcela> parcelas = new ArrayList<>();

	private Titulo titulo;
	private TituloParcela ttParcela;
	private Banco banco;

	private Long numeroParcela;
	private boolean adicionou = false;

	// carregar os bancos na tela
	private List<Banco> todosBancos = new ArrayList<>();

	// construtor
	public CadastroTituloBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		titulo = new Titulo();
		ttParcela = new TituloParcela();
		numeroParcela = new Long(0);
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();

	}

	public void salvar() {
		titulo.setTitulosParcelas(parcelas);

		this.titulo = cadastroTituloService.salvar(this.titulo);
		limpar();
		FacesUtil.addInfoMessage("Titulo registrado com sucesso!");
	}

	public void adicionarParcela() {

		// metodo controla numero da parcela
		if (ttParcela.getNumeroParcela() == null) {
			numeroParcela++;
			ttParcela.setNumeroParcela(numeroParcela);
		} else if (adicionou) {
			numeroParcela++;
			ttParcela.setNumeroParcela(numeroParcela);
			adicionou = false;
		}

		ttParcela.setTitulo(titulo);
		parcelas.add(ttParcela);

		adicionou = true;
		ttParcela = new TituloParcela();
	}

	// método para excluir ordens
	public void excluirParcela(TituloParcela parcelaSelecionada) {

		parcelas.remove(parcelaSelecionada);
		
		FacesUtil.addInfoMessage("Parcela número " + parcelaSelecionada.getId() + " excluída com sucesso.");
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<Banco> getTodosBancos() {
		return todosBancos;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public List<TituloParcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<TituloParcela> parcelas) {
		this.parcelas = parcelas;
	}

	public TituloParcela getTtParcela() {
		return ttParcela;
	}

	public void setTtParcela(TituloParcela ttParcela) {
		this.ttParcela = ttParcela;
	}

	// se o id do despesa não for nulo, está editando
	public boolean isEditando() {
		return this.titulo.getId() != null;
	}
}