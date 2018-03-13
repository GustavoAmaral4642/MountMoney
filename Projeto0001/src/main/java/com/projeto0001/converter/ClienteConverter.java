package com.projeto0001.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.repository.Clientes;
import com.projeto0001.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ClienteFornecedor.class)
public class ClienteConverter implements Converter{

	//@Inject -> não funciona em conversores JSF 2.1
	private Clientes clientes;
	
	// construtor de produtos
	public ClienteConverter(){
		clientes = CDIServiceLocator.getBean(Clientes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ClienteFornecedor retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = clientes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		// testa inclusão e edição de registros
		if (value != null) {
			ClienteFornecedor cliente = (ClienteFornecedor) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		
		return "";
	}
	
}
