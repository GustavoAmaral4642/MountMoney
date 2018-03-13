package com.projeto0001.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.projeto0001.model.Ativo;
import com.projeto0001.model.ContaCorretora;
import com.projeto0001.model.Ordem;
import com.projeto0001.model.OrdemConsulta;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.filter.CarteiraFilter;
import com.projeto0001.repository.filter.OrdemFilter;
import com.projeto0001.security.Seguranca;
import com.projeto0001.service.CadastroCarteiraService;
import com.projeto0001.service.CadastroOrdemService;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

import javassist.expr.NewArray;

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