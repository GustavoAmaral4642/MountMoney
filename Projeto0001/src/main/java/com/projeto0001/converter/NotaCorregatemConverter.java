package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.NotaCorretagem;
import com.projeto0001.repository.NotasCorretagens;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = NotaCorretagem.class)
public class NotaCorregatemConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private NotasCorretagens notas;
	
	// construtor de cartaoCredito
	public NotaCorregatemConverter(){
		notas = CDIServiceLocator.getBean(NotasCorretagens.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		NotaCorretagem retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = notas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			NotaCorretagem nota = (NotaCorretagem) value;
			return nota.getId() == null ? null : nota.getId().toString();
		}
		
		return "";
	}
	
}
