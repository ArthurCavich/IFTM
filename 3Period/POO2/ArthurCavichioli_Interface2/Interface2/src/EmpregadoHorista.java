public class EmpregadoHorista extends Empregado {
    private double salarioHora;
    private double horasTrabalhadas;
    
    public EmpregadoHorista(String nome, String sobrenome, String numeroSeguroSocial, double salarioHora, double horasTrabalhadas) {
        super(nome, sobrenome, numeroSeguroSocial);
        if (salarioHora <= 0) {
            throw new IllegalArgumentException("Salário por hora deve ser maior que zero");
        }
        if (horasTrabalhadas <= 0) {
            throw new IllegalArgumentException("Horas trabalhadas deve ser maior que zero");
        }
        this.salarioHora = salarioHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }
    
    public double getSalarioHora() {
        return salarioHora;
    }
    
    public void setSalarioHora(double salarioHora) {
        if (salarioHora <= 0) {
            throw new IllegalArgumentException("Salário por hora deve ser maior que zero");
        }
        this.salarioHora = salarioHora;
    }
    
    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }
    
    public void setHorasTrabalhadas(double horasTrabalhadas) {
        if (horasTrabalhadas <= 0) {
            throw new IllegalArgumentException("Horas trabalhadas deve ser maior que zero");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }
    
    public double calculaPagamento() {
        if (horasTrabalhadas <= 40) {
            return salarioHora * horasTrabalhadas;
        } else {
            return 40 * salarioHora + (horasTrabalhadas - 40) * salarioHora * 1.5;
        }
    }
    
    @Override
    public String toString() {
        return "Empregado Horista: " + getNome() + " " + getSobrenome() + "\n" +
               "Número do Seguro Social: " + getNumeroSeguroSocial() + "\n" +
               "Valor por Hora: $" + String.format("%.2f", salarioHora) + 
               ", Horas Trabalhadas: " + horasTrabalhadas;
    }
}
