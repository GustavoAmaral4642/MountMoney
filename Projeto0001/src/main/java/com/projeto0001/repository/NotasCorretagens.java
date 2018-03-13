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

import com.projeto0001.model.NotaCorretagem;
import com.projeto0001.repository.filter.NotaCorretagemFilter;
import com.projeto0001.security.Seguranca;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class NotasCorretagens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Seguranca segUsuario = new Seguranca();

	// grava registros de notas de corretagem
	public NotaCorretagem guardar(NotaCorretagem nota) {

		// registra usuário
		nota.setUsuario(segUsuario.getUsuario());

		return nota = manager.merge(nota);
	}

	// busca notas de corretagem por id
	public NotaCorretagem porId(Long id) {

		NotaCorretagem nota = manager.find(NotaCorretagem.class, id);

		// apenas pode ver registros do proprio usuario
		if (nota.getUsuario().equals(segUsuario.getUsuario())) {
			return nota;
		}

		return null;
	}

	// remove registros de nota de corretagem
	@Transactional
	public void remover(NotaCorretagem nota) {

		try {
			// pesquisa nota de corretagem pelo ID
			nota = porId(nota.getId());
			// marcando nota de corretagem para exclusão
			manager.remove(nota);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Nota de corretagem não pode ser excluída!");
		}
	}

	// buscar todos as notas de corretagens
	public List<NotaCorretagem> todasNotas() {
		try {
			return manager.createQuery("from NotaCorretagem", NotaCorretagem.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	// busca os notas por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<NotaCorretagem> filtradas(NotaCorretagemFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(NotaCorretagem.class).createAlias("contaCorretora", "c");

		// se o numero da nota não estiver vazio. Importacao da ferramenta pelo
		// pom.xml
		if (StringUtils.isNotBlank(filtro.getNumeroNota())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("numeroNota", filtro.getNumeroNota(), MatchMode.ANYWHERE));
		}

		if (filtro.getDtMovimentoDe() != null) {
			criteria.add(Restrictions.ge("dataPregao", filtro.getDtMovimentoDe()));
		}

		if (filtro.getDtMovimentoAte() != null) {
			criteria.add(Restrictions.le("dataPregao", filtro.getDtMovimentoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeContaCorretora())) {
			criteria.add(
					Restrictions.ilike("c.nomeContaCorretora", filtro.getNomeContaCorretora(), MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		return criteria.addOrder(Order.asc("numeroNota")).list();
	}

	// busca ordem dentro de nota
	public NotaCorretagem carregarOrdens(NotaCorretagem nota) {
		return manager.createQuery("from NotaCorretagem u where id = :notaCorretagem", NotaCorretagem.class)
				.setParameter("notaCorretagem", nota.getId()).getSingleResult();
	}

}
