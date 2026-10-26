package br.edu.unicesumar;

public class Aluno {
    private String nome;
    private int ra;

        public Aluno(int ra, String nome) {
            this.nome = nome;
            this.ra = ra;

    }

       public int getRa() {
        return ra;

    }public void setRa(int ra) {
        this.ra = ra;

    }public String getNome() {
        return nome;

    }public void setNome(String nome) {
        this.nome = nome;
    }
}
