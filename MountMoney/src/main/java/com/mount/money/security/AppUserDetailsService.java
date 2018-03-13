package com.mount.money.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mount.money.model.Grupo;
import com.mount.money.model.Usuario;
import com.mount.money.repository.Usuarios;
import com.mount.money.util.cdi.CDIServiceLocator;

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
