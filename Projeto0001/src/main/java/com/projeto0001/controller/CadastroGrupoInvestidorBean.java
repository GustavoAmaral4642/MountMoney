package com.projeto0001.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projeto0001.model.GrupoInvestidor;
import com.projeto0001.model.Usuario;
import com.projeto0001.repository.GruposInvestidores;
import com.projeto0001.repository.Usuarios;
import com.projeto0001.service.CadastroGrupoInvestidorService;
import com.projeto0001.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroGrupoInvestidorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta classe usuarios
	@Inject
	private Usuarios usuarios;

	@Inject
	private GruposInvestidores grupos;
	
	@Inject
	private CadastroGrupoInvestidorService cadastroGrupoInvestidorService;
	
	private Usuario usuario; // obtendo objeto do usuario

	private GrupoInvestidor grupo; // obtendo objeto grupo de investidores

	private List<Usuario> usuariosTodos; // objeto contendo a lista de grupos de investidores
	private List<Usuario> incluidos; // objeto que recebe os grupos investidores escolhidos

	// construtor
	public CadastroGrupoInvestidorBean() {
		limpar();
	}

	// iniciar coleções
	public void inicializar() {
		// se não for atualização de página, carrega listagem
		if (FacesUtil.isNotPostback()) {
			usuariosTodos = usuarios.usuariosTodos();

			// para edição de registros
			if (grupo.getNomeGrupo() != null) {
				carregarUsuariosInvestidores();
			}
		}
	}

	// metodo é chamado para carregar listagem dos usuários na edição de registros
	public void carregarUsuariosInvestidores() {
		grupo = grupos.carregarUsuariosInvestidores(grupo);
		incluidos = grupo.getUsuarios();
	}

	// método para limpar a tela
	public void limpar() {
		usuario = new Usuario();
		grupo = new GrupoInvestidor();
		incluidos = new ArrayList<>();
	}

	public void salvar() {

		// adiciona os grupos ao usuario antes de gravar
		if (incluidos.size() > 0) {
			this.grupo.getUsuarios().addAll(incluidos);
		}

		this.grupo = cadastroGrupoInvestidorService.salvar(this.grupo);
		limpar();
		FacesUtil.addInfoMessage("Grupo de investidores gravado com sucesso!");
		
	}

	// metodo para adicionar um usuario dentro da table do cadastro de grupo de investidores
	public void adicionaUsuario() {

		boolean valida = true;

		// percorre o array de incluidos, para verificar se um determinado
		// grupo já foi selecionado
		if (incluidos.size() > 0 && incluidos.contains(usuario)) {
			valida = false;
		}

		// se "não foi incluído", se usuario não for vazio ou descricao não
		// estiver preenchido com espaços.
		if (usuario == null || usuario.getNome().trim().equals("")) {
			FacesUtil.addErrorMessage("Por favor, selecine um usuário!");
		} else if (valida) {
			incluidos.add(usuario);
		} else {
			FacesUtil.addErrorMessage(usuario.getNome() + " já foi adicionado");
		}
	}

	// metodo para excluir um usuario dentro da table do cadastro de grupo de investidor
	public void removeUsuario(Usuario usuarioSelecionado) {
		
		boolean valida = true;

		// percorre o array de incluidos, para excluir
		if (incluidos.size() > 0 && incluidos.contains(usuarioSelecionado)) {
			valida = false;
		}

		// se valida for falso, remove grupo
		if (!valida) {
			incluidos.remove(usuarioSelecionado);
			FacesUtil.addInfoMessage(usuarioSelecionado.getNome() + " removido da lista!");
		}
	}

	// retorna o usuario
	public Usuario getUsuario() {
		return usuario;
	}

	// adiciona usuario
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// retorna o grupo para o xhtml
	public GrupoInvestidor getGrupo() {
		return grupo;
	}

	// grava algum valor que vem da xhtml em grupo
	public void setGrupo(GrupoInvestidor grupo) {
		this.grupo = grupo;
	}

	// metodo para retornar os usuario para o xhtml
	public List<Usuario> getUsuariosTodos() {
		return usuariosTodos;
	}

	// metodo que retorna os usuarios incluidos
	public List<Usuario> getIncluidos() {
		return incluidos;
	}

	// metodo para gravar na incluidos
	public void setIncluidos(List<Usuario> incluidos) {
		this.incluidos = incluidos;
	}

	// se o id do banco não for nulo, está editando
	public boolean isEditando() {
		return this.grupo.getId() != null;
	}
}
