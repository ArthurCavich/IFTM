package br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarFilosofosLock {
    private static final int N = 5;
    private static final int PENSANDO = 0;
    private static final int FAMINTO = 1;
    private static final int COMENDO = 2;

    private static int[] estado = new int[N];
    private static Lock lock = new ReentrantLock();
    private static Condition[] cond = new Condition[N];

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            estado[i] = PENSANDO;
            cond[i] = lock.newCondition();
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
        lock.lock();
        try {
            estado[i] = FAMINTO;
            System.out.println("Filosofo " + i + " esta com fome");
            testar(i);
            while (estado[i] != COMENDO) {
                cond[i].await();
            }
            System.out.println("Filosofo " + i + " pegou os garfos e esta comendo");
        } finally {
            lock.unlock();
        }
    }

    public static void soltarGarfos(int i) {
        lock.lock();
        try {
            estado[i] = PENSANDO;
            System.out.println("Filosofo " + i + " terminou de comer e esta pensando");
            testar((i + 4) % N);
            testar((i + 1) % N);
        } finally {
            lock.unlock();
        }
    }

    private static void testar(int i) {
        int esquerda = (i + 4) % N;
        int direita = (i + 1) % N;
        if (estado[i] == FAMINTO && estado[esquerda] != COMENDO && estado[direita] != COMENDO) {
            estado[i] = COMENDO;
            cond[i].signal();
        }
    }
}
