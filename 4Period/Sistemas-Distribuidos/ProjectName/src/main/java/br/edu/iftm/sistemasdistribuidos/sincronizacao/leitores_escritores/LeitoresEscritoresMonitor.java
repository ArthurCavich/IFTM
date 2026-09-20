package br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores;

public class LeitoresEscritoresMonitor {
    private static int leitores = 0;
    private static boolean escrevendo = false;

    public static synchronized void iniciarLeitura(int id) throws InterruptedException {
        while (escrevendo) {
            LeitoresEscritoresMonitor.class.wait();
        }
        leitores++;
        System.out.println("Leitor " + id + " esta lendo o quadro (Total lendo: " + leitores + ")");
    }

    public static synchronized void terminarLeitura(int id) {
        leitores--;
        System.out.println("Leitor " + id + " terminou de ler.");
        if (leitores == 0) {
            LeitoresEscritoresMonitor.class.notifyAll();
        }
    }

    public static synchronized void iniciarEscrita(int id) throws InterruptedException {
        while (escrevendo || leitores > 0) {
            LeitoresEscritoresMonitor.class.wait();
        }
        escrevendo = true;
        System.out.println(">>> ESCRITOR " + id + " ESTA ESCREVENDO NO QUADRO (Acesso exclusivo) <<<");
    }

    public static synchronized void terminarEscrita(int id) {
        escrevendo = false;
        System.out.println(">>> Escritor " + id + " terminou de escrever.");
        LeitoresEscritoresMonitor.class.notifyAll();
    }

    public static void main(String[] args) {
        // Inicia 3 leitores
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long) (Math.random() * 2000));
                        iniciarLeitura(id);
                        Thread.sleep((long) (Math.random() * 1500));
                        terminarLeitura(id);
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
                        iniciarEscrita(id);
                        Thread.sleep((long) (Math.random() * 2000));
                        terminarEscrita(id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
