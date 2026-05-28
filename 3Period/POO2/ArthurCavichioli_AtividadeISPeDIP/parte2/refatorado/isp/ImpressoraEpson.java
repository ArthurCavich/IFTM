package parte2.refatorado.isp;

public class ImpressoraEpson implements DispositivoLigavel, Imprimivel {

    @Override
    public void ligar() {
        System.out.println("Impressora ligada.");
    }

    @Override
    public void desligar() {
        System.out.println("Impressora desligada.");
    }

    @Override
    public void imprimirDocumento(String texto) {
        System.out.println("Imprimindo: " + texto);
    }
}
