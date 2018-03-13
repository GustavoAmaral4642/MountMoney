package com.mount.money.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	// retorna primeiro acesso ou não.
	public static boolean isPostback(){
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	// nega primeiro acesso
	public static boolean isNotPostback(){
		return !isPostback();
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	public static void addInfoMessage(String message)
	{
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
}