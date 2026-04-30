public class EmpregadoAssalariado extends Empregado {
    private double salarioSemanal;
    
    public EmpregadoAssalariado(String nome, String sobrenome, String numeroSeguroSocial, double salarioSemanal) {
        super(nome, sobrenome, numeroSeguroSocial);
        if (salarioSemanal <= 0) {
            throw new IllegalArgumentException("Salário semanal deve ser maior que zero");
        }
        this.salarioSemanal = salarioSemanal;
    }
    
    public double getSalarioSemanal() {
        return salarioSemanal;
    }
    
    public void setSalarioSemanal(double salarioSemanal) {
        if (salarioSemanal <= 0) {
            throw new IllegalArgumentException("Salário semanal deve ser maior que zero");
        }
        this.salarioSemanal = salarioSemanal;
    }
    
    public double calculaPagamento() {
        return salarioSemanal;
    }
    
    @Override
    public String toString() {
        return "Empregado Assalariado: " + getNome() + " " + getSobrenome() + "\n" +
               "Número do Seguro Social: " + getNumeroSeguroSocial() + "\n" +
               "Salário Semanal: $" + String.format("%.2f", salarioSemanal);
    }
}
