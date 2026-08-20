package br.edu.unicesumar;

/**
 * Representa um cachorro que pode ser associado a uma Pessoa.
 */
public class Cachorro {

    // O nome é privado: o objeto controla como esse dado é acessado.
    private String nome;

    /**
     * Construtor que cria um cachorro já recebendo seu nome.
     */
    public Cachorro(String nome){
        // this.nome representa o atributo da instância atual.
        this.nome=nome;
    }

    /**
     * Getter: permite que outras classes consultem o nome sem acessar o atributo diretamente.
     */
    public String getNome() {
        return nome;
    }
}
