package br.edu.unicesumar;

/**
 * Representa um paciente e o texto com seus exames.
 */
public class Paciente {

    // O campo é public no código original, então qualquer classe pode alterá-lo.
    // Isso facilita o exemplo, mas reduz a proteção oferecida pelo encapsulamento.
    public String exames;

    /**
     * Construtor sem argumentos.
     * Ele garante que exames comece como uma String vazia, e não como null.
     */
    public Paciente(){
        exames="";
    }

}
