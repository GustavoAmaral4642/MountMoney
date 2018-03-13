package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.TituloPagarParcela;
import com.projeto0001.repository.TitulosPagarParcela;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TituloPagarParcela.class)
public class TituloPagarParcelaConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private TitulosPagarParcela titulosParcela;
	
	// construtor de pedidos
	public TituloPagarParcelaConverter(){
		titulosParcela = CDIServiceLocator.getBean(TitulosPagarParcela.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TituloPagarParcela retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = titulosParcela.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			TituloPagarParcela tituloParcela = (TituloPagarParcela) value;
			return tituloParcela.getId() == null ? null : tituloParcela.getId().toString();
		}
		
		return "";
	}
	
}
