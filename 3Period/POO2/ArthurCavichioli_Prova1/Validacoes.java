public class Validacoes {
    public static boolean validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        for (int i = 0; i < cpf.length(); i++) {
            char c = cpf.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static void validarValorPositivo(double valor) throws CalculoInvalidoException {
        if (valor < 0) {
            throw new CalculoInvalidoException("Valor negativo inválido: " + valor);
        }
    }
}
