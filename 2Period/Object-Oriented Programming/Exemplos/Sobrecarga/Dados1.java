public class Dados1 {
    private int a, b;
    private float c, d;

    public Dados1(int a, int b){
        this.a = a;
        this.b = b;
}
    public Dados1(float c, float d){
        this.c = c;
        this.d = d;
    }

    public int getA(){
        this.a = a;
        return a;
    }

    public int getB(){
        this.b = b;
        return b;
    }

    public float getC(){
        this.c = c;
        return c;
    }

    public float getD(){
        this.d = d;
        return d;
    }
}