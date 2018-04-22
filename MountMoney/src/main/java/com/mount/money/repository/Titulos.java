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

import com.mount.money.model.Titulo;
import com.mount.money.repository.filter.TituloFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Titulos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Seguranca segUsuario = new Seguranca();

	@Inject
	private EntityManager manager;

	public Titulo guardar(Titulo titulo) {

		// registra o usuário
		titulo.setUsuario(segUsuario.getUsuario());

		return titulo = manager.merge(titulo);
	}

	public Titulo porId(Long id) {

		Titulo titulo = manager.find(Titulo.class, id);

		// apenas pode ser registros do proprio usuário
		if (titulo.getUsuario().equals(segUsuario.getUsuario())) {
			return titulo;
		}

		return null;
	}

	@Transactional
	public void remover(Titulo titulo) {

		try {
			// pesquisa titulo pelo ID
			titulo = porId(titulo.getId());
			// marcando titulo para exclusão
			manager.remove(titulo);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Titulo não pode ser excluído!");
		}
	}

	public List<Titulo> filtrados(TituloFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Titulo.class);

		if (filtro.getDataEmissaoDe() != null) {
			criteria.add(Restrictions.ge("dataEmissao", filtro.getDataEmissaoDe()));
		}

		if (filtro.getDataEmissaoAte() != null) {
			criteria.add(Restrictions.le("dataEmissao", filtro.getDataEmissaoAte()));
		}

		if (filtro.getDataPagamentoDe() != null) {
			criteria.add(Restrictions.ge("dataPagamento", filtro.getDataPagamentoDe()));
		}

		if (filtro.getDataPagamentoAte() != null) {
			criteria.add(Restrictions.le("dataPagamento", filtro.getDataPagamentoAte()));
		}
		
		if (StringUtils.isNotBlank(filtro.getHistorico())) {
			criteria.add(Restrictions.ilike("historico", filtro.getHistorico(), MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		return criteria.addOrder(Order.asc("dataEmissao")).list();
	}

	// trazer todos os bancos que tem movimento
	public List<Titulo> todosTitulos() {
		try {
			return manager.createQuery("from Titulo where usuario = :usuario"
					+ "order by numeroTitulo", Titulo.class)
					.setParameter("usuario", segUsuario.getUsuario()).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
