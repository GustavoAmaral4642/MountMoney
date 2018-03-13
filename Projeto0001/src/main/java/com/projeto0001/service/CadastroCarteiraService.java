package com.projeto0001.service;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.projeto0001.model.Ordem;
import com.projeto0001.model.OrdemConsulta;
import com.projeto0001.model.OrdemTotal;
import com.projeto0001.model.TipoOrdem;
import com.projeto0001.repository.Carteiras;
import com.projeto0001.repository.filter.CarteiraFilter;
import com.projeto0001.util.jsf.FacesUtil;

public class CadastroCarteiraService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Carteiras carteiras;

	@Inject
	private ConsultarYahooFinance c;

	public List<OrdemConsulta> consultaPrecoMedio(List<String> ativosFiltrados, CarteiraFilter filtro)
			throws IOException {

		OrdemConsulta odConsulta;
		List<OrdemConsulta> odsConsultas = new ArrayList<>();
		List<Ordem> ordens = new ArrayList<>();

		BigDecimal aux1compra;
		BigDecimal aux2compra;
		Long aux3compra;
		BigDecimal aux4compra;

		for (String s : ativosFiltrados) {
			odConsulta = new OrdemConsulta();
			ordens = carteiras.montaRelatorio(s, filtro);
			odConsulta.setAtivo(s);

			aux1compra = new BigDecimal(0); // recebe o valor total da ordem
			aux2compra = new BigDecimal(0); // recebe o valor dividido - preco
											// médio
			aux3compra = new Long(0); // recebe quantidade total
			aux4compra = new BigDecimal(0); // recebe valor da bmf

			for (Ordem o : ordens) {
				if (o.getTpOrdem().equals(TipoOrdem.COMPRA)) {
					aux1compra = aux1compra.add(o.getVrTotal());
					aux3compra += o.getQtMovimentada();
					aux2compra = aux1compra.divide(new BigDecimal(aux3compra), MathContext.DECIMAL32);

				} else if (o.getTpOrdem().equals(TipoOrdem.VENDA)) {
					aux3compra -= o.getQtMovimentada();
					// aux1compra = aux1compra.subtract(o.getVrTotal());
					aux1compra = aux2compra.multiply(new BigDecimal(aux3compra));
					if (aux1compra.compareTo(BigDecimal.ZERO) < 0) {
						aux1compra = BigDecimal.ZERO;
						aux2compra = BigDecimal.ZERO;
					}
				} else {

				}
			}
			if (aux1compra.equals(BigDecimal.ZERO) || aux1compra.compareTo(BigDecimal.ZERO) < 0) {
				aux1compra = BigDecimal.ZERO;
				continue;
			}
			if (aux3compra <= 0) {
				continue;
			}

			try{
			aux4compra = c.consultaAtivo(odConsulta.getAtivo());
			}catch (RuntimeException e){
				FacesUtil.addErrorMessage("Ocorreram problemas ao tentar consultar o valor dos ativos! \n"
						+ "Erro: " + e.getMessage());				
			}
			
			odConsulta.setQntTotal(aux3compra);
			odConsulta.setVrMedio(aux2compra);
			odConsulta.setVrTotal(aux1compra);
			odConsulta.setVrAtivo(aux4compra);

			odsConsultas.add(odConsulta);
		}

		return odsConsultas;
	}

	public List<OrdemTotal> consultaPrecoLucro(List<String> ativosFiltrados, CarteiraFilter filtro) throws IOException {

		OrdemTotal odTotal;
		OrdemConsulta odConsulta;
		List<OrdemConsulta> odsConsultas;

		List<OrdemTotal> odsTotais = new ArrayList<>();
		List<Ordem> ordens = new ArrayList<>();

		Date auxDtMovimento;
		Long auxQntMovimentada;
		TipoOrdem auxTipoOrdem;
		BigDecimal auxVrOperacao;
		BigDecimal auxVrTotal;
		BigDecimal auxVrMedio;
		BigDecimal auxVrLucro;
		BigDecimal auxLucroImposto;

		for (String s : ativosFiltrados) {
			odTotal = new OrdemTotal();
			ordens = carteiras.montaRelatorio(s, filtro);
			odTotal.setSiglaAtivo(s);

			auxDtMovimento = new Date();
			auxQntMovimentada = new Long(0);
			auxTipoOrdem = TipoOrdem.COMPRA;
			auxVrOperacao = new BigDecimal(0);
			auxVrTotal = new BigDecimal(0);
			auxVrMedio = new BigDecimal(0);
			auxVrLucro = new BigDecimal(0);
			
			odsConsultas = new ArrayList<>();

			for (Ordem o : ordens) {

				odConsulta = new OrdemConsulta();
				auxLucroImposto = new BigDecimal(0);
				
				if (o.getTpOrdem().equals(TipoOrdem.COMPRA)) {
					auxDtMovimento = o.getDtMovimento();
					odConsulta.setDtMovimento(auxDtMovimento);
					
					auxTipoOrdem = o.getTpOrdem();
					odConsulta.setTpOrdem(auxTipoOrdem);
					
					auxVrOperacao = o.getVrOperacao();
					odConsulta.setVrOperacao(auxVrOperacao);
					
					// calculo do medio
					auxVrTotal = auxVrTotal.add(o.getVrTotal());
					
					//calculo do imposto
					if(!o.getVrCorretagem().equals(0)){
						auxLucroImposto = auxLucroImposto.add(o.getVrCorretagem());
						auxLucroImposto = auxLucroImposto.add(o.getVrEmolumentos());
						auxLucroImposto = auxLucroImposto.add(o.getVrTaxaLiquidacao());
						auxVrTotal = auxVrTotal.add(auxLucroImposto);
					}
					
					auxQntMovimentada += o.getQtMovimentada();
					auxVrMedio = auxVrTotal.divide(new BigDecimal(auxQntMovimentada), MathContext.DECIMAL32);
					
					
					odConsulta.setQntMovimentada(auxQntMovimentada);
					odConsulta.setVrMedio(auxVrMedio);

					// estes fazem assim por causa que as auxiliares sao usadas pra
					// calculo do médio
					odConsulta.setVrTotal(o.getVrTotal());
					odConsulta.setQntMovimentada(o.getQtMovimentada());
					odConsulta.setQntTotal(auxQntMovimentada);					

				} else if (o.getTpOrdem().equals(TipoOrdem.VENDA)) {
					auxDtMovimento = o.getDtMovimento();
					odConsulta.setDtMovimento(auxDtMovimento);
					
					auxTipoOrdem = o.getTpOrdem();
					odConsulta.setTpOrdem(auxTipoOrdem);
					
					auxVrOperacao = o.getVrOperacao();
					odConsulta.setVrOperacao(auxVrOperacao);
					
					odConsulta.setQntMovimentada(o.getQtMovimentada());
					odConsulta.setVrTotal(o.getVrTotal());
					
					// calculo do medio e lucro
					auxQntMovimentada -= o.getQtMovimentada();
					auxVrTotal = auxVrMedio.multiply(new BigDecimal(auxQntMovimentada));
					
					
					if (auxVrTotal.compareTo(BigDecimal.ZERO) < 0) {
						auxVrTotal = BigDecimal.ZERO;
						auxVrMedio = BigDecimal.ZERO;
					} else {
						auxVrLucro = o.getVrOperacao().subtract(auxVrMedio);
						auxVrLucro = auxVrLucro.multiply(new BigDecimal(o.getQtMovimentada()), MathContext.DECIMAL32);	
						
						if(!o.getVrCorretagem().equals(0)){
							auxLucroImposto = auxLucroImposto.add(o.getVrCorretagem());
							auxLucroImposto = auxLucroImposto.add(o.getVrEmolumentos());
							auxLucroImposto = auxLucroImposto.add(o.getVrTaxaLiquidacao());		
							auxVrLucro = auxVrLucro.subtract(auxLucroImposto);
						}
					}
					
					odConsulta.setQntTotal(auxQntMovimentada);
					
					odConsulta.setVrLucro(auxVrLucro);
					odConsulta.setVrMedio(auxVrMedio);
					
				} else {

				}

				odsConsultas.add(odConsulta);
			}

			odTotal.setOdConsultas(odsConsultas);

			odsTotais.add(odTotal);
		}

		return odsTotais;
	}

}
