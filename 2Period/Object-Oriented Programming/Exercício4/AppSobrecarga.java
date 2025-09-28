import java.util.Scanner;

public class AppSobrecarga {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o raio: ");
                    double raio = sc.nextDouble();
                    System.out.print("Digite x do centro: ");
                    double x = sc.nextDouble();
                    System.out.print("Digite y do centro: ");
                    double y = sc.nextDouble();

                    Circulo c = new Circulo(raio, x, y);
                    c.exibe();
                }
                case 2 -> {
                    System.out.print("Digite a base: ");
                    double base = sc.nextDouble();
                    System.out.print("Digite a altura: ");
                    double altura = sc.nextDouble();

                    Triangulo t = new Triangulo(base, altura);
                    t.exibe();
                }
                case 3 -> {
                    System.out.print("Digite o lado: ");
                    double lado = sc.nextDouble();

                    Quadrado q = new Quadrado(lado);
                    q.exibe();
                }
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 4);

        sc.close();
    }
    private static void exibirMenu() {
        System.out.println("FIGURAS GEOMÉTRICAS");
        System.out.println("1 - Círculo");
        System.out.println("2 - Triângulo");
        System.out.println("3 - Quadrado");
        System.out.println("4 - SAIR");
    }

}
    