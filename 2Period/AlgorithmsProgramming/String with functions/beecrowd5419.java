import java.util.Scanner;

public class beecrowd5419 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        // comparação utilizando o método equals
        if (s1.equals(s2)) {
            System.out.println("correto");
        } else {
            System.out.println("incorreto");
        }

        sc.close();
    }
}