package br.edu.unicesumar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ===== Exercício 1: Contador de Livros =====
        System.out.println(" Exercício 1: Contador de Livros \n ");
        new Livro();
        new Livro();
        new Livro();
        System.out.println("Livros criados: " + Livro.getContadorDeLivros());

        // ===== Exercícios 2 e 3: Imposto Compartilhado =====
        System.out.println(" Exercício 3: Imposto Compartilhado \n");
        Produto p1 = new Produto(100.00);
        Produto p2 = new Produto(200.00);

        System.out.printf("P1 com imposto (5%%): R$ %.2f%n", p1.getPrecoComImposto());
        System.out.printf("P2 com imposto (5%%): R$ %.2f%n", p2.getPrecoComImposto());

        Produto.setTaxaImposto(0.10); // altera para todos

        System.out.println("--- Após taxa = 10% ---");
        System.out.printf("P1 com imposto: R$ %.2f%n", p1.getPrecoComImposto());
        System.out.printf("P2 com imposto: R$ %.2f%n", p2.getPrecoComImposto());

        // ===== Exercício 6: Constantes =====
        System.out.println(" Exercício 6: Configuração do Sistema \n");
        System.out.println("Tentativas de login: " + ConfiguracaoSistema.NUMERO_MAXIMO_LOGIN_TENTATIVAS);
        System.out.println("Blog: " + ConfiguracaoSistema.URL_BLOG_DA_EMPRESA);

        // ===== Exercício 8: Validação de Email =====
        System.out.println(" Exercício 8: Validação de Email \n");
        Usuario u = new Usuario();
        u.setEmail("usuario@dominio.com");
        System.out.println("Email atual: " + u.getEmail());
        u.setEmail("email-invalido");
        System.out.println("Email atual: " + u.getEmail());

        // ===== Exercício 9: Maior número do array =====
        System.out.println(" Exercício 9: Maior número \n");
        int[] numeros = {3, 17, 8, 42, 11, 5};
        System.out.println("Maior: " + ArrayUtil.encontrarMaiorNumero(numeros));

        // ===== Leitura interativa de e-mail =====
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um e-mail para validar: \n");
        String email = scanner.nextLine();
        System.out.println("Válido? " + ValidacaoUtil.isEmailValido(email));
        scanner.close();
    }
}