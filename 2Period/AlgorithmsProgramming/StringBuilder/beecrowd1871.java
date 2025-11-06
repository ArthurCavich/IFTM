import java.util.Scanner;

public class beecrowd1871 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // declaro as duas entradas como inteiros
                int M = scanner.nextInt();
                int N = scanner.nextInt();
                
                // Condição de parada: quando ambos são zero
                if (M == 0 && N == 0) {
                    break;
                }
                
                // faço a soma das duas entradas
                int soma = M + N;

                // declaro o resultado como uma string
                StringBuilder resultado = new StringBuilder();
                // converto a soma para uma string
                String somaStr = String.valueOf(soma);
                // faço um for para percorrer a string somaStr
                for (int i = 0; i < somaStr.length(); i++) {
                    char c = somaStr.charAt(i);
                    if (c != '0') {
                        // adiciono o caractere c ao resultado
                        resultado.append(c);
                    }
                }
                // imprimo o resultado
                System.out.println(resultado.toString());
            }
        }
    }
}
