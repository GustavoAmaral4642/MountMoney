package com.mount.money.service;

import java.io.Serializable;

public class NegocioException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;

	public NegocioException(String msg) {
		super(msg);
	}
	
}