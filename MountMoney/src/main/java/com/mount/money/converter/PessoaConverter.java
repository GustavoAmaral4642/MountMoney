package com.mount.money.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mount.money.model.Pessoa;
import com.mount.money.repository.Pessoas;
import com.mount.money.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Pessoas pessoas;
	
	// construtor 
	public PessoaConverter(){
		pessoas = CDIServiceLocator.getBean(Pessoas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = pessoas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getId() == null ? null : pessoa.getId().toString();
		}
		
		return "";
	}
	
}
