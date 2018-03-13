package com.mount.money.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mount.money.model.Ordem;
import com.mount.money.repository.filter.CarteiraFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.CadastroCarteiraService;


public class Carteiras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Seguranca segUsuario = new Seguranca();

	private CadastroCarteiraService cadastroCarteiraService;

	public List<String> somenteAtivos(CarteiraFilter filtro) {

		List<String> atvs = new ArrayList<>();

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Ordem.class).createAlias("contaCorretora", "c").createAlias("ativo",
				"a");

		ProjectionList projList = Projections.projectionList();

		if (StringUtils.isNotBlank(filtro.getNomeContaCorretora())) {
			criteria.add(
					Restrictions.ilike("c.nomeContaCorretora", filtro.getNomeContaCorretora(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getSiglaAtivo())) {
			criteria.add(Restrictions.ilike("a.siglaAtivo", filtro.getSiglaAtivo(), MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		projList.add(Projections.groupProperty("a.siglaAtivo"));
		criteria.setProjection(projList);

		atvs = criteria.addOrder(Order.asc("a.siglaAtivo")).list();

		return atvs;
	}

	//
	public List<Ordem> montaRelatorio(String ativo, CarteiraFilter filtro) {

		List<Ordem> ordensConsultadas = new ArrayList<>();
		Session session = manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Ordem.class).createAlias("contaCorretora", "c").createAlias("ativo",
				"a");
		if (StringUtils.isNotBlank(filtro.getNomeContaCorretora())) {
			criteria.add(
					Restrictions.ilike("c.nomeContaCorretora", filtro.getNomeContaCorretora(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(ativo)) {
			criteria.add(Restrictions.ilike("a.siglaAtivo", ativo, MatchMode.ANYWHERE));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		ordensConsultadas = criteria.addOrder(Order.asc("dtMovimento")).list();

		// odsConsultas =
		// cadastroOrdemService.calcularPrecoMedio(ordensConsultadas);

		return ordensConsultadas;

	}
	
}