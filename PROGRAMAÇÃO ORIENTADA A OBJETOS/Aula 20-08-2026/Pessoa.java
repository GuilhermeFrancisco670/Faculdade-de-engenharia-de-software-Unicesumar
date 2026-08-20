package br.edu.unicesumar;

/**
 * Representa uma pessoa que pode adotar um cachorro.
 */
public class Pessoa {

    // Nome privado: o estado interno não é exposto diretamente.
    private String nome;

    // A pessoa guarda uma referência para o cachorro adotado.
    // Antes de uma adoção, essa referência vale null.
    private Cachorro pet;

    /**
     * Construtor que recebe o nome da pessoa.
     * Como nenhum pet é informado, pet permanece null inicialmente.
     */
    public Pessoa(String nome){
        this.nome = nome;
    }

    /**
     * Associa um cachorro a esta pessoa.
     * O cachorro foi criado fora da classe e chega pelo parâmetro.
     */
    public void adotar(Cachorro pet){
        // Guarda a referência do cachorro no atributo da pessoa atual.
        this.pet=pet;
    }

    /**
     * Getter do pet adotado.
     * O retorno pode ser null se nenhuma adoção tiver acontecido.
     */
    public Cachorro getPet() {
        return pet;
    }
}
