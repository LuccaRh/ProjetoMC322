package Projeto;

public class Uva extends Fruta{

    @Override
    protected double calculaValor(double peso){
        return peso * TabelaValores.UVA.getValor();
    }
    
}
