package br.com.reuseplus.app;

import br.com.reuseplus.domain.Doador;
import br.com.reuseplus.domain.Instituicao;
import br.com.reuseplus.domain.Usuario;
import br.com.reuseplus.domain.Doacao;
import br.com.reuseplus.repository.DoacaoRepository;
import br.com.reuseplus.service.DoacaoService;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        demonstrarPolimorfismo();
        DoacaoService service = new DoacaoService(new DoacaoRepository());
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                System.out.println("\n=== ReUse+ ===");
                System.out.println("1 - Cadastrar doação | 2 - Listar | 3 - Alterar status | 4 - Excluir logicamente | 0 - Sair");
                opcao = lerInteiro(scanner, "Escolha: ");
                try {
                    switch (opcao) {
                        case 1 -> {
                            long doador = lerInteiro(scanner, "ID do doador: ");
                            long item = lerInteiro(scanner, "ID do item: ");
                            System.out.println("Doação criada com ID " + service.cadastrar(doador, item));
                        }
                        case 2 -> service.listar().forEach(Main::exibir);
                        case 3 -> {
                            long id = lerInteiro(scanner, "ID: ");
                            System.out.print("Novo status: ");
                            service.alterarStatus(id, scanner.nextLine());
                            System.out.println("Status atualizado.");
                        }
                        case 4 -> {
                            long id = lerInteiro(scanner, "ID: ");
                            service.excluir(id);
                            System.out.println("Doação cancelada logicamente.");
                        }
                        case 0 -> System.out.println("Até logo.");
                        default -> System.out.println("Opção inválida.");
                    }
                } catch (SQLException | IllegalArgumentException e) {
                    System.out.println("Operação não concluída: " + e.getMessage());
                }
            } while (opcao != 0);
        }
    }

    private static void demonstrarPolimorfismo() {
        Usuario[] usuarios = {
            new Doador(null, "Doador demonstrativo", "doador@reuse.local", null),
            new Instituicao(null, "Instituição demonstrativa", "instituicao@reuse.local", null, "Instituto ReUse", "Não informado")
        };
        for (Usuario usuario : usuarios) System.out.println(usuario.descreverAtuacao());
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        int valor = Integer.parseInt(scanner.nextLine());
        return valor;
    }

    private static void exibir(Doacao d) {
        System.out.printf("#%d | doador=%d | item=%d | status=%s | data=%s%n", d.getId(), d.getDoadorId(), d.getItemId(), d.getStatus(), d.getDataCadastro());
    }
}
