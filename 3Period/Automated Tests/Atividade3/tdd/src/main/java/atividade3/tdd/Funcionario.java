package atividade3.tdd;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    public double calcularPagamento() {
        // Aplica regras de limites
        int horas = horasTrabalhadas;
        double valor = valorHora;
        
        // Se valorHora é muito alto, usa máximo de horas
        if (valor >= 150.0) {
            horas = 160;
        }
        
        double pagamento = horas * valor;
        
        // Respeita mínimo e máximo de pagamento
        if (pagamento < 1518.0) {
            pagamento = 1518.0;
        }
        if (pagamento > 10000.0) {
            pagamento = 10000.0;
        }
        
        return pagamento;
    }

    // ===== GETTERS E SETTERS =====
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 20 || horasTrabalhadas > 160) {
            throw new IllegalArgumentException("Horas trabalhadas deve ser entre 20 e 160");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        // 1% de 1518 = 15.18, 10% de 1518 = 151.80
        if (valorHora < 15.18 || valorHora > 151.80) {
            throw new IllegalArgumentException("Valor hora deve estar entre 15.18 e 151.80");
        }
        this.valorHora = valorHora;
    }
}
