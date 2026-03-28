public class Triangulo extends Figura2D {
    
    public static Triangulo criar() {
        return new Triangulo();
    }
    
    @Override
    public String pegaTexto(){
        return "Possui 3 lados";
    }
}
