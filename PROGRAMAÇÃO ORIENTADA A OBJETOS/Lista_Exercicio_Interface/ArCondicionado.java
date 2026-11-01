package br.edu.unicesumar;

public class ArCondicionado implements DispositivoEletronico{
    @Override
    public void ligar() {
        System.out.println( "Ar condicionado Ligado. \n");
    }

    @Override
    public void desligar() {
        System.out.println( "Ar condicionado Desligado. \n");

    }
}
