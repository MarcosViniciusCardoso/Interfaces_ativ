package model.services;

import model.entities.AluguelCarros;
import model.entities.Pagamento;

public class ServicoAluguel {
	private Double precoPorDia;
	private Double precoPorHoras;
	
	private TaxaServico taxaServico;

	public ServicoAluguel(Double precoPorDia, Double precoPorHoras, TaxaServico taxaServico) {
		this.precoPorDia = precoPorDia;
		this.precoPorHoras = precoPorHoras;
		this.taxaServico = taxaServico;
	}
	
	public void processoFaturar(AluguelCarros alugeulCarros) {
		long t1 = alugeulCarros.getStart().getTime(); //Pegar hora de saida.
		long t2 = alugeulCarros.getFinish().getTime();//Pegar hora de entrada.
		double horas = (double) /* <-Casting */ (t2-t1)/1000/60/60;
		double pagamentoBasico;
		if(horas <= 12.0) {
			 pagamentoBasico = Math.ceil(horas)*precoPorHoras;
			
		}else {
			
			 pagamentoBasico = Math.ceil(horas/24)*precoPorDia;
		}
		
		double taxa = taxaServico.taxa(pagamentoBasico);
		alugeulCarros.setPagamento(new Pagamento(pagamentoBasico, taxa));
	}
	
	
}
