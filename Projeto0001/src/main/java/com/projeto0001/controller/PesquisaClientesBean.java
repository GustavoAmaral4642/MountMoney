package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.Endereco;
import com.projeto0001.model.TipoPessoa;
import com.projeto0001.repository.Clientes;
import com.projeto0001.repository.filter.ClienteFilter;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injetando classe clientes
	@Inject
	private Clientes clientes;

	private ClienteFilter filtro; // variavel para usar campos no filtro
	private List<ClienteFornecedor> clientesFiltrados; // receber clientes para
														// listagem

	private ClienteFornecedor clienteFornecedor; // recebe cliente selecionado
	private Endereco enderecoSelecionado; // recebe endereco selecionado

	public PesquisaClientesBean() {
		limpar();
	}

	// método para limpar os dados na tela
	public void limpar() {
		filtro = new ClienteFilter();
		filtro.setClienteFornecedor("c");
		clientesFiltrados = new ArrayList<>();
		clienteFornecedor = new ClienteFornecedor();
		clienteFornecedor.setTipo(TipoPessoa.FISICA);
	}

	// metodo para excluir clientes
	public void excluir(ClienteFornecedor clienteSelecionado) {
		// exclui cliente do banco de dados
		clientes.remover(clienteSelecionado);
		// exclui cliente da lista
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente `" + clienteSelecionado.getNome() + "` excluído com sucesso!");
	}

	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}

	public List<ClienteFornecedor> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public ClienteFornecedor getClienteFornecedor() {
		return clienteFornecedor;
	}

	public void setClienteFornecedor(ClienteFornecedor clienteFornecedor) {
		this.clienteFornecedor = clienteFornecedor;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
}
