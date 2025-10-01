package Exercicio5;

public class FigurasGeometricas {
    private double lado;
    private double base;
    private double altura;
    private double xc;
    private double yc;
    private double raio;

    public FigurasGeometricas(double lado) {
        this.lado = lado;
    }

    public FigurasGeometricas(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public FigurasGeometricas(double raio, double xcentro, double ycentro) {
        this.raio = raio;
        this.xc = xcentro;
        this.yc = ycentro;
    }

    public double areaQuadrado() {
        return lado = lado * lado;
    }

    public double areaTriangulo() {
        return (base * altura) / 2;
    }

    public double areaCirculo() {
        return Math.PI * raio * raio;
    }

    public String exibeQuadrado() {
        return "A área do quadrado de lado = " + lado + " vale: " + areaQuadrado();
    }

    public String exibeTriangulo() {
        return "A área do triângulo de base = " + base + " e de altura = " + altura + " vale: " + areaTriangulo();
    }

    public String exibeCirculo() {
        return "A área do círculo de centro (" + xc + ", " + yc + ") e de raio = " + raio + " vale: " + areaCirculo();
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getXc() {
        return xc;
    }

    public void setXc(double xc) {
        this.xc = xc;
    }

    public double getYc() {
        return yc;
    }

    public void setYc(double yc) {
        this.yc = yc;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
}