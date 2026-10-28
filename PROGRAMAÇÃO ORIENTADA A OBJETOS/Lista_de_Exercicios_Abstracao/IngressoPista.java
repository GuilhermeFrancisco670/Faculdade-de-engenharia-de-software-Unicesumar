package br.edu.unicesumar;

public class IngressoPista extends Ingresso{
    public IngressoPista(double valor) {
        super(valor);
    }

    @Override
    public void imprimirValor() {
        System.out.println("O valor do Ingresso é: " + valor + "\n");
    }
}
