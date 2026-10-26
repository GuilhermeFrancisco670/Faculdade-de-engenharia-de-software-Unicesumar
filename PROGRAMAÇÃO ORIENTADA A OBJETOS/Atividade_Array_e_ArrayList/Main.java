package br.edu.unicesumar;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        Turma turma = new Turma();

        turma.matricularAluno(new Aluno(101, "ana"));
        turma.matricularAluno(new Aluno(102, "Beto"));
        turma.matricularAluno(new Aluno(103, "Carla" ));

        turma.listarAlunos();

        Aluno encontrado = turma.buscarAlunoPorRa(102);
        if (encontrado != null){
            System.out.println("Aluno " + encontrado.getNome() + "foi encontrado");
        } else{
            System.out.println("Aluno não foi encontrado.");
        }

        Aluno naoExiste = turma.buscarAlunoPorRa(980);
        //System.out.println(naoExiste == null "RA 999 não existe." : "Achou!");

    }
}
