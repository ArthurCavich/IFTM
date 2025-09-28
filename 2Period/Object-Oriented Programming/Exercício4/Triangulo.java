public class Triangulo {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double area() {
        return (base * altura) / 2;
    }

    public void exibe() {
        System.out.println("A área do triângulo de base = " + base +
                " e de altura = " + altura + " vale " + area());
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
}
