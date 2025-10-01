package Exercicio5;

import java.util.Scanner;

public class App {

    public static void menu() {
        System.out.println("    FIGURA  ");
        System.out.println("1 - Círculo");
        System.out.println("2 - Triângulo");
        System.out.println("3 - Quadrado");
        System.out.println("4 - Sair");
        System.out.println("Digite a opção: ");
    }

    public static FigurasGeometricas le(int opcao) {
        Scanner sc = new Scanner(System.in);
        switch (opcao) {
            case 1:
                System.out.println("Digite o valor do raio do círculo: ");
                double raio = sc.nextDouble();
                System.out.println("Digite o valor de X do círculo: ");
                double xc = sc.nextDouble();
                System.out.println("Digite o valor de Y do círculo: ");
                double yc = sc.nextDouble();
                FigurasGeometricas figCirculo = new FigurasGeometricas(raio, xc, yc);
                return figCirculo;
            case 2:
                System.out.println("Digite o valor da base do triângulo: ");
                double base = sc.nextDouble();
                System.out.println("Digite o valor da altura do triângulo: ");
                double altura = sc.nextDouble();
                FigurasGeometricas figTriangulo = new FigurasGeometricas(base, altura);
                return figTriangulo;
            case 3:
                System.out.println("Digite o valor do lado do quadrado: ");
                double lado = sc.nextDouble();
                FigurasGeometricas figQuadrado = new FigurasGeometricas(lado);
                return figQuadrado;
            default:
                return null;

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

    }
}
