package Projeto;

public class Acelga implements Verdura{
	public double calculaValor(double peso) {
	       return peso * TabelaValores.ACELGA.getValor();	
	}
}