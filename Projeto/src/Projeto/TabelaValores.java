package Projeto;

public enum TabelaValores{
    MACA(2.00),
    BANANA(2.50),
    UVA(5.00);

    private final double valor;
    private TabelaValores(double valor){
        this.valor = valor;
    }
    public double getValor(){return valor;}
}
