package com.projeto0001.repository.filter;

import java.io.Serializable;

// método usuado para filtro do Cliente.java
public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clienteFornecedor;
	private String documentoReceitaFederal;
	private String nome;

	public String getClienteFornecedor() {
		return clienteFornecedor;
	}

	public void setClienteFornecedor(String clienteFornecedor) {
		this.clienteFornecedor = clienteFornecedor;
	}

	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}

	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
