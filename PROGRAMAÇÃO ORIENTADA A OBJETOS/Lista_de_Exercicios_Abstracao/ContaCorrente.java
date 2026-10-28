package br.edu.unicesumar;

public class ContaCorrente extends Conta {
    public ContaCorrente(String titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public void calcularTaxas() {
        double resultadoCorrente = getSaldo() - 15;
    }
}
