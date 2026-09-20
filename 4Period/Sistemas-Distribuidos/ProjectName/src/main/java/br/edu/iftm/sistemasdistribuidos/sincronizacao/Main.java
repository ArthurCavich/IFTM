package br.edu.iftm.sistemasdistribuidos.sincronizacao;

import java.util.Scanner;
import br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos.*;
import br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco.*;
import br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println("  ATIVIDADE 1: PROBLEMAS CLASSICOS DE SINCRONIZACAO");
        System.out.println("=================================================");
        System.out.println("1 - Jantar dos Filosofos (Semaforo)");
        System.out.println("2 - Jantar dos Filosofos (Monitor)");
        System.out.println("3 - Jantar dos Filosofos (Lock)");
        System.out.println("4 - Barbeiro Dorminhoco (Semaforo)");
        System.out.println("5 - Barbeiro Dorminhoco (Monitor)");
        System.out.println("6 - Barbeiro Dorminhoco (Lock)");
        System.out.println("7 - Leitores e Escritores (Semaforo)");
        System.out.println("8 - Leitores e Escritores (Monitor)");
        System.out.println("9 - Leitores e Escritores (Lock)");
        System.out.print("\nEscolha a opcao desejada (1-9): ");

        int opcao = scanner.nextInt();
        System.out.println("\nIniciando execucao... (Pressione Ctrl+C para encerrar a qualquer momento)\n");

        switch (opcao) {
            case 1 -> JantarFilosofosSemaforo.main(args);
            case 2 -> JantarFilosofosMonitor.main(args);
            case 3 -> JantarFilosofosLock.main(args);
            case 4 -> BarbeiroSemaforo.main(args);
            case 5 -> BarbeiroMonitor.main(args);
            case 6 -> BarbeiroLock.main(args);
            case 7 -> LeitoresEscritoresSemaforo.main(args);
            case 8 -> LeitoresEscritoresMonitor.main(args);
            case 9 -> LeitoresEscritoresLock.main(args);
            default -> System.out.println("Opcao invalida.");
        }
    }
}
