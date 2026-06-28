// Monta a cena e testa o padrao Observer.
public class Main {
    public static void main(String[] args) {
        CanalYoutube canalArthur = new CanalYoutube();

        Inscrito jose = new Usuario("José");
        Inscrito maria  = new Usuario("Maria");

        canalArthur.inscrever(jose);
        canalArthur.inscrever(maria);

        canalArthur.postarVideo("Aula de POO");

        canalArthur.cancelarInscricao(maria);

        canalArthur.postarVideo("Padrao Observer");
    }
}
