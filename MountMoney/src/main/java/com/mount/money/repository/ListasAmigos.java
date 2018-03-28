package com.mount.money.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.mount.money.model.ListaAmigo;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class ListasAmigos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// gravar registros de listas de amigos
	public ListaAmigo guardar(ListaAmigo lista) {
		return lista = manager.merge(lista);
	}

	// remove registros de listas de amigos
	@Transactional
	public void remover(ListaAmigo lista) {
		
		try {
			// pesquisa listas pelo ID
			lista = porId(lista.getId());

			// marcando listas para exclusão
			manager.remove(lista);

			// tudo que estiver pendente para execução no BD, executa após o
			// flush
			// se a lista estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Lista de Amigos não pode ser excluída!");
		}
	}

	// busca lista pelo id
	public ListaAmigo porId(Long id) {
		return manager.find(ListaAmigo.class, id);
	}

}
