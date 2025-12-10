public class Especial {
    private float limite;
    private int tempo;

    public Especial (){}

    public Especial (float limite, int tempo){
        this.limite = limite;
        this.tempo = tempo;
    }

    public float defineTaxacao(float saldo){
        if (tempo < 12){
            return 20/100;
        } else if (tempo >= 12 && tempo <=23 && limite <= 0){
            return 20/100;
        } else if (tempo >= 12 && tempo <=23 && limite > 0){
            return 15/100;
        } else if (tempo > 23 && limite <= 0){
            return 15/100;
        } else if (tempo > 23 && limite < 0){
            return 10/100;
        } else {
            return 0;
        }
    }

    public float getLimite() {
        return limite;
    }

}
