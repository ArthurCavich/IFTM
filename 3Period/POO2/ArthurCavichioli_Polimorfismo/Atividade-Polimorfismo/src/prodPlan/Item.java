package prodPlan;

import java.util.Locale;

public class Item {
    private Parte parte;
    private int quantidade;

    public Item(Parte parte, int quantidade) {
        this.parte = parte;
        this.quantidade = quantidade;
    }

    public float calculaValor() {
        return quantidade * parte.calculaValor();
    }

    @Override
    public String toString() {
        float valorUnitario = parte.calculaValor();
        float valorTotal = calculaValor();
        return String.format(Locale.US, "cod:%d nome:%s quantidade:%d valor unitario:%.1f valor:%.1f",
                parte.cod, parte.nome, quantidade, valorUnitario, valorTotal);
    }
}
