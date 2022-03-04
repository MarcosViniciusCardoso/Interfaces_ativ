package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarros;
import model.entities.Veiculos;
import model.services.BrasilTaxServicos;
import model.services.ServicoAluguel;

public class Programa {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:ss");
		
		System.out.println("I nsira os dados de aluguel");
		System.out.print("Modelo Carro: ");
		String modeloCarro = sc.nextLine();
		System.out.print("Saída (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/yyyy hh:mm)");
		Date finish = sdf.parse(sc.nextLine());
		
		AluguelCarros cr = new AluguelCarros(start, finish, new Veiculos(modeloCarro));
		
		System.out.print("Insira o preço por hora:");
		double precohoras  = sc.nextDouble();
		System.out.print("Insira o preço por dia: ");
		double precodia = sc.nextDouble();
		
		ServicoAluguel servicoAluguel = new ServicoAluguel(precodia , precohoras, new BrasilTaxServicos());
		
		servicoAluguel.processoFaturar(cr);
		
		System.out.println("Pagamento");
		System.out.println("Pagamento Básico: " + String.format("%.2f", cr.getPagamento().getPagamentoBasico()));
		System.out.println("taxa: " + String.format("%.2f", cr.getPagamento().getTaxa()));
		System.out.println("Pagamento Total: " + String.format("%.2f", cr.getPagamento().totalpagamento()));
		
		sc.close();
	}

}
