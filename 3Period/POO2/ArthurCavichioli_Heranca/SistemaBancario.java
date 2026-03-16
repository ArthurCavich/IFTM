import java.util.Scanner;

public class SistemaBancario {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        ContaBancaria contaBancaria = new ContaBancaria("Fulano", 1000.0);
        ContaCorrente contaCorrente = new ContaCorrente("Ciclano", 1200.0, 500.0);
        ContaPoupanca contaPoupanca = new ContaPoupanca("Beltrano", 800.0, 1.5);
        ContaCorrentePremium contaCorrentePremium = new ContaCorrentePremium("Marina", 2000.0, 1000.0, 2.0);
        ContaCorrenteEmpresarial contaCorrenteEmpresarial = new ContaCorrenteEmpresarial("Empresa X", 10000.0, 5000.0, 3.0);
        ContaPoupancaEstudantil contaPoupancaEstudantil = new ContaPoupancaEstudantil("Ana", 600.0, 1.0, 150.0);

        int opcao;

        do {
            System.out.println("\n===== SISTEMA BANCARIO =====");
            System.out.println("1. Conta Bancaria");
            System.out.println("2. Conta Corrente");
            System.out.println("3. Conta Poupanca");
            System.out.println("4. Conta Corrente Premium");
            System.out.println("5. Conta Corrente Empresarial");
            System.out.println("6. Conta Poupanca Estudantil");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    menuContaBancaria(contaBancaria);
                    break;
                case 2:
                    menuContaCorrente(contaCorrente);
                    break;
                case 3:
                    menuContaPoupanca(contaPoupanca);
                    break;
                case 4:
                    menuContaCorrentePremium(contaCorrentePremium);
                    break;
                case 5:
                    menuContaCorrenteEmpresarial(contaCorrenteEmpresarial);
                    break;
                case 6:
                    menuContaPoupancaEstudantil(contaPoupancaEstudantil);
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaBancaria(ContaBancaria conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Bancaria ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaCorrente(ContaCorrente conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Corrente ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Exibir limite cheque especial");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 4:
                    System.out.println(conta.exibeLimiteChequeEspecial());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaPoupanca(ContaPoupanca conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Poupanca ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Aplicar rendimento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 4:
                    conta.aplicarRendimento();
                    System.out.println("Rendimento aplicado.");
                    System.out.println(conta.exibeSaldo());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaCorrentePremium(ContaCorrentePremium conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Corrente Premium ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Exibir limite cheque especial");
            System.out.println("5. Exibir beneficio premium");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 4:
                    System.out.println(conta.exibeLimiteChequeEspecial());
                    break;
                case 5:
                    System.out.println(conta.exibeBeneficioPremium());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaCorrenteEmpresarial(ContaCorrenteEmpresarial conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Corrente Empresarial ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Exibir limite cheque especial");
            System.out.println("5. Solicitar emprestimo");
            System.out.println("6. Exibir taxa juros emprestimo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 4:
                    System.out.println(conta.exibeLimiteChequeEspecial());
                    break;
                case 5:
                    System.out.print("Valor do emprestimo: ");
                    tentarEmprestimo(conta, SC.nextDouble());
                    break;
                case 6:
                    System.out.println(conta.exibeTaxaJurosEmprestimo());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void menuContaPoupancaEstudantil(ContaPoupancaEstudantil conta) {
        int opcao;

        do {
            System.out.println("\n--- Conta Poupanca Estudantil ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Aplicar rendimento");
            System.out.println("5. Exibir limite de isencao");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");
            opcao = SC.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor do deposito: ");
                    conta.depositar(SC.nextDouble());
                    System.out.println("Deposito realizado.");
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    tentarSaque(conta, SC.nextDouble());
                    break;
                case 3:
                    System.out.println(conta.exibeSaldo());
                    break;
                case 4:
                    conta.aplicarRendimento();
                    System.out.println("Rendimento aplicado.");
                    System.out.println(conta.exibeSaldo());
                    break;
                case 5:
                    System.out.println(conta.exibeLimiteIsencao());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public static void tentarSaque(ContaBancaria conta, double valor) {
        try {
            boolean resultado = conta.sacar(valor);
            if (resultado) {
                System.out.println("Saque realizado com sucesso.");
                System.out.println(conta.exibeSaldo());
            } else {
                throw new MinhasExcecoes("Nao foi possivel realizar o saque.");
            }
        } catch (MinhasExcecoes e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void tentarEmprestimo(ContaCorrenteEmpresarial conta, double valor) {
        try {
            boolean resultado = conta.solicitaEmprestimo(valor);
            if (resultado) {
                System.out.println("Emprestimo aprovado.");
                System.out.println(conta.exibeSaldo());
            } else {
                throw new MinhasExcecoes("Emprestimo nao aprovado.");
            }
        } catch (MinhasExcecoes e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
