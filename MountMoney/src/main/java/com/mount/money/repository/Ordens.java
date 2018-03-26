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

import com.mount.money.model.Ordem;
import com.mount.money.repository.filter.OrdemFilter;
import com.mount.money.security.Seguranca;
import com.mount.money.service.NegocioException;
import com.mount.money.util.jpa.Transactional;

public class Ordens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Seguranca segUsuario = new Seguranca();

	// grava registros de ordens
	public Ordem guardar(Ordem ordem) {

		// registra o usuario
		ordem.setUsuario(segUsuario.getUsuario());

		return ordem = manager.merge(ordem);
	}

	// busca ordem por id
	public Ordem porId(Long id) {

		Ordem ordem = manager.find(Ordem.class, id);

		// apenas pode ver registros do proprio usuario
		if (ordem.getUsuario().equals(segUsuario.getUsuario())) {
			return ordem;
		}
		return null;
	}

	// remove registros de banco
	@Transactional
	public void remover(Ordem ordem) {

		try {
			// pesquisa ordem pelo ID
			ordem = porId(ordem.getId());
			// marcando ordem para exclusão
			manager.remove(ordem);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Ordem não pode ser excluída!");
		}
	}

	// busca as ordens por filtro usando 'criteria' - removido criteria
	public List<Ordem> filtradas(OrdemFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Ordem.class).createAlias("contaCorretora", "c").createAlias("ativo",
				"a");

		if (filtro.getDtMovimentoDe() != null) {
			criteria.add(Restrictions.ge("dtMovimento", filtro.getDtMovimentoDe()));
		}

		if (filtro.getDtMovimentoAte() != null) {
			criteria.add(Restrictions.le("dtMovimento", filtro.getDtMovimentoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeContaCorretora())) {
			criteria.add(
					Restrictions.ilike("c.nomeContaCorretora", filtro.getNomeContaCorretora(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getSiglaAtivo())) {
			criteria.add(Restrictions.ilike("a.siglaAtivo", filtro.getSiglaAtivo(), MatchMode.ANYWHERE));
		}

		if (filtro.getTpOrdem() != null && filtro.getTpOrdem().length > 0) {
			criteria.add(Restrictions.in("tpOrdem", filtro.getTpOrdem()));
		}

		// apenas pode ver registros do proprio usuario
		if (StringUtils.isNotBlank(segUsuario.getUsuario().getNome())) {

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}

		return criteria.addOrder(Order.asc("dtMovimento")).list();
	}

	// buscar todas as ordens para notaCorretagem
	public List<Ordem> todasOrdens() {
		return manager.createQuery("from Ordem", Ordem.class).getResultList();
	}
	
	//Buscar ordem por número
	public Ordem porNumero(String od){
		
		try {
			return manager.createQuery("from Ordem where upper(numeroOrdem) = :numOrdem"
					+ " and usuario = :us", Ordem.class)
					.setParameter("numOrdem", od.toUpperCase())
					.setParameter("us", segUsuario.getUsuario())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}	
	
}
