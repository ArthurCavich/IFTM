import java.util.Scanner;

public class beecrowd5485 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String entrada = sc.nextLine();
        
        //validação para o formato R$ seguido de espaço, dígitos, ponto e 2 dígitos
        if (entrada.matches("R\\$ [0-9]+\\.[0-9]{2}")) {
            // Extrai o valor numérico da string
            String valorStr = entrada.substring(3).replace(",", ".");
            double valor = Double.parseDouble(valorStr);
            
            // Verifica se o valor está no intervalo válido [100.00, 99999.99]
            if (valor >= 100.00 && valor <= 99999.99) {
                System.out.println("Valor válido");
            } else {
                System.out.println("Valor inválido");
            }
        } else {
            System.out.println("Valor inválido");
        }
        
        sc.close();
    }
}

