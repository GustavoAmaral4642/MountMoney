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

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.Contato;
import com.projeto0001.model.Endereco;
import com.projeto0001.repository.filter.ClienteFilter;
import com.projeto0001.service.NegocioException;
import com.projeto0001.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// grava registros de clientes
	public ClienteFornecedor guardar(ClienteFornecedor cliente) {

		return cliente = manager.merge(cliente);
	}

	// busca se existe um cliente com o CNPJ ou CPF igual
	public ClienteFornecedor porDocumento(String documentoReceitaFederal) {
		try {
			return manager
					.createQuery("from Cliente where upper(documentoReceitaFederal) = :documento",
							ClienteFornecedor.class)
					.setParameter("documento", documentoReceitaFederal.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// busca se existe um cliente por nome
	public List<ClienteFornecedor> porNome(String nome) {
		return this.manager.createQuery("from Cliente " + "where upper(nome) like :nome", ClienteFornecedor.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	// busca os clientes por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<ClienteFornecedor> filtrados(ClienteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ClienteFornecedor.class);

		// se o status não estiver vazio. Importacao da ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getClienteFornecedor())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("cliente_fornecedor", filtro.getClienteFornecedor(), MatchMode.ANYWHERE));
		}

		// se o documentoReceitaFederal não estiver vazio. Importacao da
		// ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("documentoReceitaFederal", filtro.getDocumentoReceitaFederal(),
					MatchMode.ANYWHERE));
		}

		// se o nome não estiver vazio. Importacao da ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getNome())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	// busca cliente por id
	public ClienteFornecedor porId(Long id) {
		return manager.find(ClienteFornecedor.class, id);
	}

	// busca Endereco por id
	public Endereco enderecoPorId(Long id) {
		return manager.find(Endereco.class, id);
	}

	// busca Contato por id
	public Contato contatoPorId(Long id) {
		return manager.find(Contato.class, id);
	}

	// remove registros de cliente
	@Transactional
	public void remover(ClienteFornecedor cliente) {

		try {
			// pesquisa cliente pelo ID
			cliente = porId(cliente.getId());
			// marcando cliente para exclusão
			manager.remove(cliente);

			// tudo que estiver pendente para execução no BD, executa apos o
			// flush.
			// se o cliente estiver sendo usado por outra tabela, flush lança
			// exceção
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído!");
		}
	}

	public List<ClienteFornecedor> todos() {
		return manager.createQuery("from Cliente ORDER BY nome", ClienteFornecedor.class).getResultList();
	}

	// busca os endereços dos clientes por filtro usando criteria
	@SuppressWarnings("unchecked")
	public List<Endereco> enderecosFiltrados(Endereco filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ClienteFornecedor.class);

		// se o logradouro não estiver vazio. Importacao da
		// ferramenta pelo pom.xml
		if (StringUtils.isNotBlank(filtro.getLogradouro())) {
			// adicionando restricao de igualdade 'ilike' é case insensitive
			// MATCHMODE é para colocar o % utilizado no like.
			criteria.add(Restrictions.ilike("logradouro", filtro.getLogradouro(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("logradouro")).list();
	}

}
