import java.util.List;

public class Validacao {

    public static boolean validaSenha(String senha, List<String> senhasAutorizadas) {
        if (senha.equals(senhasAutorizadas)) {
            return true;
        } else {
            return false;
        }
    }

}
