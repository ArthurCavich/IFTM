package br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos;

import java.util.concurrent.Semaphore;

public class JantarFilosofosSemaforo {
    private static final int N = 5;
    private static final int PENSANDO = 0;
    private static final int FAMINTO = 1;
    private static final int COMENDO = 2;

    private static int[] estado = new int[N];
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore[] s = new Semaphore[N];

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            estado[i] = PENSANDO;
            s[i] = new Semaphore(0);
        }

        for (int i = 0; i < N; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long) (Math.random() * 2000));
                        pegarGarfos(id);
                        Thread.sleep((long) (Math.random() * 2000));
                        soltarGarfos(id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void pegarGarfos(int i) throws InterruptedException {
        mutex.acquire();
        estado[i] = FAMINTO;
        System.out.println("Filosofo " + i + " esta com fome");
        testar(i);
        mutex.release();
        s[i].acquire();
    }

    public static void soltarGarfos(int i) throws InterruptedException {
        mutex.acquire();
        estado[i] = PENSANDO;
        System.out.println("Filosofo " + i + " terminou de comer e esta pensando");
        testar((i + 4) % N);
        testar((i + 1) % N);
        mutex.release();
    }

    private static void testar(int i) {
        int esquerda = (i + 4) % N;
        int direita = (i + 1) % N;
        if (estado[i] == FAMINTO && estado[esquerda] != COMENDO && estado[direita] != COMENDO) {
            estado[i] = COMENDO;
            System.out.println("Filosofo " + i + " pegou os garfos e esta comendo");
            s[i].release();
        }
    }
}
