package br.edu.unicesumar;

public abstract class FormaGeometrica {
    protected double area;
    protected double perimetro;

    public FormaGeometrica(double area, double perimetro) {
        this.area = 0.0;
        this.perimetro = perimetro;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    public abstract double calcularArea();
}
