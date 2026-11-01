package br.edu.unicesumar;

public class Produto {
    private double preco;
    private static double taxaImposto = 0.05;

    public Produto(double preco) {
        this.preco = preco;
    }

    // ✅ apenas calcula e retorna, sem alterar o preço original
    public double getPrecoComImposto() {
        return this.preco * (1 + taxaImposto);
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // ✅ agora realmente altera o atributo estático
    public static void setTaxaImposto(double novaTaxa) {
        taxaImposto = novaTaxa;
    }

    public static double getTaxaImposto() {
        return taxaImposto;
    }
}