package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.Usuario;
import com.mount.money.repository.Usuarios;
import com.mount.money.repository.filter.UsuarioFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
		
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;

	public PesquisaUsuariosBean(){
		filtro = new UsuarioFilter();
	}
	
	//método para excluir usuarios
	public void excluir(Usuario usuarioSelecionado){
		//removendo do banco de dados
		usuarios.remover(usuarioSelecionado);
		
		//removendo do list
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " excluído com sucesso!");
	}
	
	public void pesquisar(){
		usuariosFiltrados = usuarios.filtrados(filtro);
	
	}
	
	public void limpar(){
		filtro = new UsuarioFilter();
		usuariosFiltrados = new ArrayList<>();
	}
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
	
}
