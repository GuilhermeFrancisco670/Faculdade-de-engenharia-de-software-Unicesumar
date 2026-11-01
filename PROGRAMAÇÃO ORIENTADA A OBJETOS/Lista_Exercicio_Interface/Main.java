package br.edu.unicesumar;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        /*1. O Sistema da Receita Federal (Tributáveis)
    A Receita Federal precisa de um sistema que calcule os impostos devidos sobre diferentes tipos de bens e
    ativos. Tanto uma ContaCorrente (que tem impostos sobre o saldo) quanto um SeguroDeVida (que pode ter
    tributação específica) são "tributáveis", mas são produtos financeiros completamente distintos.
        • Sua Tarefa:
        - Parte A: Crie a interface Tributavel com um método: double getValorImposto().
        - Parte B: Crie a classe ContaCorrente, com um atributo saldo, que implements Tributavel. A
        lógica do imposto é retornar 2% do saldo.
        - Parte C: Crie a classe SeguroDeVida, que implements Tributavel. A lógica do imposto é um
        valor fixo de R$ 42,00.
        - Parte D (Teste): No main, crie um objeto de cada classe e imprima o valor do imposto de cada
            um para provar que ambos cumprem o contrato Tributavel, cada um à sua maneira. */

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.getValorImposto();
        System.out.println("O valor do Imposto é: " + contaCorrente.getValorImposto() + "\n");

        SeguroDeVida segurodeVida = new SeguroDeVida();
        segurodeVida.getValorImposto();
        System.out.println("O valor do Imposto é: " + segurodeVida.getValorImposto() + "\n");

        // 2. Sistema de Automação Residencial
        System.out.println("2. Sistema de Automação Residencial \n");

        Lampada lampada = new Lampada();
        lampada.ligar();
        lampada.desligar();

        ArCondicionado arCondicionado = new ArCondicionado();
        arCondicionado.ligar();
        arCondicionado.desligar();

        //3. O Dilema do Home Office na Elotech
        System.out.println("3. O Dilema do Home Office na Elotech \n");

        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.trabalharDeCasa();

        //4. O Zoológico Virtual (Análise de Design)
        System.out.println("4. O Zoológico Virtual (Análise de Design) \n");

        /* 1) Animal sera uma classe abstrata, a relação: Leao, Tigre, Pato e Morcego
        são "é-um-tipo-de" Animal. Isso indica uma hierarquia de tipos com estado/comportamento
         comum (ex.: peso). Uma abstract class é adequada porque: permite definir atributos
         concretos compartilhados (ex.: peso) e métodos comuns; pode fornecer implementação
         parcial (ex.: lógica de alimentação) e deixar partes variáveis abstratas; expressa melhor
         a ideia de "categoria base" do que uma interface.

         2) voar vai ser uma interface, a relação é de Pato e Morcego "é-capaz-de" Voar; Leao e Tigre não.
              Isso é uma capacidade/comportamento que não pertence a toda a hierarquia de Animal. uma
              interface é melhor porque cria um contrato, quem implementa, concegue voar, sem impor uma
              única linhagem de herança; ela permite que tipos diferentes (ex.: Pato, Morcego, Aviao, Balão)
              compartilhem a mesma habilidade; isso é bom porque dai não precissamos criar classes intemediarias
              só para agrupar quem voa

        */

        // 5. Uma Prévia do Polimorfismo com Interfaces
        System.out.println("5. Uma Prévia do Polimorfismo com Interfaces \n");

        List<Tributavel>  tributos = new ArrayList<Tributavel>();
        tributos.add(contaCorrente);
        tributos.add(segurodeVida);

        for(Tributavel tributo : tributos) {
            tributo.getValorImposto();
            tributo.getSomar();
        }

        //6. Caça ao Bug: O Contrato Quebrado
        System.out.println("6. Caça ao Bug: O Contrato Quebrado  \n");

        /* Quando uma classe implementa uma interface em Java, ela assina
         um contrato obrigatório: deve implementar TODOS os métodos declarados na interface.
        * A classe RelatorioPDF implementou apenas o método imprimir(), mas esqueceu de
        *  implementar o método getCabecalho(). Isso viola o contrato da interface, gerando um erro de compilação. */

        /* o código coorigido ficaria assim:
                *  public class RelatorioPDF implements Imprimivel {

            @Override
            public void imprimir() {
                System.out.println("Imprimindo PDF...");
            }

            @Override
            public String getCabecalho() {
                return "Relatório PDF - Cabeçalho Padrão";
            }
        }
                *
                *   */

        // 7.Sistema de Exportação de Dados
        System.out.println("7. Sistema de Exportação de Dados \n");

        Usuario usuario = new Usuario();
        usuario.toCSV();

        Produto produto = new Produto();
        produto.toCSV();

        // 8. Múltiplas Capacidades: A Porta de Segurança
        System.out.println("8. Múltiplas Capacidades: A Porta de Segurança  \n");

        // 9. Desacoplamento e Flexibilidade (Conceitual)
        System.out.println("9. Desacoplamento e Flexibilidade (Conceitual) \n");

    }
}
