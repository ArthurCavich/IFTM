package br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores;

import java.util.concurrent.Semaphore;

public class LeitoresEscritoresSemaforo {
    private static int leitores = 0;
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore db = new Semaphore(1);

    public static void leitor(int id) throws InterruptedException {
        // Entrada do leitor
        mutex.acquire();
        leitores++;
        if (leitores == 1) {
            db.acquire(); // primeiro leitor bloqueia os escritores
        }
        mutex.release();

        // Leitura compartilhada
        System.out.println("Leitor " + id + " esta lendo o quadro (Total lendo: " + leitores + ")");
        Thread.sleep((long) (Math.random() * 1500));

        // Saida do leitor
        mutex.acquire();
        leitores--;
        System.out.println("Leitor " + id + " terminou de ler.");
        if (leitores == 0) {
            db.release(); // ultimo leitor libera para os escritores
        }
        mutex.release();
    }

    public static void escritor(int id) throws InterruptedException {
        // Entrada do escritor (exclusiva)
        db.acquire();

        // Escrita exclusiva
        System.out.println(">>> ESCRITOR " + id + " ESTA ESCREVENDO NO QUADRO (Acesso exclusivo) <<<");
        Thread.sleep((long) (Math.random() * 2000));
        System.out.println(">>> Escritor " + id + " terminou de escrever.");

        // Saida do escritor
        db.release();
    }

    public static void main(String[] args) {
        // Inicia 3 leitores
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long) (Math.random() * 2000));
                        leitor(id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Inicia 2 escritores
        for (int i = 1; i <= 2; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long) (Math.random() * 3000));
                        escritor(id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
