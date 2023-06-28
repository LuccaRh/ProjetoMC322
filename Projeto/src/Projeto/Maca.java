package Projeto;

public class Maca extends Fruta{

    protected double calculaValor(double peso){
        return peso * TabelaValores.MACA.getValor();
    }
    
}
