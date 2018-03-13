package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.GrupoInvestidor;
import com.projeto0001.repository.GruposInvestidores;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = GrupoInvestidor.class)
public class GrupoInvestidoresConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private GruposInvestidores grupos;
	
	// construtor de produtos
	public GrupoInvestidoresConverter(){
		grupos = CDIServiceLocator.getBean(GruposInvestidores.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		GrupoInvestidor retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = grupos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			GrupoInvestidor grupo = (GrupoInvestidor) value;
			return grupo.getId() == null ? null : grupo.getId().toString();
		}
		
		return "";
	}	
}
