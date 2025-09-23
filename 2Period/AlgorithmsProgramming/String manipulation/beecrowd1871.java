import java.util.Scanner;

public class beecrowd1871 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        //utilizado while para repetição
		while (true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			//se atingir os valores, pare
			if (a == 0 && b == 0) {
				break;
			}
			//variável soma
			int soma = a + b;
            //soma e devolve como string
			String somaStr = Integer.toString(soma);
            //variável que receberá o resultado da soma abaixo
			String resultado = "";
            //percorre a string e com o tamanho de somaStr
			for (int i = 0; i < somaStr.length(); i++) {
				//se a posição da escrita por diferente de zero
				if (somaStr.charAt(i) != '0') {
				    //concatena
					resultado += somaStr.charAt(i);
				}
				
			}
			//imprime o resultado
			System.out.println(resultado);
		}

		sc.close();
	}
	
}
