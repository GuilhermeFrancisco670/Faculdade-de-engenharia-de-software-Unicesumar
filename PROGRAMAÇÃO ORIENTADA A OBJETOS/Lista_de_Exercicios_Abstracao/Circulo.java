package br.edu.unicesumar;

public class Circulo extends FormaGeometrica{

    private double raio;

    public Circulo(double area, double perimetro, double raio) {
        super(area, perimetro);
        this.raio = raio;
    }

    @Override
    public double calcularArea(){
        // Math.PI * raio * raio = area;
        return Math.PI * raio * raio;
        //System.out.println("A area do Circulo é: " + area);
    }
}