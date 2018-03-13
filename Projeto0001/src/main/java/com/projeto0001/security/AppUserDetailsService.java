package com.projeto0001.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.projeto0001.model.Grupo;
import com.projeto0001.model.Usuario;
import com.projeto0001.repository.Usuarios;
import com.projeto0001.util.cdi.CDIServiceLocator;

// fornecer detalhes de usuario
public class AppUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// não consegue injetar
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		
		if(usuario != null){
			user = new UsuarioSistema(usuario, getTarefas(usuario));
			
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getTarefas(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities  = new ArrayList<>();
		
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		return authorities;
	}

}
