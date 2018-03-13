package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Ativo;
import com.mount.money.model.Banco;
import com.mount.money.model.Ocorrencia;
import com.mount.money.model.Ordem;
import com.mount.money.repository.Ocorrencias;
import com.mount.money.util.jpa.Transactional;

public class CadastroOcorrenciaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ocorrencias ocorrencias;

	// metodo para chamar o outro para salvar
	@Transactional
	public Ocorrencia salvar(Ocorrencia ocorrencia) {
		// grava o movimento
		return ocorrencias.guardar(ocorrencia);
	}

	/**** Ordem ****/
	
	// recebe ordem para inclusão de ocorrencias
	public Ocorrencia logOrdem(Ordem ordem) {

		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setTipo("ORDEM");
		ocorrencia.setDescricao("Inclusão de " + ordem.getTpOrdem().getDescricao() + " do ativo "
				+ ordem.getAtivo().getSiglaAtivo() + ", quantidade em lotes de " + ordem.getQtMovimentada()
				+ ", no valor de " + ordem.getVrTotal());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	
	/**** Ativo ****/
	
	// recebe ativo para inclusão de ocorrencias
	public Ocorrencia logAtivoI(Ativo ativo) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("ATIVO");
		ocorrencia.setDescricao("Inclusão de Ativo: " 
				+ ativo.getSiglaAtivo()  
				+ "; " + ativo.getNomeAtivo()) ;
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe ativo para inclusão de ocorrencias
	public Ocorrencia logAtivoD(Ativo ativo) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("ATIVO");
		ocorrencia.setDescricao("Exclusão de ativo " 
				+ ativo.getSiglaAtivo()  
				+ "; " + ativo.getNomeAtivo()) ;
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Banco ****/
	
	// recebe banco para inclusão de ocorrencias
	public Ocorrencia logBancoI(Banco banco) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("BANCO");
		ocorrencia.setDescricao("Inclusão de Banco: " 
				+ " " + banco.getNomeBanco()  
				+ "; Agência: " + banco.getNumeroAgencia()  
				+ "; N.Conta: " + banco.getNumeroConta()
				+ "; Tipo: " + banco.getTipoBanco()
				+ "; Valor Inicial de: " + banco.getValorInicial());
		
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe banco para inclusão de ocorrencias
	public Ocorrencia logBancoD(Banco banco) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("BANCO");
		ocorrencia.setDescricao("Exclusão de Banco: " 
				+ " " + banco.getNomeBanco()  
				+ "; Agência: " + banco.getNumeroAgencia()  
				+ "; N.Conta: " + banco.getNumeroConta()
				+ "; Tipo: " + banco.getTipoBanco()
				+ "; Valor Inicial de: " + banco.getValorInicial());
		
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}
	
}