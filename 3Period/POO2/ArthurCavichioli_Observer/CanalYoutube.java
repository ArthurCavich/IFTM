
// SUBJECT CONCRETO: implementa o contrato Canal.
import java.util.ArrayList;
import java.util.List;

public class CanalYoutube implements Canal {
    private List<Inscrito> inscritos = new ArrayList<>();

    @Override
    public void inscrever(Inscrito inscrito) {
        inscritos.add(inscrito);
    }

    @Override
    public void cancelarInscricao(Inscrito inscrito) {
        inscritos.remove(inscrito);
    }

    @Override
    public void postarVideo(String video) {
        System.out.println("\nVideo novo: " + video);
        for (Inscrito inscrito : inscritos) {
            inscrito.notificar(video);
        }
    }
}