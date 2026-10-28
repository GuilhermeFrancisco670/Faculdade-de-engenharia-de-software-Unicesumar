package br.edu.unicesumar;

public class IngressoVIP extends Ingresso {
    private double valorAdicional = 10.00;
    private double valorTotal = valor + valorAdicional;

    public IngressoVIP(double valor) {
        super(valor);
    }

    @Override
    public void imprimirValor() {
        //valorTotal;
        System.out.println("O valor do Ingresso Vip é: " + valorTotal);
    }

}
