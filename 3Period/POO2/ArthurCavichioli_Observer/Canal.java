// SUBJECT: contrato do canal observado.
public interface Canal {
    void inscrever(Inscrito inscrito);
    void cancelarInscricao(Inscrito inscrito);
    void postarVideo(String video);
}
