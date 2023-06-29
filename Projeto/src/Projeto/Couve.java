package Projeto;

public class Couve implements Verdura{
	public double calculaValor(double peso) {
	       return peso * TabelaValores.COUVE.getValor();
	}
}