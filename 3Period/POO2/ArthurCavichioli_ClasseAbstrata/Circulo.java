public class Circulo extends Figura2D {
    
    public static Circulo criar() {
        return new Circulo();
    }
    
    @Override
    public String pegaTexto(){
        return "Não possui lados";
    }
}
