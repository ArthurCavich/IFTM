package br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco;

public class BarbeiroMonitor {
    private static final int CADEIRAS = 5;
    private static int esperando = 0;
    private static boolean barbeiroPronto = false;
    private static boolean clienteNaCadeira = false;
    private static boolean corteConcluido = false;

    public static synchronized void cortarCabeloBarbeiro() throws InterruptedException {
        while (esperando == 0) {
            System.out.println("Sem clientes. Barbeiro dormindo...");
            BarbeiroMonitor.class.wait();
        }

        // Chama um cliente da sala de espera
        esperando--;
        barbeiroPronto = true;
        clienteNaCadeira = false;
        corteConcluido = false;
        BarbeiroMonitor.class.notifyAll();

        // Aguarda o cliente sentar na cadeira
        while (!clienteNaCadeira) {
            BarbeiroMonitor.class.wait();
        }

        System.out.println("Barbeiro esta cortando cabelo...");
        Thread.sleep((long) (Math.random() * 1500) + 500);

        // Finaliza o corte
        corteConcluido = true;
        barbeiroPronto = false;
        clienteNaCadeira = false;
        System.out.println("Barbeiro terminou o corte.");
        BarbeiroMonitor.class.notifyAll();
    }

    public static synchronized void entrarCliente(int id) throws InterruptedException {
        if (esperando < CADEIRAS) {
            esperando++;
            System.out.println("Cliente " + id + " sentou na espera (" + esperando + "/" + CADEIRAS + ")");
            BarbeiroMonitor.class.notifyAll(); // acorda o barbeiro

            // Aguarda o barbeiro chamar e a cadeira estar livre
            while (!barbeiroPronto || clienteNaCadeira) {
                BarbeiroMonitor.class.wait();
            }

            // Ocupa a cadeira do barbeiro
            clienteNaCadeira = true;
            System.out.println("Cliente " + id + " sentou na cadeira do barbeiro e esta cortando cabelo");
            BarbeiroMonitor.class.notifyAll();

            // Aguarda o termino do corte
            while (!corteConcluido) {
                BarbeiroMonitor.class.wait();
            }

            System.out.println("Cliente " + id + " terminou e foi embora feliz.");
        } else {
            System.out.println("Barbearia cheia! Cliente " + id + " foi embora.");
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
