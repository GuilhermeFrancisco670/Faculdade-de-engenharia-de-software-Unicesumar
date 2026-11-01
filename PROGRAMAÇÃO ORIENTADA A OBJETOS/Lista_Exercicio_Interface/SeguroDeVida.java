package br.edu.unicesumar;

public class SeguroDeVida implements Tributavel{

    @Override
    public double getValorImposto() {
        return saldo;
    }

    @Override
    public double getSomar() {
        // soma += soma;
        return soma;
    }
}
