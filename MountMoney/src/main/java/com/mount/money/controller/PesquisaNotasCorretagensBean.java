package com.mount.money.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mount.money.model.NotaCorretagem;
import com.mount.money.repository.NotasCorretagens;
import com.mount.money.repository.filter.NotaCorretagemFilter;
import com.mount.money.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaNotasCorretagensBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//injetando classe notasCorretagens
	@Inject
	private NotasCorretagens notas;

	private NotaCorretagemFilter filtro; // variavel para usar campos no filtro
	private List<NotaCorretagem> notasFiltradas; // receber notas para listagem
	
	public PesquisaNotasCorretagensBean(){
		limpar();
	}
	
	public void limpar(){
		filtro = new NotaCorretagemFilter();
		notasFiltradas = new ArrayList<>();		
	}
	
	//metodo para excluir notas
	public void excluir(NotaCorretagem notaSelecionada){
		//exclui nota do banco de dados
		notas.remover(notaSelecionada);
		//exclui bancos da lista
		notasFiltradas.remove(notaSelecionada);
		
		FacesUtil.addInfoMessage("Nota de Corretagem " + notaSelecionada.getNumeroNota() + " excluída com sucesso!");
	}
	
	//metodo para pesquisar notas
	public void pesquisar(){
		notasFiltradas = notas.filtradas(filtro);		
	}

	public List<NotaCorretagem> getNotasFiltradas() {
		return notasFiltradas;
	}

	public NotaCorretagemFilter getFiltro() {
		return filtro;
	}

}

