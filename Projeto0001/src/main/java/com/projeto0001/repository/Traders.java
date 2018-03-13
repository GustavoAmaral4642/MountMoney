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

import com.projeto0001.model.Trader;
import com.projeto0001.repository.filter.TraderFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class Traders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de trader
	public Trader guardar(Trader trader) {
		return trader = manager.merge(trader);
	}

	// busca trader por id
	public Trader porId(Long id) {
		return manager.find(Trader.class, id);
	}

	// remove registros de trader
	@Transactional
	public void remover(Trader trader) {

		try {
			// pesquisa trader pelo ID
			trader = porId(trader.getId());
			
			// marcando trader para exclusão
			manager.remove(trader);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Trader não pode ser excluído!");
		}
	}

	// buscar todos os traders
	public List<Trader> todosTraders() {
		try {
			return manager.createQuery("from Trader", Trader.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	// busca os traders por filtro usando criteria
		@SuppressWarnings("unchecked")
		public List<Trader> filtrados(TraderFilter filtro) {

			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Trader.class);

			// se o nomeTrader não estiver vazio. Importacao da ferramenta pelo
			// pom.xml
			if (StringUtils.isNotBlank(filtro.getNomeTrader())) {
				// adicionando restricao de igualdade 'ilike' é case insensitive
				// MATCHMODE é para colocar o % utilizado no like.
				criteria.add(Restrictions.ilike("nomeTrader", filtro.getNomeTrader(), MatchMode.ANYWHERE));
			}

			return criteria.addOrder(Order.asc("nomeTrader")).list();
		}
}
