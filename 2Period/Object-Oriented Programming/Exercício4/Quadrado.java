public class Quadrado {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    public double area() {
        return lado * lado;
    }

    public void exibe() {
        System.out.println("A área do quadrado de lado = " + lado + " vale " + area());
    }

    public double getLado() {
        return lado;
    }
}