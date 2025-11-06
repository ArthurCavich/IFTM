import java.util.Scanner;

public class App {

    // lê três inteiros do usuário e retorna como array
    public static int[] le() {
        Scanner sc = new Scanner(System.in);
        int[] lados = new int[3];
        System.out.print("Digite o lado 1: ");
        lados[0] = sc.nextInt();
        System.out.print("Digite o lado 2: ");
        lados[1] = sc.nextInt();
        System.out.print("Digite o lado 3: ");
        lados[2] = sc.nextInt();
        return lados;
    }

    // exibe o tipo do triângulo
    public static void exibe(String tipo) {
        System.out.println("Tipo do triângulo: " + tipo);
    }

    public static void main(String[] args) {
        int[] lados = le();
        int a = lados[0];
        int b = lados[1];
        int c = lados[2];

        // usa ValidaTriangulo.verifica para checar se formam triângulo
        if (!ValidaTriangulo.verifica(a, b, c)) {
            System.out.println("As medidas não formam um triângulo. Programa encerrado.");
            return;
        }

        Triangulo t = new Triangulo(a, b, c);
        String tipo = t.tipoTriangulo();
        exibe(tipo);
    }
}
