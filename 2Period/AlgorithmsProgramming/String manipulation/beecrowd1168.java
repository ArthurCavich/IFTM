import java.util.Scanner;

public class beecrowd1168 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // consumir quebra de linha

        // tabela de LEDs por dígito
        int[] leds = {6,2,5,5,4,5,6,3,7,6};

        for (int i = 0; i < n; i++) {
            String numero = sc.nextLine();
            int total = 0;

            for (int j = 0; j < numero.length(); j++) {
                char c = numero.charAt(j);
                int digito = c - '0'; // converte char para número
                total += leds[digito];
            }

            System.out.println(total + " leds");
        }

        sc.close();
    }
}
