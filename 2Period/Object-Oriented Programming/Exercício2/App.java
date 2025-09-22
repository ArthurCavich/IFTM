import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1: executarCompararNumeros(sc); break;
                case 2: executarEquacaoSegundoGrau(sc); break;
                case 3: executarMediaAritmetica(sc); break;
                case 4: executarTipoTriangulo(sc); break;
                case 5: executarSomaPosNeg(sc); break;
                case 6: demonstrarSomasExemplo(); break;  
                case 7: executarFatoresNumero(sc); break;
                case 8: executarParesImpares(sc); break;
                case 9: executarVerificarPrimo(sc); break;
                case 10: executarFatorial(sc); break;
                case 11: executarMMC(sc); break;
                case 12: executarMDC(sc); break;
                case 13: executarFibonacci(sc); break;
                case 14: executarPA(sc); break;
                case 15: executarSomaPG(sc); break;
                case 16: executarTabuada(); break;
                case 17: executarNumeroPerfeito(sc); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    // -------------- MENU ----------------
    static void exibirMenu() {
        System.out.println("\n=== MENU DE OPÇÕES ===");
        System.out.println("1. Comparar dois números");
        System.out.println("2. Raízes de equação do 2º grau");
        System.out.println("3. Média aritmética");
        System.out.println("4. Tipo de triângulo");
        System.out.println("5. Soma até digitar 0 (contagem positivas/negativas)");
        System.out.println("6. Exemplo fixo: -1, -6, 3, 4, -2, 7, 9, 0");
        System.out.println("7. Fatores de um número, repetir se quiser");
        System.out.println("8. Pares, ímpares, positivos, negativos");
        System.out.println("9. Verificar número primo");
        System.out.println("10. Fatorial");
        System.out.println("11. MMC de dois números");
        System.out.println("12. MDC de dois números");
        System.out.println("13. Sequência de Fibonacci");
        System.out.println("14. Progressão Aritmética (PA)");
        System.out.println("15. Soma dos elementos da PG");
        System.out.println("16. Tabuada de multiplicação de 1 a 10");
        System.out.println("17. Número perfeito");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }


    // ---------------- Exercício 1 ----------------
    static void executarCompararNumeros(Scanner sc) {
        int a = lerInteiro(sc, "Digite o primeiro número: ");
        int b = lerInteiro(sc, "Digite o segundo número: ");
        int resultado = comparar(a, b);
        exibirComparacao(resultado);
    }

    static int lerInteiro(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextInt();
    }

    static int comparar(int a, int b) {
        if (a < b) return -1;
        else if (a == b) return 0;
        else return 1;
    }

    static void exibirComparacao(int res) {
        if (res == -1) System.out.println("O primeiro número é menor.");
        else if (res == 0) System.out.println("Os números são iguais.");
        else System.out.println("O primeiro número é maior.");
    }


    // ---------------- Exercício 2 ----------------
    static void executarEquacaoSegundoGrau(Scanner sc) {
        double a = lerDouble(sc, "Digite a: ");
        double b = lerDouble(sc, "Digite b: ");
        double c = lerDouble(sc, "Digite c: ");
        double delta = calcularDelta(a, b, c);
        exibirRaizes(a, b, delta);
    }

    static double lerDouble(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextDouble();
    }

    static double calcularDelta(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    static void exibirRaizes(double a, double b, double delta) {
        if (delta < 0) {
            System.out.println("Não há raízes reais.");
        } else {
            double r1 = (-b + Math.sqrt(delta)) / (2 * a);
            double r2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("Raízes: " + r1 + " e " + r2);
        }
    }


    // ---------------- Exercício 3 ----------------
    static void executarMediaAritmetica(Scanner sc) {
        int a = lerInteiro(sc, "Digite o primeiro número: ");
        int b = lerInteiro(sc, "Digite o segundo número: ");
        double media = calcularMedia(a, b);
        exibirMedia(media);
    }

    static double calcularMedia(int a, int b) {
        return (a + b) / 2.0;
    }

    static void exibirMedia(double media) {
        System.out.println("Média aritmética: " + media);
    }


    // ---------------- Exercício 4 ----------------
    static void executarTipoTriangulo(Scanner sc) {
        int a = lerInteiro(sc, "Lado 1: ");
        int b = lerInteiro(sc, "Lado 2: ");
        int c = lerInteiro(sc, "Lado 3: ");
        if (formaTriangulo(a, b, c)) {
            exibirTipoTriangulo(a, b, c);
        } else {
            System.out.println("Não formam triângulo.");
        }
    }

    static boolean formaTriangulo(int a, int b, int c) {
        return (a + b > c && a + c > b && b + c > a);
    }

    static void exibirTipoTriangulo(int a, int b, int c) {
        if (a == b && b == c) System.out.println("Triângulo equilátero.");
        else if (a == b || a == c || b == c) System.out.println("Triângulo isósceles.");
        else System.out.println("Triângulo escaleno.");
    }


    // ---------------- Exercício 5 ----------------
    static void executarSomaPosNeg(Scanner sc) {
        int somaPos = 0, somaNeg = 0;
        int numero;
        do {
            numero = lerInteiro(sc, "Digite um número (0 para finalizar): ");
            if (numero > 0) somaPos += numero;
            else if (numero < 0) somaNeg += numero;
            // se for zero, para
        } while (numero != 0);
        exibirSomasPosNeg(somaPos, somaNeg);
    }

    static void exibirSomasPosNeg(int pos, int neg) {
        System.out.println("Soma dos positivos: " + pos);
        System.out.println("Soma dos negativos: " + neg);
    }


    // ---------------- Exercício 6 ----------------
    static void demonstrarSomasExemplo() {
        int[] numeros = { -1, -6, 3, 4, -2, 7, 9, 0 };
        ResultadoSoma res = calcularSomasDoArray(numeros);
        exibirResultadoSomasExemplo(res);
    }

    static ResultadoSoma calcularSomasDoArray(int[] arr) {
        int somaPos = 0, somaNeg = 0;
        for (int n : arr) {
            if (n >= 0) somaPos++;
            else if (n < 0) somaNeg++;
        }
        return new ResultadoSoma(somaPos, somaNeg);
    }

    static void exibirResultadoSomasExemplo(ResultadoSoma r) {
        System.out.println("Somas positivas = " + r.pos);
        System.out.println("Somas negativas = " + r.neg);
    }

    static class ResultadoSoma {
        int pos, neg;
        ResultadoSoma(int pos, int neg) {
            this.pos = pos;
            this.neg = neg;
        }
    }


    // ---------------- Exercício 7 ----------------
    static void executarFatoresNumero(Scanner sc) {
        char opc;
        do {
            int n = lerInteiro(sc, "Digite um número natural: ");
            int[] fatores = acharFatores(n);
            exibirFatores(n, fatores);
            System.out.print("Deseja digitar novamente? (s/S para sim): ");
            opc = sc.next().charAt(0);
        } while (opc == 's' || opc == 'S');
    }

    static int[] acharFatores(int n) {
        
        int[] temp = new int[n];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                temp[count++] = i;
            }
        }
        int[] fatores = new int[count];
        System.arraycopy(temp, 0, fatores, 0, count);
        return fatores;
    }

    static void exibirFatores(int n, int[] fatores) {
        System.out.print("Fatores de " + n + ": ");
        for (int f : fatores) {
            System.out.print(f + " ");
        }
        System.out.println();
    }


    // ---------------- Exercício 8 ----------------
    static void executarParesImpares(Scanner sc) {
        int num, pares = 0, impares = 0, positivos = 0, negativos = 0;
        do {
            num = lerInteiro(sc, "Digite um número (0 para parar): ");
            if (num == 0) break;
            if (num % 2 == 0) pares++;
            else impares++;
            if (num > 0) positivos++;
            else negativos++;
        } while (true);
        exibirResumoParesImpares(pares, impares, positivos, negativos);
    }

    static void exibirResumoParesImpares(int pares, int impares, int positivos, int negativos) {
        System.out.println("Quantidade de pares: " + pares);
        System.out.println("Quantidade de impares: " + impares);
        System.out.println("Quantidade de positivos: " + positivos);
        System.out.println("Quantidade de negativos: " + negativos);
    }


    // ---------------- Exercício 9 ----------------
    static void executarVerificarPrimo(Scanner sc) {
        int n = lerInteiro(sc, "Digite um número natural: ");
        boolean primo = ehPrimo(n);
        exibirPrimo(n, primo);
    }

    static boolean ehPrimo(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static void exibirPrimo(int n, boolean primo) {
        if (primo) System.out.println(n + " é primo.");
        else System.out.println(n + " não é primo.");
    }


    // ---------------- Exercício 10 ----------------
    static void executarFatorial(Scanner sc) {
        int n = lerInteiro(sc, "Digite um número natural: ");
        long fat = calcularFatorial(n);
        exibirFatorial(n, fat);
    }

    static long calcularFatorial(int n) {
        long fat = 1;
        for (int i = 1; i <= n; i++) {
            fat *= i;
        }
        return fat;
    }

    static void exibirFatorial(int n, long fat) {
        System.out.println("Fatorial de " + n + " é: " + fat);
    }


    // ---------------- Exercício 11 ----------------
    static void executarMMC(Scanner sc) {
        int a = lerInteiro(sc, "Digite o primeiro número (maior que zero): ");
        int b = lerInteiro(sc, "Digite o segundo número (maior que zero): ");
        int mmc = calcularMMC(a, b);
        exibirMMC(a, b, mmc);
    }

    static int calcularMMC(int a, int b) {
        // fórmula: mmc(a,b) = |a * b| / mdc(a,b)
        int mdc = calcularMDCLogica(a, b);
        return Math.abs(a * b) / mdc;
    }

    static void exibirMMC(int a, int b, int mmc) {
        System.out.println("MMC de " + a + " e " + b + " é: " + mmc);
    }


    // ---------------- Exercício 12 ----------------
    static void executarMDC(Scanner sc) {
        int a = lerInteiro(sc, "Digite o primeiro número (maior que zero): ");
        int b = lerInteiro(sc, "Digite o segundo número (maior que zero): ");
        int mdc = calcularMDCLogica(a, b);
        exibirMDC(a, b, mdc);
    }

    static int calcularMDCLogica(int a, int b) {
        // algoritmo de Euclides
        while (b != 0) {
            int resto = a % b;
            a = b;
            b = resto;
        }
        return a;
    }

    static void exibirMDC(int a, int b, int mdc) {
        System.out.println("MDC de " + a + " e " + b + " é: " + mdc);
    }


    // ---------------- Exercício 13 ----------------
    static void executarFibonacci(Scanner sc) {
        int termos = lerInteiro(sc, "Quantos termos da sequência Fibonacci deseja ver? ");
        int[] seq = gerarFibonacci(termos);
        exibirFibonacci(seq);
    }

    static int[] gerarFibonacci(int termos) {
        if (termos <= 0) return new int[0];
        int[] seq = new int[termos];
        seq[0] = 0;
        if (termos > 1) seq[1] = 1;
        for (int i = 2; i < termos; i++) {
            seq[i] = seq[i - 1] + seq[i - 2];
        }
        return seq;
    }

    static void exibirFibonacci(int[] seq) {
        System.out.print("Sequência Fibonacci: ");
        for (int n : seq) {
            System.out.print(n + " ");
        }
        System.out.println();
    }


    // ---------------- Exercício 14 ----------------
    static void executarPA(Scanner sc) {
        int a1 = lerInteiro(sc, "Primeiro termo da PA: ");
        int razao = lerInteiro(sc, "Razão: ");
        int n = lerInteiro(sc, "Número de termos: ");
        int[] pa = gerarPA(a1, razao, n);
        exibirPA(pa);
    }

    static int[] gerarPA(int a1, int razao, int n) {
        int[] pa = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = a1 + i * razao;
        }
        return pa;
    }

    static void exibirPA(int[] pa) {
        System.out.print("PA: ");
        for (int t : pa) {
            System.out.print(t + " ");
        }
        System.out.println();
    }


    // ---------------- Exercício 15 ----------------
    static void executarSomaPG(Scanner sc) {
        double a1 = lerDouble(sc, "Primeiro termo da PG: ");
        double razao = lerDouble(sc, "Razão da PG: ");
        int n = lerInteiro(sc, "Quantidade de termos da PG: ");
        double soma = calcularSomaPG(a1, razao, n);
        exibirSomaPG(a1, razao, n, soma);
    }

    static double calcularSomaPG(double a1, double q, int n) {
        // soma da PG: a1 * (q^n - 1) / (q - 1), se q != 1
        if (q == 1) return a1 * n;
        return a1 * (Math.pow(q, n) - 1) / (q - 1);
    }

    static void exibirSomaPG(double a1, double q, int n, double soma) {
        System.out.println("Soma da PG com " + n + " termos: " + soma);
    }


    // ---------------- Exercício 16 ----------------
    static void executarTabuada() {
        int[][] tab = gerarTabuada();
        exibirTabuada(tab);
    }

    static int[][] gerarTabuada() {
        int[][] tab = new int[10][10];
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                tab[i-1][j-1] = i * j;
            }
        }
        return tab;
    }

    static void exibirTabuada(int[][] tab) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Tabuada do " + (i+1) + ":");
            for (int j = 0; j < 10; j++) {
                System.out.println((i+1) + " x " + (j+1) + " = " + tab[i-1][j]);
            }
            System.out.println();
        }
    }


    // ---------------- Exercício 17 ----------------
    static void executarNumeroPerfeito(Scanner sc) {
        int n = lerInteiro(sc, "Digite um número natural: ");
        boolean perfeito = ehNumeroPerfeito(n);
        exibirNumeroPerfeito(n, perfeito);
    }

    static boolean ehNumeroPerfeito(int n) {
        if (n <= 1) return false;
        int soma = 1;  // 1 é divisor próprio sempre
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0) soma += i;
        }
        return soma == n;
    }

    static void exibirNumeroPerfeito(int n, boolean perfeito) {
        if (perfeito) System.out.println(n + " é número perfeito.");
        else System.out.println(n + " não é número perfeito.");
    }

}