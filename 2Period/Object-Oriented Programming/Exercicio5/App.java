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

    public static void submenu() {
        System.out.println();
        System.out.println("AÇÕES");
        System.out.println("1 - Exibir Dados");
        System.out.println("2 - Área");
        System.out.println("3 - Voltar");
        System.out.print("Digite a opção: ");
    }

    public static FigurasGeometricas le(int opcao, Scanner sc) {
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
        int opcao = 0;
        FigurasGeometricas figura = null;

        while (true) {
            System.out.println();
            menu();
            opcao = sc.nextInt();
            if (opcao == 4) {
                System.out.println("Encerrando...");
                break;
            }
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção de figura inválida.");
                continue;
            }

             figura = le(opcao, sc);
            if (figura == null) {
                System.out.println("Falha ao ler dados da figura.");
                continue;
            }

            int opSub = 0;
            do {
                submenu(); // exibe submenu antes de ler
                opSub = sc.nextInt();
                switch (opSub) {
                    case 1:
                        switch (opcao) {
                            case 1: // círculo
                                System.out.println(figura.exibeCirculo());
                                break;
                            case 2: // triângulo
                                System.out.println(figura.exibeTriangulo());
                                break;
                            case 3: // quadrado
                                System.out.println(figura.exibeQuadrado());
                                break;
                        }
                        break;
                    case 2:
                        double area = 0;
                        switch (opcao) {
                            case 1:
                                area = figura.areaCirculo();
                                break;
                            case 2:
                                area = figura.areaTriangulo();
                                break;
                            case 3:
                                area = figura.areaQuadrado();
                                break;
                        }
                        System.out.println("Área = " + area);
                        break;
                    case 3:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida no submenu.");
                }
            } while (opSub != 3);
        }

        sc.close();

    }
}
