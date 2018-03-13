package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.ContaCorretora;
import com.projeto0001.repository.ContasCorretoras;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ContaCorretora.class)
public class ContaCorretoraConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private ContasCorretoras contas;
	
	// construtor de cartaoCredito
	public ContaCorretoraConverter(){
		contas = CDIServiceLocator.getBean(ContasCorretoras.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ContaCorretora retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = contas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			ContaCorretora conta = (ContaCorretora) value;
			return conta.getId() == null ? null : conta.getId().toString();
		}
		
		return "";
	}
	
}
