package com.projeto0001.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projeto0001.model.Grupo;

public class Grupos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	//buscar grupos por código
	public Grupo porId(Long id){
		return manager.find(Grupo.class, id);
	}
	
	//buscar todos os grupos
	public List<Grupo> todosGrupos(){
		return manager.createQuery("from Grupo", Grupo.class)
				.getResultList();
	}
}
