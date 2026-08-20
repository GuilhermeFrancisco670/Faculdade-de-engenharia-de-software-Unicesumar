package br.edu.unicesumar;

/**
 * Representa uma casa que possui um quarto principal.
 *
 * O relacionamento com Quarto é usado aqui como exemplo de composição:
 * a Casa cria o Quarto internamente durante sua construção.
 */
public class Casa {

    // Endereço encapsulado; nenhuma classe externa acessa o campo diretamente.
    private String endereco;

    // A Casa mantém uma referência para um objeto Quarto.
    private Quarto quartoPrincipal;

    /**
     * Construtor da Casa.
     * Recebe o endereço e cria automaticamente o quarto principal.
     */
    public Casa(String endereco){
        // Armazena o endereço recebido no atributo da nova casa.
        this.endereco=endereco;

        // new Quarto() cria um objeto e guarda sua referência na Casa.
        this.quartoPrincipal=new Quarto();
    }

    /**
     * Getter que permite consultar o quarto principal.
     * O método devolve a referência do objeto Quarto, não uma cópia dele.
     */
    public Quarto getQuartoPrincipal() {
        return quartoPrincipal;
    }
}
