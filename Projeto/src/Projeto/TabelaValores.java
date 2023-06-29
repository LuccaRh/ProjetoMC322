package Projeto;

public enum TabelaValores{
    ALFACE(3.50),
    BROCOLIS(5.00),
    COUVE(4.00),
    ACELGA(2.00),
	MACA(2.00),
    BANANA(2.50),
    UVA(5.00);

    private final double valor;
    private TabelaValores(double valor){
        this.valor = valor;
    }
    public double getValor(){return valor;}
}
