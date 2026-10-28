package br.edu.unicesumar;

public class ContaBancaria {
    private String titular;
    private Double saldo;
    private String numeroConta;

    // Construtor agora exige os três parâmetros iniciais
    public ContaBancaria(String titular, double saldo, String numeroConta){
        this.titular = titular;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
    }

    public String getTitular(){
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    // Renomeado para getNumeroConta para seguir a convenção do Java
    public String getNumeroConta(){
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void deposita(double valor){
        if(valor > 0){
            saldo += valor;
            // Corrigido para %.2f para formatar variáveis double adequadamente
            System.out.printf("Depósito de R$ %.2f realizado com sucesso \n", valor);
        } else{
            System.out.print("Erro: o valor do depósito deve ser positivo \n");
        }
    }

    public void sacar(double valor) {
        // Verifica se o valor de saque é positivo e se há saldo suficiente
        if(valor > 0 && valor <= saldo){
            saldo -= valor;
            // Removidos os erros de digitação e a anulação do System.out
            System.out.printf("Saque de R$ %.2f realizado com sucesso \n", valor);
        } else{
            System.out.print("Erro: saque invalido, verifique o saldo \n");
        }
    }
}