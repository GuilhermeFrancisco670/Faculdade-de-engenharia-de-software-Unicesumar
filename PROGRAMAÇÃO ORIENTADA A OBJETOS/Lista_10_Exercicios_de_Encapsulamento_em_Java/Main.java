package br.edu.unicesumar;

public class Main {
    public static void main(String[] args){

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Guilherme");

        pessoa.setIdade(22);

        System.out.print("Exercício 1: Criando Getters e Setters Simples:");
        System.out.printf("O nome é: %s \n", pessoa.getNome());
        System.out.printf("A idade é: %d \n", pessoa.getIdade());
        System.out.printf("Exercicio 3: Criando uma conta Bancaria \n ");
        // -------------------------------------------------------------
        ContaBancaria conta = new ContaBancaria("Mariana", "123456","2");
        conta.deposita(500.0);
        conta.sacar(200.0);
        conta.sacar(400.0);
        System.out.printf(" saldo Final: %d \n", ContaBancaria.getSaldo());

    }
}