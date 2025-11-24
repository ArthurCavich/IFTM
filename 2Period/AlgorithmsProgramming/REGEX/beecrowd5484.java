import java.util.Scanner;

public class beecrowd5484 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String email = sc.nextLine();

        // Expressão regular para validar o email, utilizei as validações do exercício nos slides
        if (email.matches(".+[@](\\w|[.])+[.][a-z]{2,6}")) {
            System.out.println("Email válido");
 
        }
        sc.close();
	}

}
