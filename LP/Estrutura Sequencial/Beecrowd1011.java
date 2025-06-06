import java.io.IOException;
import java.util.Scanner;
 
public class Beecrowd1011 {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        double raio = s.nextDouble();
        double pi = 3.14159;
    
        double volume = 4 / 3.0 * pi * raio * raio * raio;
        
        System.out.printf("VOLUME = %.3f%n", volume);
 
    }
 
}
