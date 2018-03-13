package com.projeto0001.repository;

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

import com.projeto0001.model.Ativo;
import com.projeto0001.repository.filter.AtivoFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class Ativos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de ativos
	public Ativo guardar(Ativo ativo) {
		return ativo = manager.merge(ativo);
	}

	// busca Ativo por id
	public Ativo porId(Long id) {
		return manager.find(Ativo.class, id);
	}

	// remove registros de Ativo
	@Transactional
	public void remover(Ativo ativo) {

		try {
			// pesquisa Ativo pelo ID
			ativo = porId(ativo.getId());
			
			// marcando Ativo para exclusão
			manager.remove(ativo);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Ativo não pode ser excluído!");
		}
	}

	public Ativo porSigla(String ativo){
		try {
			return manager.createQuery("from Ativo where upper(siglaAtivo) = :siglaAtivo", Ativo.class)
					.setParameter("siglaAtivo", ativo.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	// buscar todos os Moeda
	public List<Ativo> todosAtivos() {
		try {
			return manager.createQuery("from Ativo a order by a.siglaAtivo", Ativo.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	// busca os Ativos por filtro usando criteria
		@SuppressWarnings("unchecked")
		public List<Ativo> filtrados(AtivoFilter filtro) {

			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Ativo.class);

			// se o Ativo não estiver vazio. Importacao da ferramenta pelo
			// pom.xml
			if (StringUtils.isNotBlank(filtro.getSiglaAtivo())) {
				// adicionando restricao de igualdade 'ilike' é case insensitive
				// MATCHMODE é para colocar o % utilizado no like.
				criteria.add(Restrictions.ilike("siglaAtivo", filtro.getSiglaAtivo(), MatchMode.ANYWHERE));
			}

			if (StringUtils.isNotBlank(filtro.getNomeAtivo())) {
				// adicionando restricao de igualdade 'ilike' é case insensitive
				// MATCHMODE é para colocar o % utilizado no like.
				criteria.add(Restrictions.ilike("nomeAtivo", filtro.getNomeAtivo(), MatchMode.ANYWHERE));
			}
			
			return criteria.addOrder(Order.asc("siglaAtivo")).list();
		}
}
