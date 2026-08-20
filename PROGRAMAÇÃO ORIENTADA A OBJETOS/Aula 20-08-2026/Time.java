package br.edu.unicesumar;

/**
 * Representa um time com nome e treinador.
 */
public class Time {

    // Nome do time protegido por encapsulamento.
    private String nome;

    // Referência para o treinador associado ao time.
    private Treinador treinador;

    /**
     * Construtor que recebe todas as informações necessárias para criar o time.
     * O Treinador é criado fora da classe e injetado pelo parâmetro.
     */
    public Time(String nome, Treinador treinador){
        this.nome=nome;
        this.treinador=treinador;
    }

}
