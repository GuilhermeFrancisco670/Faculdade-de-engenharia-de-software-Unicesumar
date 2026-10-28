package br.edu.unicesumar;

public abstract class Ingresso {
    protected double valor;

    public Ingresso(double valor) {
        this.valor = valor;
    }

    public abstract void imprimirValor();
}
