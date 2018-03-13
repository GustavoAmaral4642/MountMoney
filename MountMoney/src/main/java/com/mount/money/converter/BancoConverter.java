package com.mount.money.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.Banco;
import com.mount.money.repository.Bancos;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Banco.class)
public class BancoConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Bancos bancos;
	
	// construtor de cartaoCredito
	public BancoConverter(){
		bancos = CDIServiceLocator.getBean(Bancos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Banco retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = bancos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Banco banco = (Banco) value;
			return banco.getId() == null ? null : banco.getId().toString();
		}
		
		return "";
	}
	
}
