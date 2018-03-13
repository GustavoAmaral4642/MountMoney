package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.Ativo;
import com.projeto0001.repository.Ativos;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Ativo.class)
public class AtivoConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Ativos ativos;
	
	// construtor de paresMoeda
	public AtivoConverter(){
		ativos = CDIServiceLocator.getBean(Ativos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ativo retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = ativos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Ativo moeda = (Ativo) value;
			return moeda.getId() == null ? null : moeda.getId().toString();
		}
		
		return "";
	}
	
}
