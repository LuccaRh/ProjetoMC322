package Projeto;

public class Brocolis implements Verdura{
	public double calculaValor(double peso) {
	       return peso * TabelaValores.BROCOLIS.getValor();
	}
}