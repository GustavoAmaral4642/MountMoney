package com.projeto0001.repository;

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

import com.projeto0001.model.GrupoInvestidor;
import com.projeto0001.model.Usuario;
import com.projeto0001.repository.filter.GrupoInvestidorFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class GruposInvestidores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de grupos de investidores
	public GrupoInvestidor guardar(GrupoInvestidor grupo) {
		System.out.println("1");
		return grupo = manager.merge(grupo);
	}

	// buscar grupos de investidores por código
	public GrupoInvestidor porId(Long id) {
		return manager.find(GrupoInvestidor.class, id);
	}

	// buscar todos os grupos de investidores
	public List<GrupoInvestidor> todosGruposInvestidores() {
		return manager.createQuery("from GrupoInvestidor", GrupoInvestidor.class).getResultList();
	}

	// busca usuários dentro de grupos de investidores
	public GrupoInvestidor carregarUsuariosInvestidores(GrupoInvestidor grupo) {
		return manager.createQuery("from GrupoInvestidor u where id = :grupoInvestidor", GrupoInvestidor.class)
				.setParameter("grupoInvestidor", grupo.getId()).getSingleResult();
	}

	// remove registros de grupos de investimento
	@Transactional
	public void remover(GrupoInvestidor grupoInvestidor) {
		
		try {
			// pesquisa grupo de investimento pelo ID
			grupoInvestidor= porId(grupoInvestidor.getId());

			// marcando grupo de investimento para exclusão
			manager.remove(grupoInvestidor);

			// tudo que estiver pendente para execução no BD, executa após o
			// flush
			// se o produto estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo de investimento não pode ser excluído!");
		}
	}

	// busca os grupos de investidores por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<GrupoInvestidor> filtrados(GrupoInvestidorFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(GrupoInvestidor.class);

		// se o nome não estiver vazio. Importacao da ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getNomeGrupo())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nomeGrupo", filtro.getNomeGrupo(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nomeGrupo")).list();
	}

}
