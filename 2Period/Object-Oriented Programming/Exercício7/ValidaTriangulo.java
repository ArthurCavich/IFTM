public class ValidaTriangulo {
    // verifica se três lados formam um triângulo válido
    public static boolean verifica(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return false;
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
}
