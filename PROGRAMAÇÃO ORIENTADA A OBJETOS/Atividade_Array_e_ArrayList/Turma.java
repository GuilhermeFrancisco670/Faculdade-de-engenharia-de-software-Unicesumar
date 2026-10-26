package br.edu.unicesumar;

public class Turma {
    private Aluno[] alunos;
    private int proximaPosicaoLivre;

    public Turma() {
        this.alunos = new Aluno[20];
        this.proximaPosicaoLivre = 0;
    }

    public void matricularAluno(Aluno aluno){
        // verificar se a espaço na turma
        if (this.proximaPosicaoLivre < this.alunos.length){

            // colocar o aluno nesse espaço
            this.alunos[this.proximaPosicaoLivre] = aluno;

            // prepara para a proxima posição livre
            this.proximaPosicaoLivre++;

            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("ERRO: turma cheia!");
        }
    }

    public void listarAlunos(){
        System.out.println("--- Alunos da Turma ---");

        // o loop do for vai até a proxima posição livre disponivel
        for( int i =0; i < this.proximaPosicaoLivre; i++){
            System.out.println("-" + this.alunos[i].getNome());
        }
    }

    public Aluno buscarAlunoPorRa(int ra){
        // Percorre SOMENTE as posições preenchidas (até proximaPosicaoLivre)
        for ( int i = 0; i < this.alunos.length; i++){
            if(this.alunos[i].getRa() == ra){
                return this.alunos[i];  /* achou! retorna o objeto */
            }
        }
        return null;
    }

    public Aluno[] getAlunos() {

        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {

        this.alunos = alunos;

    }

    public int getProximaPosicaoLivre() {

        return proximaPosicaoLivre;

    }

    public void setProximaPosicaoLivre(int proximaPosicaoLivre) {

        this.proximaPosicaoLivre = proximaPosicaoLivre;

    }
}
