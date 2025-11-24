import java.util.ArrayList;
import java.util.Scanner;

public class exe03 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // a) Solicitar n (quantidade de números)
            int n = scanner.nextInt();
            
            ArrayList<Integer> numeros = new ArrayList<>();
            
            // b) Inserir n números inteiros no ArrayList
            for (int i = 0; i < n; i++) {
                int numero = scanner.nextInt();
                numeros.add(numero);
            }
            
            // c) Mostrar a lista na ordem de inserção
            for (int i = 0; i < numeros.size(); i++) {
                System.out.print(numeros.get(i));
                if (i < numeros.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            // d) Calcular e mostrar a soma
            int soma = 0;
            for (int num : numeros) {
                soma += num;
            }
            System.out.println(soma);
            
            // e) Encontrar e mostrar o maior número
            int maior = numeros.get(0);
            for (int num : numeros) {
                if (num > maior) {
                    maior = num;
                }
            }
            System.out.println(maior);
            
            // f) Remover números ímpares e mostrar a lista final
            // Usar loop reverso para evitar problemas ao remover elementos
            for (int i = numeros.size() - 1; i >= 0; i--) {
                if (numeros.get(i) % 2 != 0) {
                    numeros.remove(i);
                }
            }
            
            // Mostrar a lista final (apenas números pares)
            for (int i = 0; i < numeros.size(); i++) {
                System.out.print(numeros.get(i));
                if (i < numeros.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

