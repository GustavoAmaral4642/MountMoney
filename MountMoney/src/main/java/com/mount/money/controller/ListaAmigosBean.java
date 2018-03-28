package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.ListaAmigo;
import com.mount.money.model.Ocorrencia;
import com.mount.money.model.Usuario;
import com.mount.money.repository.Usuarios;
import com.mount.money.util.jsf.FacesUtil;
import com.mount.money.service.CadastroListaAmigoService;
import com.mount.money.service.CadastroOcorrenciaService;

@Named
@ViewScoped
public class ListaAmigosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroListaAmigoService cadastroListaAmigoService;

	@Inject
	private Usuarios usuarios;
	/*
	 * @Inject private CadastroOcorrenciaService cadastroOcorrenciaService;
	 * 
	 * private Ocorrencia ocorrencia;
	 */
	private List<Usuario> todosUsuarios;
	private List<Usuario> usuariosFiltrados;

	private ListaAmigo lista;

	// construtor
	public ListaAmigosBean() {
		limpar();

	}

	// metodo para limpar os dados na tela
	public void limpar() {
		todosUsuarios = new ArrayList<>();
		lista = new ListaAmigo();

	}

	// iniciar coleções
	public void inicializar() {
		todosUsuarios = usuarios.usuariosTodos();
	}

	public void salvar() {

		
		
		this.lista = cadastroListaAmigoService.salvar(this.lista);

		/*
		 * // tratar e salvar ocorrencia this.ocorrencia =
		 * cadastroOcorrenciaService.logAtivoI(ativo); this.ocorrencia =
		 * cadastroOcorrenciaService.salvar(ocorrencia);
		 */

		limpar();
		FacesUtil.addInfoMessage("Convite para amizade enviado com sucesso!");
	}

	public ListaAmigo getListaAmigo() {
		return lista;
	}

	public void setListaAmigo(ListaAmigo lista) {
		this.lista = lista;
	}

	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	// se o id da lista de amigo não for nulo, está editando
	public boolean isEditando() {
		return this.lista.getId() != null;
	}

}
