package br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco;

import java.util.concurrent.Semaphore;

public class BarbeiroSemaforo {
    private static final int CADEIRAS = 5;
    private static int esperando = 0;

    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore clientes = new Semaphore(0);
    private static Semaphore barbeiro = new Semaphore(0);
    private static Semaphore corteConcluido = new Semaphore(0);

    public static void main(String[] args) {
        // Thread do Barbeiro
        new Thread(() -> {
            try {
                while (true) {
                    // Dorme se nao houver clientes
                    clientes.acquire();

                    mutex.acquire();
                    esperando--;
                    barbeiro.release(); // convida o cliente para a cadeira
                    mutex.release();

                    System.out.println("Barbeiro esta cortando cabelo...");
                    Thread.sleep((long) (Math.random() * 1500) + 500);

                    System.out.println("Barbeiro terminou o corte.");
                    corteConcluido.release(); // libera o cliente
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
                    mutex.acquire();
                    if (esperando < CADEIRAS) {
                        esperando++;
                        System.out.println("Cliente " + id + " sentou na espera (" + esperando + "/" + CADEIRAS + ")");
                        clientes.release(); // acorda o barbeiro
                        mutex.release();

                        barbeiro.acquire(); // aguarda ser chamado pelo barbeiro
                        System.out.println("Cliente " + id + " sentou na cadeira do barbeiro e esta cortando cabelo");

                        corteConcluido.acquire(); // aguarda o termino do corte
                        System.out.println("Cliente " + id + " terminou e foi embora feliz.");
                    } else {
                        mutex.release();
                        System.out.println("Barbearia cheia! Cliente " + id + " foi embora.");
                    }
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
