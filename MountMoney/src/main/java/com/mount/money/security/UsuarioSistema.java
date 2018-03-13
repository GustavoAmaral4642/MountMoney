package com.mount.money.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mount.money.model.Usuario;

// para tratar o usuário do Sistema
public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.usuario = usuario;
		// TODO Auto-generated constructor stub
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
