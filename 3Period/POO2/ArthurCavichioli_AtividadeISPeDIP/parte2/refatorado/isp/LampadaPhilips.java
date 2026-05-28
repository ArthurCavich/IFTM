package parte2.refatorado.isp;

public class LampadaPhilips implements DispositivoLigavel {

    @Override
    public void ligar() {
        System.out.println("Lâmpada acesa.");
    }

    @Override
    public void desligar() {
        System.out.println("Lâmpada apagada.");
    }
}
