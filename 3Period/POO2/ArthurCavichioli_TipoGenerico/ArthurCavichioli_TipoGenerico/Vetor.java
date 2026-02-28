
public class Vetor<T> {

    private T[] vet;
    private int tamanho;

    public Vetor(int tamanho) {
        this.tamanho = tamanho;
        this.vet = (T[]) new Object[tamanho]; //aqui eu crio o vetor genérico com o tamanho passado no parâmetro
    }

    //get do vet, onde T é o tipo
    public T[] getVet() {
        return vet;
    }

    public void setVet(T[] vet) {
        this.vet = vet;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public T getElemento(int indice) {
        return this.vet[indice];
    }

    public void setElemento(int indice, T elemento) {
        this.vet[indice] = elemento;
    }

}
