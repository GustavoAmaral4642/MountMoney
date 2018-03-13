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

import com.mount.money.model.Banco;
import com.mount.money.model.ContaCorretora;
import com.mount.money.repository.filter.BancoFilter;
import com.mount.money.repository.filter.ContaCorretoraFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class ContasCorretoras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Seguranca segUsuario = new Seguranca();

	// grava registros de contas de corretoras
	public ContaCorretora guardar(ContaCorretora conta) {

		// registra o usuario
		conta.setUsuario(segUsuario.getUsuario());

		return conta = manager.merge(conta);
	}

	// busca conta por id
	public ContaCorretora porId(Long id) {

		ContaCorretora conta = manager.find(ContaCorretora.class, id);

		// apenas pode ver registros do proprio usuario
		if (conta.getUsuario().equals(segUsuario.getUsuario())) {
			return conta;
		}
		return null;
	}

	// remove registros de conta de corretora
	@Transactional
	public void remover(ContaCorretora conta) {

		try {
			// pesquisa conta de corretora pelo ID
			conta = porId(conta.getId());
			// marcando conta para exclusão
			manager.remove(conta);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Conta de corretora não pode ser excluída!");
		}
	}

	public List<ContaCorretora> todasContas() {
		try {
			return manager.createQuery("from ContaCorretora cc "
					+ "where cc.usuario = :us "
					+ "order by cc.nomeContaCorretora", ContaCorretora.class)
					.setParameter("us", segUsuario.getUsuario())
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	// busca as contas corretoras por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<ContaCorretora> filtrados(ContaCorretoraFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContaCorretora.class);

		// se o nomeContaCorretora não estiver vazio. Importacao da ferramenta
		// pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getNomeContaCorretora())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nomeContaCorretora", filtro.getNomeContaCorretora(), MatchMode.ANYWHERE));
		}

		// se o numeroContaCorretora não estiver vazio. Importacao da ferramenta
		// pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getNumeroContaCorretora())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(
					Restrictions.ilike("numeroContaCorretora", filtro.getNumeroContaCorretora(), MatchMode.ANYWHERE));
		}

		// se o tipoContaCorretora não estiver vazio. Importacao da ferramenta
		// pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getTipoContaCorretora())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("tipoContaCorretora", filtro.getTipoContaCorretora(), MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if(StringUtils.isNotBlank(segUsuario.getUsuario().getNome())){

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}
		
		return criteria.addOrder(Order.asc("nomeContaCorretora")).list();
	}

}
