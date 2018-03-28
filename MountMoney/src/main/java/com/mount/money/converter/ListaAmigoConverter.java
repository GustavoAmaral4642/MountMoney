package com.mount.money.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.ListaAmigo;
import com.mount.money.repository.ListasAmigos;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ListaAmigo.class)
public class ListaAmigoConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private ListasAmigos listas;
	
	public ListaAmigoConverter(){
		listas = CDIServiceLocator.getBean(ListasAmigos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ListaAmigo retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = listas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			ListaAmigo lista = (ListaAmigo) value;
			return lista.getId() == null ? null : lista.getId().toString();
		}
		
		return "";
	}
	
}
