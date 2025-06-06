import java.io.IOException;
import java.util.Scanner;

public class Beecrowd1014 {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        double x = s.nextDouble();
        double y = s.nextDouble();
        
        double consumo = x / y;
        
        System.out.printf("%.3f km/l\n", consumo);

    }
 
}
