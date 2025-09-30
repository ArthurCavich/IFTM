import java.util.Scanner;

public class beecrowd1429{
	public static void main(String[] args) {
	    //criação dos fatoriais até o quinto dígito.
		int[] fatoriais = {1, 1, 2, 6, 24, 120};
		
		Scanner sc = new Scanner(System.in);
		//criação do while para condição de parada
		while (true){
		    //aqui eu solicito a notação acm para conversão em dígitos
		    String acm = sc.nextLine();
		    //condição de parada quando a notação iguala a '0'
		    if (acm.equals("0")){
		        break;
		    }
		    //variável para somar
		    int resultado = 0;
		    //variável para saber o tamanho total pra percorrer no for
		    int n = acm.length();
		    for(int i = 0; i < n; i++){
		        //aqui eu percorro a notação transformando a posição inversa (direita para esquerda)
		        char digitoChar = acm.charAt(n - 1 - i);
		        //aqui eu converto o caracter para inteiro
		        int digito = digitoChar - '0';
		        //aqui eu realizo o fatorial do numero digito com o valor do fatorial
		        resultado += digito * fatoriais[i + 1];
		    }
		    //imprime o resultado enquanto não for '0'
		    System.out.println(resultado);
		}
		
	}
}
