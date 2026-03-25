import java.util.Scanner;

public class SistemaBancario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializa as contas no início
        ContaBancaria cb = new ContaBancaria("Titular Comum", 1500.0);
        ContaCorrente cc = new ContaCorrente("João Silva", 1000.0, 500.0);
        ContaCorrenteEmpresarial cce = new ContaCorrenteEmpresarial("Empresa X", 5000.0, 2000.0, 100.0);
        ContaCorrentePremium ccp = new ContaCorrentePremium("Maria Santos", 10000.0, 1000.0, 2.5);
        ContaPoupanca cp = new ContaPoupanca("Pedro Costa", 2000.0, 0.5);
        ContaPoupancaEstudantil cpe = new ContaPoupancaEstudantil("Ana Oliveira", 500.0, 0.3, 0.5);

        boolean continuar = true;

        while (continuar) {
            System.out.println(menu());
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        menuContaBancaria(sc, cb);
                        break;
                    case 2:
                        menuContaCorrente(sc, cc);
                        break;
                    case 3:
                        menuContaCorrenteEmpresarial(sc, cce);
                        break;
                    case 4:
                        menuContaCorrentePremium(sc, ccp);
                        break;
                    case 5:
                        menuContaPoupanca(sc, cp);
                        break;
                    case 6:
                        menuContaPoupancaEstudantil(sc, cpe);
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Encerrando...");
                        break;
                    default:
                        throw new MinhasExcecoes("Opção inválida!");
                }
            } catch (MinhasExcecoes e) {
                System.out.println("✗ Erro: " + e.getMessage());
            }

            System.out.println();
        }

        sc.close();
    }

    public static String menu() {
        return "Qual o tipo de conta bancária?\n" +
                "1- Conta Bancária\n" +
                "2- Conta Corrente\n" +
                "3- Conta Corrente Empresarial\n" +
                "4- Conta Corrente Premium\n" +
                "5- Conta Poupança\n" +
                "6- Conta Poupança Estudantil\n" +
                "0- Sair";
    }

    // ===== SUB-MENUS: OPERAÇÕES DE CADA CLASSE =====

    private static void menuContaBancaria(Scanner sc, ContaBancaria cb) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA BANCÁRIA (CLASSE PAI) ---");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Ver saldo");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (cb.sacar(valor)) {
                        System.out.println("✓ Saque realizado! " + cb.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    cb.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + cb.exibeSaldo());
                    break;
                case 3:
                    System.out.println(cb.exibeSaldo());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

    private static void menuContaCorrente(Scanner sc, ContaCorrente cc) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA CORRENTE ---");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Ver saldo");
            System.out.println("4- Ver limite cheque especial");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (cc.sacar(valor)) {
                        System.out.println("✓ Saque realizado! " + cc.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    cc.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + cc.exibeSaldo());
                    break;
                case 3:
                    System.out.println(cc.exibeSaldo());
                    break;
                case 4:
                    System.out.println(cc.exibeLimiteChequeEspecial());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

    private static void menuContaCorrenteEmpresarial(Scanner sc, ContaCorrenteEmpresarial cce) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA CORRENTE EMPRESARIAL ---");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Ver saldo");
            System.out.println("4- Ver limite cheque especial");
            System.out.println("5- Ver taxa administrativa");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (cce.sacar(valor)) {
                        System.out.println("✓ Saque realizado! " + cce.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    cce.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + cce.exibeSaldo());
                    break;
                case 3:
                    System.out.println(cce.exibeSaldo());
                    break;
                case 4:
                    System.out.println(cce.exibeLimiteChequeEspecial());
                    break;
                case 5:
                    System.out.println(cce.exibeTaxaJurosEmprestimo());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

    private static void menuContaCorrentePremium(Scanner sc, ContaCorrentePremium ccp) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA CORRENTE PREMIUM ---");
            System.out.println("1- Sacar (com cashback)");
            System.out.println("2- Depositar");
            System.out.println("3- Ver saldo");
            System.out.println("4- Ver limite cheque especial");
            System.out.println("5- Ver benefício premium");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (ccp.sacar(valor)) {
                        System.out.println("✓ Saque realizado com cashback! " + ccp.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    ccp.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + ccp.exibeSaldo());
                    break;
                case 3:
                    System.out.println(ccp.exibeSaldo());
                    break;
                case 4:
                    System.out.println(ccp.exibeLimiteChequeEspecial());
                    break;
                case 5:
                    System.out.println(ccp.exibeBeneficioPremium());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

    private static void menuContaPoupanca(Scanner sc, ContaPoupanca cp) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA POUPANÇA ---");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Aplicar rendimento");
            System.out.println("4- Ver saldo");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (cp.sacar(valor)) {
                        System.out.println("✓ Saque realizado! " + cp.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    cp.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + cp.exibeSaldo());
                    break;
                case 3:
                    cp.aplicarRendimento();
                    System.out.println("✓ Rendimento aplicado! " + cp.exibeSaldo());
                    break;
                case 4:
                    System.out.println(cp.exibeSaldo());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

    private static void menuContaPoupancaEstudantil(Scanner sc, ContaPoupancaEstudantil cpe) throws MinhasExcecoes {
        boolean operando = true;

        while (operando) {
            System.out.println("\n--- CONTA POUPANÇA ESTUDANTIL ---");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Aplicar rendimento");
            System.out.println("4- Ver saldo");
            System.out.println("5- Ver desconto estudantil");
            System.out.println("0- Voltar");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor para sacar: ");
                    double valor = sc.nextDouble();
                    if (cpe.sacar(valor)) {
                        System.out.println("✓ Saque realizado! " + cpe.exibeSaldo());
                    } else {
                        throw new MinhasExcecoes("Saldo insuficiente!");
                    }
                    break;
                case 2:
                    System.out.print("Valor para depositar: ");
                    double deposito = sc.nextDouble();
                    cpe.depositar(deposito);
                    System.out.println("✓ Depósito realizado! " + cpe.exibeSaldo());
                    break;
                case 3:
                    cpe.aplicarRendimento();
                    System.out.println("✓ Rendimento aplicado! " + cpe.exibeSaldo());
                    break;
                case 4:
                    System.out.println(cpe.exibeSaldo());
                    break;
                case 5:
                    System.out.println(cpe.exibeLimiteIsencao());
                    break;
                case 0:
                    operando = false;
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }
        }
    }

}