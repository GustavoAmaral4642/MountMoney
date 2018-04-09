package com.mount.money.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.Despesa;
import com.mount.money.repository.Despesas;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Despesa.class)
public class DespesaConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Despesas despesas;
	

	public DespesaConverter(){
		despesas = CDIServiceLocator.getBean(Despesas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Despesa retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = despesas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Despesa despesa = (Despesa) value;
			return despesa.getId() == null ? null : despesa.getId().toString();
		}
		
		return "";
	}
	
}
