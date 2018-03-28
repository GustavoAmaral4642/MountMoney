package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Grupo;
import com.mount.money.model.Pessoa;
import com.mount.money.model.Usuario;
import com.mount.money.repository.Grupos;
import com.mount.money.repository.Usuarios;
import com.mount.money.service.CadastroUsuarioService;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// injeta classe CadastroUsuarioService
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	// injeta classe usuarios
	@Inject
	private Usuarios usuarios;

	// injeta classe grupos
	@Inject
	private Grupos grupos;

	private Usuario usuario; // obtendo objeto do usuario

	private Grupo grupo; // obtendo objeto grupo
	private Grupo grupoSelecionado; // obtendo objeto do grupo(exclusão)

	private List<Grupo> gruposTodos; // objeto contendo a lista de grupos
	private List<Grupo> incluidos; // objeto que recebe os grupos escolhidos

	// construtor
	public CadastroUsuarioBean() {
		limpar();
	}

	// iniciar coleções
	public void inicializar() {
		// se não for atualização de página, carrega listagem
		if (FacesUtil.isNotPostback()) {
			gruposTodos = grupos.todosGrupos();

			// para edição de registros
			if (usuario.getNome() != null) {
				carregarGruposUsuarios();
			}
		}
	}

	// metodo é chamado para carregar listagem dos grupos na edição de registros
	public void carregarGruposUsuarios() {
		usuario = usuarios.carregarGrupos(usuario);
		incluidos = usuario.getGrupos();
	}

	// método para limpar a tela
	public void limpar() {
		usuario = new Usuario();
		grupo = null;
		grupoSelecionado = null;
		incluidos = new ArrayList<>();
		usuario.setPessoa(new Pessoa());
	}

	public void salvar() {

		// adiciona os grupos ao usuario antes de gravar
		if (incluidos.size() > 0) {
			this.usuario.getGrupos().addAll(incluidos);
		}

		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		limpar();
		FacesUtil.addInfoMessage("Usuário gravado com sucesso!");
	}

	// metodo para adicionar um grupo dentro da table do cadastro de usuario
	public void adicionaGrupo() {

		boolean valida = true;

		// percorre o array de incluidos, para verificar se um determinado
		// grupo já foi selecionado
		if (incluidos.size() > 0 && incluidos.contains(grupo)) {
			valida = false;
		}

		// se "não foi incluído", se grupo não for vazio ou descricao não
		// estiver preenchido com espaços.
		if (grupo == null || grupo.getDescricao().trim().equals("")) {
			FacesUtil.addErrorMessage("Por favor, selecine um grupo!");
		} else if (valida) {
			incluidos.add(grupo);
		} else {
			FacesUtil.addErrorMessage(grupo.getDescricao() + " já foi adicionado");
		}
	}

	// metodo para excluir um grupo dentro da table do cadastro de usuario
	public void removeGrupo() {

		boolean valida = true;

		// percorre o array de incluidos, para excluir
		if (incluidos.size() > 0 && incluidos.contains(grupoSelecionado)) {
			valida = false;
		}

		// se valida for falso, remove grupo
		if (!valida) {
			incluidos.remove(grupoSelecionado);
			FacesUtil.addInfoMessage(grupoSelecionado.getDescricao() + " removido da lista!");
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
	public Grupo getGrupo() {
		return grupo;
	}

	// grava algum valor que vem da xhtml em grupo
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	// metodo para retornar os grupos para o xhtml
	public List<Grupo> getGruposTodos() {
		return gruposTodos;
	}

	// metodo que retorna os grupos incluidos
	public List<Grupo> getIncluidos() {
		return incluidos;
	}

	// metodo para gravar na incluidos
	public void setIncluidos(List<Grupo> incluidos) {
		this.incluidos = incluidos;
	}

	// retorna o grupo selecionado para exclusão
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	// grava o grupo selecionado para exclusão
	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	// se o id do banco não for nulo, está editando
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
}
