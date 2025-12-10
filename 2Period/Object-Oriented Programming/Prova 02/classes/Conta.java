public class Conta {
    private int numero;
    private Cliente correntista;
    private float saldo;

    // construtor comum
    public Conta() {

    }

    // construtor sobrecarregado
    public Conta(int numero, Cliente cliente, float saldo) {
        this.numero = numero;
        this.correntista = cliente;
        this.saldo = saldo;
    }

    public void depositar(float valor) {
        saldo += valor;
    }

    public void depositar(float valor, Especial taxa) {
        if (taxa.getLimite() > 0) {
            saldo += valor;
        } else {
            saldo += valor - (valor * (5 / 100));
        }
    }

    // sacar comum
    public boolean sacar(float valor) {
        float taxa = valor - (valor * (5 / 100));
        if (saldo < (valor + taxa)) {
            toString();
            return false; 
        } else {
            return true;
        }
    }

    // sacar especial
    public boolean sacar(float valor, Especial taxa) {
        if (saldo < (valor + taxa.defineTaxacao(valor))) {
            return false; // mensagem de erro
        } else {
            saldo = saldo - valor - (valor * taxa.defineTaxacao(valor));
            return true; // mensagem de sucesso
        }
    }

    public boolean movimentar(float valor, int numero) {
        if (numero == Movimento.sacar) {
            sacar(valor);
            return true;
        } else if (numero == Movimento.depositar) {
            depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean movimentar(float valor, int numero, Especial taxa) {
        if (numero == Movimento.sacar) {
            sacar(valor, taxa);
            return true;
        } else if (numero == Movimento.depositar) {
            depositar(valor, taxa);
            return true;
        } else {
            return false;
        }
    }

    public float getSaldo() {
        return saldo;
    }
}
