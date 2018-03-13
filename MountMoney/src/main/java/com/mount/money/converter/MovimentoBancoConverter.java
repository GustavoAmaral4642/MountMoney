package com.mount.money.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.MovimentoBanco;
import com.mount.money.repository.MovimentosBancos;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = MovimentoBanco.class)
public class MovimentoBancoConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private MovimentosBancos movimentosBancos;
	
	// construtor de cartaoCredito
	public MovimentoBancoConverter(){
		movimentosBancos = CDIServiceLocator.getBean(MovimentosBancos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		MovimentoBanco retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = movimentosBancos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			MovimentoBanco movimento = (MovimentoBanco) value;
			return movimento.getId() == null ? null : movimento.getId().toString();
		}
		
		return "";
	}
	
}
