package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.Banco;
import com.projeto0001.model.Trader;
import com.projeto0001.repository.Bancos;
import com.projeto0001.repository.Traders;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Trader.class)
public class TraderConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Traders traders;
	
	// construtor de trader
	public TraderConverter(){
		traders = CDIServiceLocator.getBean(Traders.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Trader retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = traders.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Trader trader = (Trader) value;
			return trader.getId() == null ? null : trader.getId().toString();
		}
		
		return "";
	}
	
}
