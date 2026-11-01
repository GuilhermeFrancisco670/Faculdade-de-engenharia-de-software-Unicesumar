package br.edu.unicesumar;

public class Livro {
    private static int contadorDeLivros;

    public Livro() {
        contadorDeLivros++; // ✅ incrementa a cada nova instância
    }

    public static int getContadorDeLivros() {
        return contadorDeLivros;
    }
}