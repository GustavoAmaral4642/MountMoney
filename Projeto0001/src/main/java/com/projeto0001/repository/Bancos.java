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

import com.projeto0001.model.Banco;
import com.projeto0001.model.TipoBanco;
import com.projeto0001.repository.filter.BancoFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class Bancos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de bancos
	public Banco guardar(Banco banco) {
		return banco = manager.merge(banco);
	}

	// busca banco por id
	public Banco porId(Long id) {
		return manager.find(Banco.class, id);
	}

	// busca tipoBanco por id
	public TipoBanco tiposBancoPorId(Long id) {
		return manager.find(TipoBanco.class, id);
	}

	// busca se existe um banco com o mesmo numero de conta
	public Banco porNumeroConta(String nomeBanco, String numeroAgencia, String numeroConta, TipoBanco tipoBanco) {
		try {
			return manager
					.createQuery(
							"from Banco where upper(numeroConta) = :numeroConta" + " and "
									+ "upper(numeroAgencia) = :numeroAgencia" + " and "
									+ "upper(tipoBanco) = :tipoBanco" + " and " + "upper(nomeBanco) = :nomeBanco",
							Banco.class)
					.setParameter("numeroConta", numeroConta.toUpperCase()).setParameter("numeroAgencia", numeroAgencia)
					.setParameter("tipoBanco", tipoBanco).setParameter("nomeBanco", nomeBanco).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// busca se existe um banco com o mesmo nome e do tipo CAIXA
	public Banco porNome(String nomeBanco, TipoBanco tipoBanco) {
		try {
			return manager
					.createQuery("from Banco where upper(nomeBanco) = :nomeBanco" + " AND "
							+ "upper(tipoBanco) = :tipoBanco", Banco.class)
					.setParameter("nomeBanco", nomeBanco.toUpperCase()).setParameter("tipoBanco", tipoBanco)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// remove registros de banco
	@Transactional
	public void remover(Banco banco) {

		try {
			// pesquisa banco pelo ID
			banco = porId(banco.getId());
			// marcando banco para exclusão
			manager.remove(banco);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Banco não pode ser excluído!");
		}
	}

	// buscar todos os bancos
	public List<Banco> todosBancos() {
		try {
			return manager.createQuery("from Banco", Banco.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
/*
	// buscar bancos por movimento
	public List<Banco> bancosPorMovimento(MovimentoBancario movimento) {
		try {
			return manager.createQuery("from Banco where id = :id", Banco.class)
					.setParameter("id", movimento.getBanco()).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
*/
	// busca os bancos por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<Banco> filtrados(BancoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Banco.class);

		// se o nomeBanco não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getNomeBanco())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nomeBanco", filtro.getNomeBanco(), MatchMode.ANYWHERE));
		}

		// se o numeroConta não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getNumeroConta())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("numeroConta", filtro.getNumeroConta(), MatchMode.ANYWHERE));
		}

		// se o descricao não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getTipoBanco())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("tipoBanco", filtro.getTipoBanco(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nomeBanco")).list();
	}

}
