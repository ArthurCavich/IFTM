public class App {

    public static void main(String[] args) {
        // SEM POLIMORFISMO
        // Circulo circulo = new Circulo();
        // Triangulo triangulo = new Triangulo();
        // Quadrado quadrado = new Quadrado();

        // System.out.println(circulo.pegaTexto());
        // System.out.println(triangulo.pegaTexto());
        // System.out.println(quadrado.pegaTexto());

        Figura2D fig = Circulo.criar();
        System.out.println(fig.pegaTexto());

        Figura2D fig2 = Quadrado.criar();
        System.out.println(fig2.pegaTexto());

        Figura2D fig3 = Triangulo.criar();
        System.out.println(fig3.pegaTexto());
    }

}
