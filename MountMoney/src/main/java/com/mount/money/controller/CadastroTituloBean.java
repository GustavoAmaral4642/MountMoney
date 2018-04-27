package com.mount.money.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Banco;
import com.mount.money.model.Despesa;
import com.mount.money.model.MovimentoBanco;
import com.mount.money.model.Titulo;
import com.mount.money.model.TituloParcela;
import com.mount.money.repository.Bancos;
import com.mount.money.service.CadastroDespesaService;
import com.mount.money.service.CadastroMovimentoBancoService;
import com.mount.money.service.CadastroTituloService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTituloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroTituloService
	@Inject
	private CadastroTituloService cadastroTituloService;

	@Inject
	private CadastroDespesaService cadastroDespesaService;

	@Inject
	private CadastroMovimentoBancoService cadastroMovimentoService;

	// injetando classe bancos
	@Inject
	private Bancos bancos;

	private List<TituloParcela> parcelas = new ArrayList<>();
	private List<Banco> todosBancos = new ArrayList<>();
	
	private Titulo titulo;
	private TituloParcela ttParcela;
	private Despesa despesa;
	private MovimentoBanco movimento;
	private Banco banco;

	private Long numeroParcela;
	private boolean adicionou = false;
	private boolean editou = false;


	// construtor
	public CadastroTituloBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		titulo = new Titulo();
		ttParcela = new TituloParcela();
		despesa = new Despesa();
		numeroParcela = new Long(0);
		banco = new Banco();
		movimento = new MovimentoBanco ();
		parcelas = new ArrayList<>();
	}

	// iniciar coleções
	public void inicializar() {
		todosBancos = bancos.todosBancos();

		if (isEditando()) {
			parcelas = titulo.getTitulosParcelas();
		}
	}

	public void salvar() {

		try {
			titulo.setTitulosParcelas(parcelas);
			this.titulo = cadastroTituloService.salvar(this.titulo);

			// salvando despesa
			for (TituloParcela p : parcelas) {
				if (p.getDataPagamento() == null) {

				} else if (p.getValorPagamento().equals(BigDecimal.ZERO)) {

				} else {
					despesa.setDataDespesa(p.getDataPagamento());
					despesa.setHistorico(p.getHistoricoPagamento());
					despesa.setValorDespesa(p.getValorPagamento());
					despesa.setBanco(p.getBanco());
					
					movimento.setBanco(p.getBanco());
					movimento.setDataMovimento(p.getDataPagamento());
					movimento.setHistorico("Titulo Pago: " + p.getHistoricoPagamento());
					movimento.setValorMovimento(p.getValorPagamento());
					movimento.setTipoMovimento("S");
					
					cadastroDespesaService.salvar(despesa);
					cadastroMovimentoService.salvar(movimento);
				}
			}

			limpar();
			FacesUtil.addInfoMessage("Titulo registrado com sucesso!");

		} catch (RuntimeException e) {
			FacesUtil.addErrorMessage("Ocorreu um erro ao tentar gravar o título. "
					+ "Por favor, entre em contato com o Administrador do Sistema!");
		}

	}

	public void adicionarParcela() {

		if (isEditando() && !editou) {
			for (TituloParcela p : parcelas) {
				numeroParcela = p.getNumeroParcela();
			}

			adicionou = true;
			editou = true;			
		}

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

	public void pagarParcela(TituloParcela parcela) {
		ttParcela = parcela;
	}

	public void pagando() {

		for (int i = 0; i < parcelas.size(); i++) {
			if (ttParcela.getNumeroParcela() == parcelas.get(i).getNumeroParcela()) {
				parcelas.get(i).setDataPagamento(ttParcela.getDataPagamento());
				parcelas.get(i).setValorPagamento(ttParcela.getValorPagamento());
				parcelas.get(i).setBanco(ttParcela.getBanco());
				parcelas.get(i).setHistoricoPagamento(ttParcela.getHistoricoPagamento());
			}
		}
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

	public MovimentoBanco getMovimento() {
		return movimento;
	}

	public void setMovimento(MovimentoBanco movimento) {
		this.movimento = movimento;
	}

	// se o id do despesa não for nulo, está editando
	public boolean isEditando() {
		return this.titulo.getId() != null;
	}
}