package br.edu.unicesumar;

public class Funcionario {
    // Atributos privados garantem que não sejam alterados diretamente de fora da classe
    private String nome;
    private String cargo;
    private double salario;

    // Método para recuperar o valor do salário
    public double getSalario() {
        return salario;
    }

    // Método para alterar o salário contendo a regra de negócio (validação)
    public void setSalario(double salario) {
        // Verifica se o valor recebido é maior ou igual a zero
        if(salario >= 0) {
            // Se for válido, atualiza o atributo da classe
            this.salario = salario;
            System.out.printf("Salario alterado com Sucesso \n");
        } else {
            // Se for negativo, barra a alteração e avisa o usuário
            System.out.printf("Erro: salario não pode ser menor do que zero \n");
        }
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para cargo
    public String getCargo() {
        return cargo;
    }

    // Setter para cargo
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}