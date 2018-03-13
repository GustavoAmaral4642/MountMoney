package com.projeto0001.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.MovimentoBovespa;
import com.projeto0001.repository.MovimentosBovespas;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = MovimentoBovespa.class)
public class MovimentoBovespaConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private MovimentosBovespas movimentosBovespas;
	
	// construtor de cartaoCredito
	public MovimentoBovespaConverter(){
		movimentosBovespas = CDIServiceLocator.getBean(MovimentosBovespas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		MovimentoBovespa retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = movimentosBovespas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			MovimentoBovespa movimento = (MovimentoBovespa) value;
			return movimento.getId() == null ? null : movimento.getId().toString();
		}
		
		return "";
	}
	
}
