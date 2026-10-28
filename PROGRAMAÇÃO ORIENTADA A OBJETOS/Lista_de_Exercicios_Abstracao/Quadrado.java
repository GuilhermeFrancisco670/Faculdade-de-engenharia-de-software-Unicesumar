package br.edu.unicesumar;

public class Quadrado extends FormaGeometrica{

    public Quadrado(double area, double perimetro) {
        super(area, perimetro);
    }
    private double lado = 3;
    private double y;

    @Override
    public double calcularArea(){

        return this.area + lado * lado;
        //System.out.println("A area do Quadrado é " + this.area + lado * lado);
    }
}
