package br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LeitoresEscritoresLock {
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Lock readLock = rwLock.readLock();
    private static Lock writeLock = rwLock.writeLock();

    public static void leitor(int id) throws InterruptedException {
        readLock.lock();
        try {
            System.out.println("Leitor " + id + " esta lendo o quadro");
            Thread.sleep((long) (Math.random() * 1500));
            System.out.println("Leitor " + id + " terminou de ler.");
        } finally {
            readLock.unlock();
        }
    }

    public static void escritor(int id) throws InterruptedException {
        writeLock.lock();
        try {
            System.out.println(">>> ESCRITOR " + id + " ESTA ESCREVENDO NO QUADRO (Acesso exclusivo) <<<");
            Thread.sleep((long) (Math.random() * 2000));
            System.out.println(">>> Escritor " + id + " terminou de escrever.");
        } finally {
            writeLock.unlock();
        }
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
