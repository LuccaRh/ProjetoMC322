package Projeto;

public class Maca extends Fruta{

    @Override
    protected double calculaValor(double peso){
        return peso * TabelaValores.MACA.getValor();
    }
    
}
