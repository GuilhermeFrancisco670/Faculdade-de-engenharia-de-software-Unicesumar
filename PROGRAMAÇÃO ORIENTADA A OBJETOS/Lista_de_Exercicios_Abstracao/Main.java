package br.edu.unicesumar;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        System.out.println("1. Refatorando a Frota da \"Mobilidade Urbana\" ");

        // Veiculo  veiculo = new Veiculo("Mercedes-Benz", "Actros");

        Carro carro = new Carro("volkswagen", "Fusca");
        System.out.println("Pedágio do Carro: R$ " + carro.calcularPedagio() + "\n");

        Moto moto = new Moto("Kawasaki ", "Ninja ZX-6R");
        System.out.println("Pedágio Moto: R$ " + moto.calcularPedagio() + "\n");

        // 2. O Contrato das Formas Geométricas no "CanvasPro"
        System.out.println("2. O Contrato das Formas Geométricas no \"CanvasPro\" \n ");

        Circulo circulo = new Circulo(1, 2,8 );
        System.out.println("A Area do Circulo é: "+ circulo.calcularArea() + "\n");

        Quadrado quadrado = new Quadrado(3,4);
        System.out.println("A Area do Quadrado é: " + quadrado.calcularArea() + "\n");

        //3. Contas no "NextGen Bank"
        System.out.println("3. Contas no \"NextGen Bank\" \n ");

        ContaPoupanca contaPoupanca = new ContaPoupanca("claudio", 250);
        contaPoupanca.setSaldo(250);
        System.out.println("A Conta poupança não possue taxas \n ");

        ContaCorrente contaCorrente = new ContaCorrente("claudio", 250);
        contaCorrente.calcularTaxas();

        //4. A Essência da Abstração (Conceitual)
        System.out.println("4. A Essência da Abstração (Conceitual) \n");

        System.out.println("Quando devo usar uma classe abstrata? \n ");
        System.out.println("abstract é para modelar conceitos incompletos que só ganham existência real nas subclasses. A “coisa real” é a classe concreta que você instancia;");
        System.out.println(" o “conceito” é a base abstrata que organiza e obriga o contrato. \n");

        //5. Caça ao Erro de Compilação (Leitura de Código)
        System.out.println("5. Caça ao Erro de Compilação (Leitura de Código) \n");

        System.out.println("Como a classe Piano herdou de InstrumentoMusical, ela tinha que implementar o método tocar(), mas em vez disso ela apenas criou um método totalmente novo chamado afinar() \n");

        //6. Justificando a Abstração (Leitura de Código)
        System.out.println("6. Justificando a Abstração (Leitura de Código) \n ");

        /*
         * A classe ProcessoJudicial precisa ser abstract porque ela declara o método
         * abstrato 'validarDocumentos()' (na linha: public abstract void validarDocumentos();).
         * No Java, se uma classe possui um ou mais métodos abstratos, ela é obrigatoriamente
         * obrigada a ser declarada como abstract. Além disso, isso impede que a classe seja
         * instanciada diretamente, garantindo que apenas suas subclasses concretas (que
         * implementarão as regras específicas de validação) possam ser criadas.
         */

         System.out.println("A classe ProcessoJudicial precisa ser abstract porque ela declara o método");
        System.out.println("abstrato 'validarDocumentos()' (na linha: public abstract void validarDocumentos();).");
        System.out.println("No Java, se uma classe possui um ou mais métodos abstratos, ela é obrigatoriamente");
        System.out.println("obrigada a ser declarada como abstract. Além disso, isso impede que a classe seja");
        System.out.println("instanciada diretamente, garantindo que apenas suas subclasses concretas (que");
        System.out.println("implementarão as regras específicas de validação) possam ser criadas.");

        //7. Decisão de Design: Sistema de E-commerce
        System.out.println("7. Decisão de Design: Sistema de E-commerce \n");

        /* Deve ser abstrato, a classe produto vai agrupar atributos como: id, nome, estoque, preço. e os
        comportamentos que todos os itens compartilham. Ao declarar a classe como abstract, isso impede que o sistema
        crie objetos incompletos e garante que apenas as subclasses concretas que possuem as especificidades necessárias
        para a lógica de negócio possam ser instanciadas.

        Permitir o cadastro de um produto genérico quebraria regras de negócio e causaria falhas no sistema como
        Inconsistência de Dados e erros de calculos
        */

        System.out.println("Deve ser abstrato, a classe produto vai agrupar atributos como: id, nome, estoque, preço. e os");
        System.out.println("comportamentos que todos os itens compartilham. Ao declarar a classe como abstract, isso impede que o sistema");
        System.out.println("crie objetos incompletos e garante que apenas as subclasses concretas que possuem as especificidades necessárias");
        System.out.println("para a lógica de negócio possam ser instanciadas. \n");
        System.out.println("Permitir o cadastro de um produto genérico quebraria regras de negócio e causaria falhas no sistema como");
        System.out.println("Inconsistência de Dados e erros de calculos \n");

        //8. Hierarquia de Ingressos para Eventos
        IngressoVIP ingressoVIP = new IngressoVIP(20);
        ingressoVIP.imprimirValor();

        IngressoPista ingressoPista = new IngressoPista(20);
        ingressoPista.imprimirValor();

    }
}
