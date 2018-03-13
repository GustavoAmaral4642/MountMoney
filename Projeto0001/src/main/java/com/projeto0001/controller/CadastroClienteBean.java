package com.projeto0001.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.Contato;
import com.projeto0001.model.Endereco;
import com.projeto0001.repository.Clientes;
import com.projeto0001.service.CadastroClienteFornecedorService;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroClienteService
	@Inject
	private CadastroClienteFornecedorService cadastroClienteService;

	@Inject
	private Clientes clientes;

	private ClienteFornecedor cliente;

	private Endereco endereco;

	private Endereco enderecoSelecionado;

	private Contato contato;

	private Contato contatoSelecionado;
	
	// para carregar os enderecos gravados do cliente
	private List<Endereco> enderecosGravados;

	// construtor
	public CadastroClienteBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		cliente = new ClienteFornecedor();
		cliente.setTipo(cliente.getTipo().FISICA);
		cliente.setCliente_fornecedor("c");
		endereco = new Endereco();
		enderecoSelecionado = new Endereco();
		contato = new Contato();
		contato.setContato("celular");
		contatoSelecionado = new Contato();
	}

	// iniciar coleções
	public void inicializar() {

	}

	public void salvar() {

		this.cliente = cadastroClienteService.salvar(this.cliente);
		limpar();

		FacesUtil.addInfoMessage("Cliente gravado com sucesso!");
	}

	// adicionar endereco na lista de cadastro
	public void adicionaEndereco() {

		cadastroClienteService.adicionaEndereco(endereco);
		endereco.setClienteFornecedor(cliente);

		// adiciona o endereço alterado
		cliente.getEnderecos().add(endereco);
		// limpa tela
		endereco = new Endereco();
	}

	// adicionar contato na lista de cadastro
	public void adicionaContato() {

		cadastroClienteService.adicionaContato(contato);
		contato.setClienteFornecedor(cliente);

		// adiciona o contato alterado
		cliente.getContatos().add(contato);
		// limpa tela
		contato = new Contato();
		contato.setContato("celular");
	}

	// removendo contato
	public void excluirContato(Contato contatoSelecionado) {
		cliente.getContatos().remove(contatoSelecionado);
	}

	// removendo endereco
	public void excluirEndereco(Endereco enderecoSelecionado) {
		cliente.getEnderecos().remove(enderecoSelecionado);
	}

	// para atualizar um endereço quando o botão editar for selecionado
	public void carregarEndereco() {
		endereco = enderecoSelecionado;
	}

	public ClienteFornecedor getCliente() {
		return cliente;
	}

	public void setCliente(ClienteFornecedor cliente) {
		this.cliente = cliente;
	}

	// retornar o endereco
	public Endereco getEndereco() {
		return endereco;
	}

	// salvar o endereco
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	// retorna os enderecos do cliente gravados no banco
	public List<Endereco> getEnderecosGravados() {
		return enderecosGravados;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}
	
	// se o id do banco não for nulo, está editando
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

}
