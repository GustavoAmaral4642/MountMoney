package com.mount.money.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.Titulo;
import com.mount.money.repository.Titulos;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Titulo.class)
public class TituloConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Titulos titulos;
	

	public TituloConverter(){
		titulos = CDIServiceLocator.getBean(Titulos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Titulo retorno = null;
		
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
			Titulo titulo = (Titulo) value;
			return titulo.getId() == null ? null : titulo.getId().toString();
		}
		
		return "";
	}
	
}
