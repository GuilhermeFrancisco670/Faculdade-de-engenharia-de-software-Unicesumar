package br.edu.unicesumar;

/**
 * Representa um aluno e suas duas notas.
 *
 * Esta classe é um exemplo de encapsulamento: os dados ficam privados e a
 * alteração das notas passa por uma regra de validação.
 */
public class Aluno {

    // private impede que outras classes alterem o nome diretamente.
    private String nome;

    // As notas também ficam protegidas dentro da classe.
    private double nota1;
    private double nota2;

    /**
     * Método construtor: é executado quando usamos new Aluno("...").
     * O parâmetro recebido inicializa o atributo nome do novo objeto.
     */
    public Aluno(String nome){
        // this.nome é o atributo; nome é o parâmetro do construtor.
        this.nome=nome;
    }

    /**
     * Getter da primeira nota.
     * Ele permite consultar um atributo privado sem liberar sua alteração.
     */
    public double getNota1() {
        return nota1;
    }

    /** Getter da segunda nota. */
    public double getNota2() {
        return nota2;
    }

    /**
     * Setter privado da primeira nota.
     * Como é private, somente a própria classe pode chamá-lo.
     * Isso obriga a alteração a passar por setNotaX, que valida os dados.
     */
    private void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    /** Setter privado da segunda nota; segue a mesma ideia do setter anterior. */
    private void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    /**
     * Altera a nota do bimestre informado.
     * O método público funciona como uma porta controlada para o estado do objeto.
     */
    public void setNotaX(int bimestre, double nota){

        // A nota só é aceita se estiver no intervalo de 0 a 10, inclusive.
        if (nota >= 0 && nota<=10){

            // Se o bimestre for 1, a alteração será direcionada à nota1.
            if (bimestre == 1){
                setNota1(nota);

            // Se o bimestre for 2, a alteração será direcionada à nota2.
            }else if (bimestre == 2){
                setNota2(nota);

            // Qualquer outro número de bimestre é rejeitado.
            }else {
                System.out.println("Bimestre inválido!");
            }

        // Se a nota sair do intervalo permitido, ela não é armazenada.
        }else {
            System.out.println("Nota invalida!");
        }

    }

    /**
     * Exibe o estado principal do aluno no console.
     * %s recebe o nome e cada %f recebe uma nota.
     */
    public void exibirDados(){
        System.out.printf("Aluno: %s\n", nome);
        System.out.println("Notas");
        // Observação: a mensagem original contém "Nota: 2:"; o ideal seria "Nota 2:".
        System.out.printf("Nota 1: %f | Nota: 2: %f\n", nota1, nota2);
    }

}
