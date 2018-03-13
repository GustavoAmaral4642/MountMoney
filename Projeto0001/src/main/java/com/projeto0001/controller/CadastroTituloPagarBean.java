package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.FormaPagamento;
import com.projeto0001.model.TituloPagar;
import com.projeto0001.model.TituloPagarParcela;
import com.projeto0001.repository.Clientes;
import com.projeto0001.service.CadastroTituloPagarService;
import com.projeto0001.service.NegocioException;
import com.projeto0001.service.PagamentoTituloPagarService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTituloPagarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes fornecedores;

	@Inject
	private CadastroTituloPagarService cadastroTituloPagarService;

	@Inject
	private PagamentoTituloPagarService pagamentoTituloPagarService;
	
	@Produces
	@TituloPagarEdicao
	private TituloPagar titulo;

	private TituloPagarParcela tituloParcela;
	private List<TituloPagarParcela> titulosParcelas;

	private List<ClienteFornecedor> listaFornecedores;

	// construtor
	public CadastroTituloPagarBean() {
		limpar();
	}

	// iniciar coleções, chamado pelo preRenderView
	public void inicializar() {
		// carregar os vendedores/usuarios

	}

	// metodo para limpar a tela
	public void limpar() {
		titulo = new TituloPagar();
		tituloParcela = new TituloPagarParcela();
		titulosParcelas = new ArrayList<>();
	}

	// salvar titulo
	public void salvar() {
		this.titulo = this.cadastroTituloPagarService.salvar(this.titulo);
		FacesUtil.addInfoMessage("Titulo registrado com sucesso");
	}

	// metodo para observar tituloPagarAlteradoEvent.
	// para emissao do titulo e atualizar a tela
	public void tituloPagarAlterado(@Observes TituloPagarAlteradoEvent event) {
		this.titulo = event.getTitulo();
	}

	// recalcular titulo após informar juros e desconto
	public void recalcularTitulo() {

		// para corrigir erro de ediçao de titulos.
		// para dar o erro, apague o teste, chama a edição de itens e inclua
		// valor de desconto e juros
		if (this.titulo != null) {
			this.titulo.recalcularValorTotal();
		}
	}

	// adicionar parcela na lista
	public void adicionarParcela() {

		// se não informar data de vencimento, não faz nada
		if (tituloParcela.getDataVencimento() == null) {
			throw new NegocioException("Por favor, informe a data de vencimento");
		}
		
		tituloParcela.setTitulo(titulo); // insere titulo em parcela

		// insere o numero da parcela usando o tamanho da lista
		tituloParcela.setNumeroParcela(titulosParcelas.size() + 1);

		titulosParcelas.add(tituloParcela); // insere o parcelas na listagem
		titulo.setParcelas(titulosParcelas); // insere a listagem no titulo

		// para alterar o campo "quantidade de parcelas"
		titulo.setQuantidadeParcelas(titulo.getParcelas().size());

		recalcularTitulo();

		limparParcela();
	}

	// limpa tela de parcelas
	public void limparParcela() {
		tituloParcela = new TituloPagarParcela();
	}
	
	
	// carregar historico na dialog
	public void carregarHistorico(){
		tituloParcela.setHistorico(titulo.getHistorico());		
	}
	
	// faz o pagamento do titulo
	public void pagarParcela(){
		
		titulo = pagamentoTituloPagarService.salvar(titulo, tituloParcela);		
	}
	
	// retornar a forma de pagamento em um array
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	public TituloPagar getTitulo() {
		return titulo;
	}

	public void setTitulo(TituloPagar titulo) {
		this.titulo = titulo;
	}

	public TituloPagarParcela getTituloParcela() {
		return tituloParcela;
	}

	public void setTituloParcela(TituloPagarParcela tituloParcela) {
		this.tituloParcela = tituloParcela;
	}

	public List<ClienteFornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public List<TituloPagarParcela> getTitulosParcelas() {
		return titulosParcelas;
	}

	public void setTitulosParcelas(List<TituloPagarParcela> titulosParcelas) {
		this.titulosParcelas = titulosParcelas;
	}

	// se o numero do titulo não for nulo, está editando
	public boolean isEditando() {
		return this.titulo.getId() != null;
	}

}
