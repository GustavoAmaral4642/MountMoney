package com.mount.money.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mount.money.model.Ativo;
import com.mount.money.model.Banco;
import com.mount.money.model.Categoria;
import com.mount.money.model.ContaCorretora;
import com.mount.money.model.Despesa;
import com.mount.money.model.MovimentoBanco;
import com.mount.money.model.NotaCorretagem;
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
		ocorrencia.setDescricao("Inclusão de Ativo: " + ativo.getSiglaAtivo() + "; " + ativo.getNomeAtivo());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe ativo para inclusão de ocorrencias
	public Ocorrencia logAtivoD(Ativo ativo) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("ATIVO");
		ocorrencia.setDescricao("Exclusão de ativo " + ativo.getSiglaAtivo() + "; " + ativo.getNomeAtivo());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Banco ****/

	// recebe banco para inclusão de ocorrencias
	public Ocorrencia logBancoI(Banco banco) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("BANCO");
		ocorrencia.setDescricao("Inclusão de Banco: " + " " + banco.getNomeBanco() + "; Agência: "
				+ banco.getNumeroAgencia() + "; N.Conta: " + banco.getNumeroConta() + "; Tipo: " + banco.getTipoBanco()
				+ "; Valor Inicial de: " + banco.getValorInicial());

		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe banco para inclusão de ocorrencias
	public Ocorrencia logBancoD(Banco banco) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("BANCO");
		ocorrencia.setDescricao("Exclusão de Banco: " + " " + banco.getNomeBanco() + "; Agência: "
				+ banco.getNumeroAgencia() + "; N.Conta: " + banco.getNumeroConta() + "; Tipo: " + banco.getTipoBanco()
				+ "; Valor Inicial de: " + banco.getValorInicial());

		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Conta de Corretora ****/

	// recebe conta de corretora para inclusão de ocorrencias
	public Ocorrencia logContaCorretoraI(ContaCorretora conta) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("CONTA DE CORRETORA");
		ocorrencia.setDescricao("Inclusão de Conta de Corretora: " + conta.getNumeroContaCorretora() + "; "
				+ conta.getNomeContaCorretora());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe ativo para inclusão de ocorrencias
	public Ocorrencia logContaCorretoraD(ContaCorretora conta) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("CONTA DE CORRETORA");
		ocorrencia.setDescricao("Exclusão de Conta de Corretora " + conta.getNumeroContaCorretora() + "; "
				+ conta.getNomeContaCorretora());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Despesa ****/

	// recebe despesa para inclusão de ocorrencias
	public Ocorrencia logDespesaI(Despesa despesa) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Despesa");
		ocorrencia.setDescricao("Inclusão de Despesa no valor de: " + despesa.getValorDespesa() + "; Banco: "
				+ despesa.getBanco().getNomeBanco() + "; Histórico: " + despesa.getHistorico());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe despesa para inclusão de ocorrencias
	public Ocorrencia logDespesaD(Despesa despesa) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Despesa");
		ocorrencia.setDescricao("Exclusão de Despesa no valor de: " + despesa.getValorDespesa() + "; Banco: "
				+ despesa.getBanco().getNomeBanco() + "; Histórico: " + despesa.getHistorico());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Movimento Bancário ****/

	// recebe movimento banco para inclusão de ocorrencias
	public Ocorrencia logMovimentoBancoI(MovimentoBanco movimento) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Movimento Bancário");
		ocorrencia.setDescricao("Inclusão de Movimento Bancário no valor de: " + movimento.getValorMovimento()
				+ "; Banco: " + movimento.getBanco().getNomeBanco() + "; Histórico: " + movimento.getHistorico());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe despesa para inclusão de ocorrencias
	public Ocorrencia logMovimentoBancoD(MovimentoBanco movimento) {

		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setTipo("Movimento Bancário");
		ocorrencia.setDescricao("Exclusão de Movimento Bancário no valor de: " + movimento.getValorMovimento()
				+ "; Banco: " + movimento.getBanco().getNomeBanco() + "; Histórico: " + movimento.getHistorico());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Nota de Corretagem ****/

	// recebe Nota de Corretagem para inclusão de ocorrencias
	public Ocorrencia logNotaCorretagemI(NotaCorretagem nota) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Nota de Corretagem");
		ocorrencia.setDescricao("Inclusão de Nota de Corretagem Número: " + nota.getNumeroNota()
				+ "; Corretora: " + nota.getContaCorretora().getNomeContaCorretora() 
				+ "; Conta Bancária: " + nota.getContaBancaria());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe nota de Corretagem para inclusão de ocorrencias
	public Ocorrencia logNotaCorretagemD(NotaCorretagem nota) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Nota de Corretagem");
		ocorrencia.setDescricao("Exclusão de Nota de Corretagem Número: " + nota.getNumeroNota()
				+ "; Corretora: " + nota.getContaCorretora().getNomeContaCorretora() 
				+ "; Conta Bancária: " + nota.getContaBancaria());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	/**** Categoria ****/

	// recebe Categoria para inclusão de ocorrencias
	public Ocorrencia logCategoriaI(Categoria categoria) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Categoria");
		ocorrencia.setDescricao("Inclusão de Categoria Sigla: " + categoria.getSiglaCategoria()
				+ "; Nome: " + categoria.getNomeCategoria());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	// recebe Categoria para inclusão de ocorrencias
	public Ocorrencia logCategoriaD(Categoria categoria) {

		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setTipo("Categoria");
		ocorrencia.setDescricao("Exclusão da Categoria Sigla: " + categoria.getSiglaCategoria()
				+ "; Nome: " + categoria.getNomeCategoria());
		ocorrencia.setDtOcorrencia(new java.sql.Date(System.currentTimeMillis()));

		return ocorrencia;
	}

	
}