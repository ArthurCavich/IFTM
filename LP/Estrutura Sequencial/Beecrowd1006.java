import java.io.IOException;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(System.in);
        
        double A = s.nextDouble();
        double B = s.nextDouble();
        double C = s.nextDouble();
        double MEDIA = ((A * 2.0) + (B * 3.0) + (C * 5.0)) / 10.0;
        System.out.printf("MEDIA = %.1f%n", MEDIA);

    }
 
}
