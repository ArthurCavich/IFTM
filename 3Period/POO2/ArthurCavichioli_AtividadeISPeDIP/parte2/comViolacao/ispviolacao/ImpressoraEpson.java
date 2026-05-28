package parte2.comViolacao.ispviolacao;

public class ImpressoraEpson implements DispositivoSuperInteligente {

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
