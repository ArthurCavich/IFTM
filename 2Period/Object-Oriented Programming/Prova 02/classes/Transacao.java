import java.util.ArrayList;


public class Transacao {
    private ArrayList<Movimento> movimentos;

    public Transacao() {

    }

    public boolean realizarTransacao(String data, Conta conta, String historico, float valor, int operacao) {
        Movimento objeto = new Movimento(data, conta, historico, valor, operacao);

        if (objeto.movimentar()) {
            movimentos.add(objeto);
            return true;
        } else {
            return false;
        }

    }

    public boolean realizarTransacao(String data, Conta conta, Especial especial, String historico, float valor,
            int operacao) {
        Movimento objeto = new Movimento(data, conta, historico, valor, operacao);

        if (objeto.movimentar()) {
            movimentos.add(objeto);
            return true;
        } else {
            return false;
        }
    }

    public void estornartransacao(){
        movimentos = null;
    }

    public ArrayList<Movimento> getMovimentos(){
        return movimentos;
    }

}
