import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        InterfaceConta contaPoupanca = new ContaPoupanca();
            InterfaceConta contaCorrente = null;
        GeradorExtratos geradorExtratos = new GeradorExtratos();

        int opcaoPrincipal;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Conta Poupanca");
            System.out.println("2 - Conta Corrente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcaoPrincipal = sc.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    menuConta(sc, contaPoupanca, geradorExtratos);
                    break;
                case 2:                    
                        if (contaCorrente == null) {
                            System.out.println("Qual e a porcentagem da taxa?");
                            double taxa = sc.nextDouble();
                            contaCorrente = new ContaCorrente(taxa);
                        }
                        menuConta(sc, contaCorrente, geradorExtratos);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcaoPrincipal != 0);

        sc.close();
    }

    private static void menuConta(Scanner sc, InterfaceConta conta, GeradorExtratos geradorExtratos) {
        int opcaoConta;

        do {
            System.out.println("\n--- Menu da Conta ---");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Ver saldo");
            System.out.println("4 - Exibir extrato");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");
            opcaoConta = sc.nextInt();

            switch (opcaoConta) {
                case 1:
                    System.out.print("Valor para deposito: ");
                    conta.depositar(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Valor para saque: ");
                    conta.sacar(sc.nextDouble());
                    break;
                case 3:
                    System.out.println("Saldo atual: R$ " + String.format("%.2f", conta.getSaldo()));
                    break;
                case 4:
                    System.out.println(geradorExtratos.exibeExtrato(conta));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcaoConta != 0);

    }
}
