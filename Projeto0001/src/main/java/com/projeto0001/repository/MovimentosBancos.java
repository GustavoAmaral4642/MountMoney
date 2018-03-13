package com.projeto0001.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.projeto0001.model.MovimentoBanco;
import com.projeto0001.repository.filter.MovimentoBancoFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class MovimentosBancos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de movimentos
	public MovimentoBanco guardar(MovimentoBanco movimento) {
		return movimento = manager.merge(movimento);
	}

	// busca movimento por id
	public MovimentoBanco porId(Long id) {
		return manager.find(MovimentoBanco.class, id);
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
		if (filtro.getBanco() != null) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.eq("banco", filtro.getBanco()));
		}

		if(filtro.getDataMovimentoDe() != null){
			criteria.add(Restrictions.ge("dataMovimento", filtro.getDataMovimentoDe()));
		}
		
		if(filtro.getDataMovimentoAte() != null){
			criteria.add(Restrictions.le("dataMovimento", filtro.getDataMovimentoAte()));
		}

		return criteria.addOrder(Order.asc("dataMovimento")).list();
	}

	// trazer todos os bancos que tem movimento
	public List<MovimentoBanco> todosMovimentos(){
		try {
			return manager.createQuery("from MovimentoBanco",MovimentoBanco.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
