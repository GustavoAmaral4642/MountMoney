package com.mount.money.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	// factory fica no escopo de ApplicationScoped
	private EntityManagerFactory factory;
	
	public EntityManagerProducer(){

		factory = Persistence.createEntityManagerFactory("MountMoneyPU");		
	}
	
	// um entity manager produzido aqui, tem um escopo de requisição,
	// a requisicao guarda o entity produzido e armazena ele.
	@Produces @RequestScoped
	public EntityManager createEntityManager(){
		return factory.createEntityManager(); 
	}
	
	
	// fecha a conexão com o banco de dados.
	// o entity que foi produzido pelo metodo createEntityManager, esta no escopo de requisicao
	// quando o escopo finalizar, chamara o metodo close. Dispose é um gatilho para disparar este metodo
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
