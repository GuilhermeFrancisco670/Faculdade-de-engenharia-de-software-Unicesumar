package br.edu.unicesumar;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public void calcularTaxas() {
        System.out.println("A Conta poupança não possue taxas ");
    }
}
