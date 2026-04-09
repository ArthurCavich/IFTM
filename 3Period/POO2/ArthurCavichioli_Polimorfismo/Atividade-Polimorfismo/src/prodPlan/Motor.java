package prodPlan;

import java.util.Locale;

public class Motor extends Parte {
    private float potencia;
    private float corrente;
    private int rpm;

    public Motor(int cod, String nome, String descricao, float valor, 
                 float potencia, float corrente, int rpm) {
        super(cod, nome, descricao, valor);
        this.potencia = potencia;
        this.corrente = corrente;
        this.rpm = rpm;
    }

    @Override
    public float calculaValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "codigo:%d nome:%s descricao:%s valor:%.1f potencia:%.1f corrente:%.1f rpm:%d",
                cod, nome, descricao, valor, potencia, corrente, rpm);
    }
}
