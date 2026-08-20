package br.edu.unicesumar;

/**
 * Ponto de entrada da aplicação.
 * O método main é onde a JVM começa a executar este exemplo.
 */
public class Main {
    public static void main(String[] args) {

        // Exemplo de encapsulamento e validação das notas de um Aluno:
        // Aluno pedro = new Aluno("Pedro Lucas");
        // O construtor recebe o nome e cria o objeto Pedro.
        // pedro.setNotaX(1, 4.66);
        // pedro.setNotaX(2, 5.56);
        // As notas são alteradas por uma operação pública que valida os valores.
        // pedro.exibirDados();

        // O construtor de Pessoa recebe o nome e inicializa uma nova pessoa.
        Pessoa lucas = new Pessoa("Lucas Felipe");

        // Como Lucas ainda não adotou um cachorro, o pet começa com valor null.
        // Imprimir null é seguro; chamar lucas.getPet().getNome() neste momento não seria.
        System.out.println(lucas.getPet());

        // Exemplo de relacionamento entre Pessoa e Cachorro:
        // Cachorro bob = new Cachorro("Bob");
        // O cachorro é criado separadamente da pessoa.
        // lucas.adotar(bob);
        // O método adotar associa a referência de bob ao atributo pet de Lucas.
        // System.out.printf("Nome do Cachorro: %s", lucas.getPet().getNome());

        // Exemplo de relacionamento entre Medico e Paciente:
        // Paciente joao = new Paciente();
        // O construtor sem argumentos inicializa exames com uma String vazia.
        // Medico julia = new Medico("Julia");
        // julia.atender(joao);
        // julia.atender(joao);
        // O médico recebe o paciente como parâmetro e altera seus exames.
        // System.out.printf("Exames do Paciente: %s", joao.exames);

        // Exemplo de associação entre Computador e Monitor:
        // Monitor monitorAcer = new Monitor();
        // Computador lenovo = new Computador();
        // O monitor existe fora do computador e depois é conectado a ele.
        // lenovo.conectarMonitor(monitorAcer);

        // Exemplo de associação entre Time e Treinador:
        // Treinador felipao = new Treinador();
        // O treinador é criado separadamente.
        // Time flamengo = new Time("Flamengo", felipao);
        // O construtor do Time recebe tanto o nome quanto o treinador.

        // Exemplo de composição entre Casa e Quarto:
        // A Casa recebe o endereço e cria seu Quarto internamente.
        Casa casa1 = new Casa("Rua teste");

        // O getter devolve a referência do quarto principal.
        // Como Quarto não sobrescreve toString(), a saída será o identificador padrão da JVM.
        System.out.println(casa1.getQuartoPrincipal());

    }
}
