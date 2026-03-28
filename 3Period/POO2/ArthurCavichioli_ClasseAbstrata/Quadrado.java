public class Quadrado extends Figura2D {
    
    public static Quadrado criar() {
        return new Quadrado();
    }
    
    @Override
    public String pegaTexto(){
        return "Possui 4 lados";
    }
}
