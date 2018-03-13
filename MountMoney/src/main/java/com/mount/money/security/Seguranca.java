package com.mount.money.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.mount.money.model.Usuario;

@Named
@RequestScoped
public class Seguranca {

	public Usuario getUsuario(){
		
		Usuario usu = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogad();
		
		if(usuarioLogado != null){
			usu = usuarioLogado.getUsuario();
		}
		
		return usu;
	}
	
	public String getNomeUsuario(){
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogad();
		
		if(usuarioLogado != null){
			nome = usuarioLogado.getUsuario().getNome();				
		}
		
		return nome;
	}

	private UsuarioSistema getUsuarioLogad() {
		UsuarioSistema usuario = null;
		
		// lembra que tem uma conversão, para buscar o usuário logado
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if(auth != null && auth.getPrincipal() != null){
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
}
