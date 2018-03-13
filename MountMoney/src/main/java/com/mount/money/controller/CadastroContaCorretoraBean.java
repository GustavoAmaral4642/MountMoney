package com.mount.money.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ContaCorretora;
import com.mount.money.service.CadastroContaCorretoraService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroContaCorretoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta a classe CadastroBancoService
	@Inject
	private CadastroContaCorretoraService cadastroContaCorretoraService;
	
	private ContaCorretora contaCorretora;
	
	// construtor
	public CadastroContaCorretoraBean() {
		limpar();
	}

	// metodo para limpar os dados na tela
	public void limpar() {
		contaCorretora = new ContaCorretora();		
		contaCorretora.setTipoContaCorretora(contaCorretora.getTipoContaCorretora().REAL);
	}

	// iniciar coleções
	public void inicializar() {
				
	}

	public void salvar() {
		
		this.contaCorretora = cadastroContaCorretoraService.salvar(this.contaCorretora);
		limpar();

		FacesUtil.addInfoMessage("Conta registrada com sucesso!");
	}

	public ContaCorretora getContaCorretora() {
		return contaCorretora;
	}

	public void setContaCorretora(ContaCorretora contaCorretora) {
		this.contaCorretora = contaCorretora;
	}


	// se o id do contaCorretora não for nulo, está editando
	public boolean isEditando() {
		return this.contaCorretora.getId() != null;
	}

}
