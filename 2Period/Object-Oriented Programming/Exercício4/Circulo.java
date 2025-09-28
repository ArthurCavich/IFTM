public class Circulo {
    private double raio;
    private double xcentro;
    private double ycentro;

    // Construtor com 3 parâmetros
    public Circulo(double raio, double xcentro, double ycentro) {
        this.raio = raio;
        this.xcentro = xcentro;
        this.ycentro = ycentro;
    }

    // Construtor com 2 parâmetros
    public Circulo(double xcentro, double ycentro) {
        this.raio = 0;
        this.xcentro = xcentro;
        this.ycentro = ycentro;
    }

    public double area() {
        return Math.PI * raio * raio;
    }

    public void exibe() {
        System.out.println("A área do círculo de centro (" + xcentro + ", " + ycentro +
                ") e de raio = " + raio + " vale " + area());
    }

    public double getRaio() {
        return raio;
    }

    public double getXcentro() {
        return xcentro;
    }

    public double getYcentro() {
        return ycentro;
    }
}
