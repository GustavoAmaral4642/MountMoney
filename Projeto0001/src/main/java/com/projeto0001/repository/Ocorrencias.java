package com.projeto0001.repository;

import java.io.Serializable;
import java.util.Date;
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

import com.projeto0001.model.Ocorrencia;
import com.projeto0001.repository.filter.OcorrenciaFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class Ocorrencias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de ocorrencias
	public Ocorrencia guardar(Ocorrencia ocorrencia) {
		
		return ocorrencia = manager.merge(ocorrencia);
	}

	// busca ocorrencia por id
	public Ocorrencia porId(Long id) {
		return manager.find(Ocorrencia.class, id);
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
		
		return criteria.addOrder(Order.asc("dtOcorrencia")).list();
	}

	// trazer todas as ordens
	public List<Ocorrencia> todosOcorrencias() {
		try {
			return manager.createQuery("from Ocorrencia", Ocorrencia.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
