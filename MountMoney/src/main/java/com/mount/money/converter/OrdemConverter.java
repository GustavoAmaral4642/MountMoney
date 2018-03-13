package com.mount.money.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.Ordem;
import com.mount.money.repository.Ordens;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Ordem.class)
public class OrdemConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Ordens ordens;
	
	// construtor de ordem
	public OrdemConverter(){
		ordens = CDIServiceLocator.getBean(Ordens.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ordem retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = ordens.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Ordem ordem = (Ordem) value;
			return ordem.getId() == null ? null : ordem.getId().toString();
		}
		
		return "";
	}
	
}
