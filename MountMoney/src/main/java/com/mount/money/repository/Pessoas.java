package com.mount.money.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mount.money.model.Pessoa;
import com.mount.money.repository.filter.PessoaFilter;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de pessoas
	public Pessoa guardar(Pessoa pessoa) {
		return pessoa = manager.merge(pessoa);
	}

	// busca Pessoa por id
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	// remove registros de Pessoa
	@Transactional
	public void remover(Pessoa pessoa) {

		try {
			// pesquisa Pessoa pelo ID
			pessoa = porId(pessoa.getId());

			// marcando Pessoa para exclusão
			manager.remove(pessoa);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Esta pessoa não pode ser excluída!");
		}
	}

	// busca as Pessoas por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<Pessoa> filtradas(PessoaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);

		// se o Pessoa não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getApelido())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("apelido", filtro.getApelido(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("apelido")).list();
	}
}
