package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Usuario;
import com.mount.money.repository.Usuarios;
import com.mount.money.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	// metodo para chamar o outro para salvar
	@Transactional
	public Usuario salvar(Usuario usuario){ 
		
		Usuario usuarioExistente = usuarios.porNome(usuario.getNome());
		
		//Testa a inclusão e edição de registros
		if(usuarioExistente != null && !usuarioExistente.equals(usuario))
		{
			throw new NegocioException("Já existe um usuário com o mesmo nome.");
		}
		
		return usuarios.guardar(usuario);
	}
	
}
