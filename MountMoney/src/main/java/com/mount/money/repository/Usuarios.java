package com.mount.money.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mount.money.model.Usuario;
import com.mount.money.repository.filter.UsuarioFilter;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// gravar registros de usuarios
	public Usuario guardar(Usuario usuario) {
		return usuario = manager.merge(usuario);
	}

	// busca usuario por nome/vendedores
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo especificado)
		return this.manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	// remove registros de usuario
	@Transactional
	public void remover(Usuario usuario) {
		
		try {
			// pesquisa usuario pelo ID
			usuario = porId(usuario.getId());

			// marcando usuario para exclusão
			manager.remove(usuario);

			// tudo que estiver pendente para execução no BD, executa após o
			// flush
			// se o produto estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído!");
		}
	}

	// busca se já existe o mesmo nome em maisculo
	public Usuario porNome(String nome) {

		try {
			return manager.createQuery("from Usuario where upper(nome) = :nome", Usuario.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		}
		// se a pesquisa não encontrar nada
		catch (NoResultException e) {
			return null;
		}
	}

	// busca os grupos por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		// se o nome não estiver vazio. Importacao da ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getNome())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	// busca usuario pelo id
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	// busca usuario dentro de Usuario
	public Usuario carregarGrupos(Usuario usuario) {
		return manager.createQuery("from Usuario u where id = :usuario", Usuario.class)
				.setParameter("usuario", usuario.getId()).getSingleResult();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;

		try {
			// retornar um usuario com base no email
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuario encontrado no email
		}
		return usuario;
	}

	// buscar todos os usuarios
	public List<Usuario> usuariosTodos() {
		try {
			return manager.createQuery("from Usuario", Usuario.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
