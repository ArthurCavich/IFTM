
public class Cidade {
    //atributos da classe encapsuladas
    private String nome, adjetivo, estado;
    //construtor
    public Cidade (String nome, String adjetivo, String estado){
        this.nome = nome;
        this.adjetivo = adjetivo;
        this.estado = estado;
    }
    //get e set

    public String getNome(){
        return nome;
    }

    public String getAdjetivo(){
        return adjetivo;
    }

    public String getEstado(){
        return estado;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setAdjetivo(String adjetivo){
        this.adjetivo = adjetivo;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
}
