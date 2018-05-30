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

import com.mount.money.model.Categoria;
import com.mount.money.repository.filter.CategoriaFilter;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de Categorias
	public Categoria guardar(Categoria categoria) {
		return categoria = manager.merge(categoria);
	}

	// busca Categoria por id
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	// remove registros de Categoria
	@Transactional
	public void remover(Categoria categoria) {

		try {
			// pesquisa Categoria pelo ID
			categoria = porId(categoria.getId());

			// marcando Categoria para exclusão
			manager.remove(categoria);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Categoria não pode ser excluída!");
		}
	}

	public Categoria porSigla(String categoria) {
		try {
			return manager.createQuery("from Categoria where upper(siglaCategoria) = :siglaCategoria", Categoria.class)
					.setParameter("siglaCategoria", categoria.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// buscar todas os Categoria
	public List<Categoria> todosCategorias() {
		try {
			return manager.createQuery("from Categoria c order by c.siglaCategoria", Categoria.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	// busca os Categorias por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(CategoriaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);

		if (StringUtils.isNotBlank(filtro.getSiglaCategoria())) {
			criteria.add(Restrictions.ilike("siglaCategoria", filtro.getSiglaCategoria(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNomeCategoria())) {
			criteria.add(Restrictions.ilike("nomeCategoria", filtro.getNomeCategoria(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("siglaCategoria")).list();
	}
}
