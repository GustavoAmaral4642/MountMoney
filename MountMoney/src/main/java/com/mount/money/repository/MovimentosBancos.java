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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mount.money.model.MovimentoBanco;
import com.mount.money.repository.filter.MovimentoBancoFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class MovimentosBancos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Seguranca segUsuario = new Seguranca();

	@Inject
	private EntityManager manager;

	// grava registros de movimentos
	public MovimentoBanco guardar(MovimentoBanco movimento) {

		// registra o usuário
		movimento.setUsuario(segUsuario.getUsuario());

		return movimento = manager.merge(movimento);
	}

	// busca movimento por id
	public MovimentoBanco porId(Long id) {

		MovimentoBanco movimento = manager.find(MovimentoBanco.class, id);

		// apenas pode ser registros do proprio usuário
		if (movimento.getUsuario().equals(segUsuario.getUsuario())) {
			return movimento;
		}

		return null;
	}

	// remove registros de banco
	@Transactional
	public void remover(MovimentoBanco movimento) {

		try {
			// pesquisa movimento pelo ID
			movimento = porId(movimento.getId());
			// marcando movimento para exclusão
			manager.remove(movimento);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Movimento bancário não pode ser excluído!");
		}
	}

	// busca os bancos por filtro usando 'criteria' - removido criteria
	public List<MovimentoBanco> filtrados(MovimentoBancoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(MovimentoBanco.class);

		// se o numeroConta não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		
		if (filtro.getBanco() != null ) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.eq("banco", filtro.getBanco()));
		}
		
		if (filtro.getDataMovimentoDe() != null) {
			criteria.add(Restrictions.ge("dataMovimento", filtro.getDataMovimentoDe()));
		}
		
		if (filtro.getDataMovimentoAte() != null) {
			criteria.add(Restrictions.le("dataMovimento", filtro.getDataMovimentoAte()));
		}
		
		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}
		
		return criteria.addOrder(Order.asc("dataMovimento")).list();
	}

	// trazer todos os bancos que tem movimento
	public List<MovimentoBanco> todosMovimentos() {
		try {
			return manager.createQuery("from MovimentoBanco where " + "usuario = :usuario", MovimentoBanco.class)
					.setParameter("usuario", segUsuario.getUsuario()).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
