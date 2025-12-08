import java.util.Objects;

class Aluno {

    //classe Aluno como pede no exercício
    private String matricula, nome; //dados privados

    public Aluno(String matricula, String nome) {

        this.matricula = matricula;
        this.nome = nome;
    }

    // métodos get matricula e nome
    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    //override solicitado no exercício
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return matricula.equals(((Aluno) o).getMatricula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}