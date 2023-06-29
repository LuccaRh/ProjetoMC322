package Projeto;

public class Alface implements Verdura{
	public double calculaValor(double peso) {
	       return peso * TabelaValores.ALFACE.getValor();
	}
}
