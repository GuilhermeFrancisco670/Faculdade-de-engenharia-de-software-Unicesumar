package br.edu.unicesumar;

public class Lampada implements DispositivoEletronico{
    @Override
    public void ligar() {
        System.out.println( "Lâmpada ligada! \n");
    }

    @Override
    public void desligar() {
        System.out.println( "Lâmpada Desligada! \n");

    }
}
