package com.example.calculadoraaula.calculadora;

public class Main {
    public static void main (String args[]){
	    Calculadora calc = new Calculadora();
	    //cenário de teste
	    int primeiroNumeroDaSoma = 10;
	    int segundoNumeroDaSoma = 20;
	    int resultadoEsperadoDaSoma = 30;
	    //executa (Ação)
	    int resultadoObtidoNaSoma = calc.somar(primeiroNumeroDaSoma, segundoNumeroDaSoma);
	    //valida resultado (Efeito esperado)
	    if (resultadoObtidoNaSoma!=resultadoEsperadoDaSoma){
	        System.out.println("Oops! Deu um resultado não esperado: "+resultadoObtidoNaSoma);
	    }
	    else {
            System.out.println("OK! Passou do teste.");
        }
	}
}
