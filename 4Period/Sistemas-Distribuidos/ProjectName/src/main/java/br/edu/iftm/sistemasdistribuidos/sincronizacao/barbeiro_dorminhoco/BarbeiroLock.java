package br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarbeiroLock {
    private static final int CADEIRAS = 5;
    private static int esperando = 0;
    private static boolean barbeiroPronto = false;
    private static boolean clienteNaCadeira = false;
    private static boolean corteConcluido = false;

    private static Lock lock = new ReentrantLock();
    private static Condition condBarbeiro = lock.newCondition();
    private static Condition condCadeira = lock.newCondition();
    private static Condition condCorte = lock.newCondition();

    public static void cortarCabeloBarbeiro() throws InterruptedException {
        lock.lock();
        try {
            while (esperando == 0) {
                System.out.println("Sem clientes. Barbeiro dormindo...");
                condBarbeiro.await();
            }

            esperando--;
            barbeiroPronto = true;
            clienteNaCadeira = false;
            corteConcluido = false;
            condCadeira.signal(); // chama 1 cliente da sala de espera

            while (!clienteNaCadeira) {
                condBarbeiro.await(); // espera o cliente sentar
            }

            System.out.println("Barbeiro esta cortando cabelo...");
        } finally {
            lock.unlock();
        }

        Thread.sleep((long) (Math.random() * 1500) + 500);

        lock.lock();
        try {
            corteConcluido = true;
            barbeiroPronto = false;
            clienteNaCadeira = false;
            System.out.println("Barbeiro terminou o corte.");
            condCorte.signal(); // avisa o cliente que o corte terminou
        } finally {
            lock.unlock();
        }
    }

    public static void entrarCliente(int id) throws InterruptedException {
        lock.lock();
        try {
            if (esperando < CADEIRAS) {
                esperando++;
                System.out.println("Cliente " + id + " sentou na espera (" + esperando + "/" + CADEIRAS + ")");
                condBarbeiro.signal(); // acorda o barbeiro

                while (!barbeiroPronto || clienteNaCadeira) {
                    condCadeira.await();
                }

                clienteNaCadeira = true;
                System.out.println("Cliente " + id + " sentou na cadeira do barbeiro e esta cortando cabelo");
                condBarbeiro.signal(); // avisa o barbeiro que sentou

                while (!corteConcluido) {
                    condCorte.await();
                }

                System.out.println("Cliente " + id + " terminou e foi embora feliz.");
            } else {
                System.out.println("Barbearia cheia! Cliente " + id + " foi embora.");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // Thread do Barbeiro
        new Thread(() -> {
            try {
                while (true) {
                    cortarCabeloBarbeiro();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Gerador de Clientes
        int idCliente = 1;
        while (true) {
            final int id = idCliente++;
            new Thread(() -> {
                try {
                    entrarCliente(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                Thread.sleep((long) (Math.random() * 1200) + 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
