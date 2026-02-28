
public class Pessoa {

    private String nome, sexo, naturalidade;

    public Pessoa(String nome, String sexo, String naturalidade) {
        this.nome = nome;
        this.sexo = sexo;
        this.naturalidade = naturalidade;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo(){
        return sexo;
    }

    public String getNaturalidade(){
        return naturalidade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSexo(String sexo){
        this.sexo = sexo;
    }

    public void setNaturalidade(String naturalidade){
        this.naturalidade = naturalidade;
    }

}
