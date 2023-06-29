package Projeto;

public class Banana extends Fruta{

    @Override
    protected double calculaValor(double peso){
        return peso * TabelaValores.BANANA.getValor();
    }
    
}
