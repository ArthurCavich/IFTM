public class Triangulo {
    private int a;
    private int b;
    private int c;

    public Triangulo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() { return a; }
    public void setA(int a) { this.a = a; }

    public int getB() { return b; }
    public void setB(int b) { this.b = b; }

    public int getC() { return c; }
    public void setC(int c) { this.c = c; }

    public String tipoTriangulo() {
        if (a == b && b == c) {
            return "equilátero";
        } else if (a == b || a == c || b == c) {
            return "isósceles";
        } else {
            return "escaleno";
        }
    }
}
