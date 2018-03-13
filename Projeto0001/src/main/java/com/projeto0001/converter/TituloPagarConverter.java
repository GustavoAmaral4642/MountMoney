package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.TituloPagar;
import com.projeto0001.repository.TitulosPagar;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TituloPagar.class)
public class TituloPagarConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private TitulosPagar titulos;
	
	// construtor de pedidos
	public TituloPagarConverter(){
		titulos = CDIServiceLocator.getBean(TitulosPagar.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TituloPagar retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = titulos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			TituloPagar titulo = (TituloPagar) value;
			return titulo.getId() == null ? null : titulo.getId().toString();
		}
		
		return "";
	}
	
}
