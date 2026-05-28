package parte2.comViolacao.ispviolacao;

public class LampadaPhilips implements DispositivoSuperInteligente {

    @Override
    public void ligar() {
        System.out.println("Lâmpada acesa.");
    }

    @Override
    public void desligar() {
        System.out.println("Lâmpada apagada.");
    }

    @Override
    public void imprimirDocumento(String texto) {
        /*
         * VIOLAÇÃO DO ISP: a lâmpada é forçada a herdar um método inútil
         * por causa da interface DispositivoSuperInteligente.
         */
        throw new UnsupportedOperationException("Lâmpadas não imprimem documentos!");
    }
}
