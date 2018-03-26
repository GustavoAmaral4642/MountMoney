package com.mount.money.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mount.money.model.ContaCorretora;
import com.mount.money.model.Ocorrencia;
import com.mount.money.repository.filter.OcorrenciaFilter;
import com.mount.money.security.Seguranca;

public class Ocorrencias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private Seguranca segUsuario = new Seguranca();

	// grava registros de ocorrencias
	public Ocorrencia guardar(Ocorrencia ocorrencia) {
		
		//registra o usuario
		ocorrencia.setUsuario(segUsuario.getUsuario());
		
		return ocorrencia = manager.merge(ocorrencia);
	}

	// busca ocorrencia por id
	public Ocorrencia porId(Long id) {
		
		Ocorrencia oc = manager.find(Ocorrencia.class, id);
		
		//apenas pode fver registros do proprio usuario
		if(oc.getUsuario().equals(segUsuario.getUsuario())){
			return oc;
		}
		
		return null;
	}

	// busca as ocorrencias por filtro usando 'criteria' - removido criteria
	public List<Ocorrencia> filtradas(OcorrenciaFilter filtro) {

		Session session = manager.unwrap(Session.class);		
		Criteria criteria = session.createCriteria(Ocorrencia.class);

		if(filtro.getDtOcorrenciaDe() != null){
			criteria.add(Restrictions.ge("dtOcorrencia", filtro.getDtOcorrenciaDe()));
		}
		
		if(filtro.getDtOcorrenciaAte() != null){
			criteria.add(Restrictions.ge("dtOcorrencia", filtro.getDtOcorrenciaAte()));
		}
		
		if(StringUtils.isNotBlank(filtro.getDescricao())){
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		// apenas pode ver registros do proprio usuario
		if(StringUtils.isNotBlank(segUsuario.getUsuario().getNome())){

			criteria.add(Restrictions.eq("usuario", segUsuario.getUsuario()));
		}
		
		return criteria.addOrder(Order.asc("dtOcorrencia")).list();
	}

	// trazer todas as ordens
	public List<Ocorrencia> todosOcorrencias() {
		try {
			return manager.createQuery("from Ocorrencia oc "
					+ "where oc.usuario = :us ", Ocorrencia.class)
					.setParameter("us", segUsuario.getUsuario())
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
