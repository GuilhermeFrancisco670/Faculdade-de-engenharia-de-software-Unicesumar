package br.edu.unicesumar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Scanner único compartilhado por todos os exercícios
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========== TRATAMENTO DE EXCEÇÕES ==========\n");

        System.out.println(">>> Exercício 1: Divisão Segura <<<");
        exercicio1DivisaoSegura();

        System.out.println("\n>>> Exercício 2: Cadastro de Produtos <<<");
        exercicio2CadastroProduto();

        System.out.println("\n>>> Exercício 3: Conversão de Texto para Número <<<");
        exercicio3ConversaoTextoNumero();

        System.out.println("\n>>> Exercício 5: Cadastro com Múltiplas Exceções <<<");
        exercicio5CadastroPessoa();

        scanner.close();
        System.out.println("\n========== FIM ==========");
    }

    // =========================================================
    // EXERCÍCIO 1: DIVISÃO SEGURA
    // Trata InputMismatchException (texto no lugar de int) e
    // ArithmeticException (divisão por zero), com retry.
    // =========================================================
    private static void exercicio1DivisaoSegura() {
        int numero1 = lerInteiroComRetry("Informe o 1º número inteiro: ");
        int numero2;
        int resultado;

        while (true) {
            numero2 = lerInteiroComRetry("Informe o 2º número inteiro: ");
            try {
                resultado = numero1 / numero2; // pode lançar ArithmeticException
                break;                         // sem exceção → sai do loop
            } catch (ArithmeticException e) {
                System.out.println("❌ Erro: não é possível dividir por zero. Tente novamente.");
            }
        }

        System.out.println("Resultado: " + numero1 + " / " + numero2 + " = " + resultado);
    }

    // =========================================================
    // EXERCÍCIO 2: CADASTRO DE PRODUTO COM VALIDAÇÃO DE PREÇO
    // - Lança IllegalArgumentException se preço < 0 (regra do enunciado)
    // - Também trata InputMismatchException caso o usuário digite
    //   algo que não seja número no preço (robustez extra)
    // =========================================================
    private static void exercicio2CadastroProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        double preco = 0;
        boolean precoValido = false;

        while (!precoValido) {
            try {
                System.out.print("Preço do produto: ");
                preco = scanner.nextDouble();
                scanner.nextLine(); // limpa o \n pendente

                // Regra do exercício: preço não pode ser negativo
                if (preco < 0) {
                    throw new IllegalArgumentException("O preço não pode ser negativo.");
                }
                precoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida: digite um número para o preço.");
                scanner.nextLine(); // descarta a linha inválida
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        System.out.println("✅ Produto cadastrado: " + nome + " — R$ " + preco);
    }

    // =========================================================
    // EXERCÍCIO 3: CONVERSÃO DE TEXTO PARA NÚMERO
    // Lê uma String com nextLine() e converte com Integer.parseInt().
    // Se o texto não for um inteiro válido, NumberFormatException é
    // lançada e capturada; o usuário tenta novamente.
    // =========================================================
    private static void exercicio3ConversaoTextoNumero() {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Digite um número inteiro (como texto): ");
            String entrada = scanner.nextLine();
            try {
                numero = Integer.parseInt(entrada); // pode lançar NumberFormatException
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, insira um número inteiro.");
            }
        }

        System.out.println("✅ Número convertido com sucesso: " + numero);
    }

    // =========================================================
    // EXERCÍCIO 5: CADASTRO COM MÚLTIPLAS EXCEÇÕES
    // - InputMismatchException: idade digitada como texto/símbolo
    // - IllegalArgumentException: idade negativa (lançada por nós)
    // O nome (String) nunca lança exceção, então só a idade é repetida.
    // =========================================================
    private static void exercicio5CadastroPessoa() {
        System.out.print("Nome da pessoa: ");
        String nome = scanner.nextLine();

        int idade = 0;
        boolean idadeValida = false;

        while (!idadeValida) {
            try {
                System.out.print("Idade: ");
                idade = scanner.nextInt();
                scanner.nextLine(); // limpa o \n pendente

                if (idade < 0) {
                    throw new IllegalArgumentException("Idade não pode ser negativa.");
                }
                idadeValida = true;
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida: digite um número inteiro para a idade.");
                scanner.nextLine(); // descarta a linha inválida
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        System.out.println("✅ Pessoa cadastrada: " + nome + " — " + idade + " anos");
    }

    // =========================================================
    // MÉTODO AUXILIAR: ler inteiro com retry (usado no Exercício 1)
    // =========================================================
    private static int lerInteiroComRetry(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // limpa o \n pendente
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida: digite apenas números inteiros.");
                scanner.nextLine(); // descarta a linha inválida
            }
        }
    }
}