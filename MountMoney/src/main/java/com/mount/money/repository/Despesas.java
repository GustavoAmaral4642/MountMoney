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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.mount.money.model.Despesa;
import com.mount.money.model.util.DespesaPorCategoria;
import com.mount.money.repository.filter.DespesaFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Despesas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Seguranca segUsuario = new Seguranca();

	@Inject
	private EntityManager manager;

	public Despesa guardar(Despesa despesa) {

		// registra o usuário
		despesa.setUsuario(segUsuario.getUsuario());

		return despesa = manager.merge(despesa);
	}

	// busca despesa por id
	public Despesa porId(Long id) {

		Despesa despesa = manager.find(Despesa.class, id);

		// apenas pode ser registros do proprio usuário
		if (despesa.getUsuario().equals(segUsuario.getUsuario())) {
			return despesa;
		}

		return null;
	}

	// remove registros de banco
	@Transactional
	public void remover(Despesa despesa) {

		try {
			// pesquisa despesa pelo ID
			despesa = porId(despesa.getId());
			// marcando despesa para exclusão
			manager.remove(despesa);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Despesa não pode ser excluída!");
		}
	}

	// busca os bancos por filtro usando 'criteria' - removido criteria
	@SuppressWarnings("unchecked")
	public List<Despesa> filtrados(DespesaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Despesa.class);

		if (filtro.getBanco() != null) {
			criteria.add(Restrictions.eq("banco", filtro.getBanco()));
		}

		if (filtro.getDataDespesaDe() != null) {
			criteria.add(Restrictions.ge("dataDespesa", filtro.getDataDespesaDe()));
		}

		if (filtro.getDataDespesaAte() != null) {
			criteria.add(Restrictions.le("dataDespesa", filtro.getDataDespesaAte()));
		}

		if (StringUtils.isNotBlank(filtro.getHistorico())) {
			criteria.add(Restrictions.ilike("historico", filtro.getHistorico(), MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		return criteria.addOrder(Order.asc("dataDespesa")).list();
	}

	public List<Despesa> todasDespesas() {
		try {

			return manager.createQuery("from Despesa where " + "usuario = :usuario", Despesa.class)
					.setParameter("usuario", segUsuario.getUsuario()).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DespesaPorCategoria> despesasPorCategoria(DespesaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Despesa.class);

		if (filtro.getDataDespesaDe() != null) {
			criteria.add(Restrictions.ge("dataDespesa", filtro.getDataDespesaDe()));
		}

		if (filtro.getDataDespesaAte() != null) {
			criteria.add(Restrictions.le("dataDespesa", filtro.getDataDespesaAte()));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		ProjectionList pl = Projections.projectionList().add(Projections.groupProperty("categoria").as("categoria"))
				.add(Projections.count("categoria").as("qntDespesa"))
				.add(Projections.sum("valorDespesa").as("valorDespesa"));

		criteria.setProjection(pl);
		criteria.setResultTransformer(Transformers.aliasToBean(DespesaPorCategoria.class));

		return criteria.list();
	}

}
