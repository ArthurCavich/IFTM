package br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos;

public class JantarFilosofosMonitor {
    private static final int N = 5;
    private static final int PENSANDO = 0;
    private static final int FAMINTO = 1;
    private static final int COMENDO = 2;

    private static int[] estado = new int[N];

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            estado[i] = PENSANDO;
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

    public static synchronized void pegarGarfos(int i) throws InterruptedException {
        estado[i] = FAMINTO;
        System.out.println("Filosofo " + i + " esta com fome");
        testar(i);
        while (estado[i] != COMENDO) {
            JantarFilosofosMonitor.class.wait();
        }
        System.out.println("Filosofo " + i + " pegou os garfos e esta comendo");
    }

    public static synchronized void soltarGarfos(int i) {
        estado[i] = PENSANDO;
        System.out.println("Filosofo " + i + " terminou de comer e esta pensando");
        testar((i + 4) % N);
        testar((i + 1) % N);
        JantarFilosofosMonitor.class.notifyAll();
    }

    private static void testar(int i) {
        int esquerda = (i + 4) % N;
        int direita = (i + 1) % N;
        if (estado[i] == FAMINTO && estado[esquerda] != COMENDO && estado[direita] != COMENDO) {
            estado[i] = COMENDO;
        }
    }
}
