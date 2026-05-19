package org.iftm.edu.br.tdd.model;

public class Funcionario {

    private static final double SALARIO_MINIMO = 1518.0;
    private static final double PAGAMENTO_MAXIMO = 10000.0;
    private static final int HORAS_MINIMAS = 5;
    private static final int HORAS_MAXIMAS = 40;
    private static final double VALOR_HORA_MINIMO = 15.18;
    private static final double VALOR_HORA_MAXIMO = 151.80;
    private static final int SEMANAS_NO_MES = 4;

    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        int horasValidadas = validaHorasTrabalhadas(horasTrabalhadas);
        double valorValidado = validaValorHora(valorHora);
        validaPagamento(calcularPagamento(horasValidadas, valorValidado));
        this.horasTrabalhadas = horasValidadas;
        this.valorHora = valorValidado;
    }

    public double calcularPagamento() {
        return calcularPagamento(horasTrabalhadas, valorHora);
    }

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
        int horasValidadas = validaHorasTrabalhadas(horasTrabalhadas);
        validaPagamento(calcularPagamento(horasValidadas, valorHora));
        this.horasTrabalhadas = horasValidadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        double valorValidado = validaValorHora(valorHora);
        validaPagamento(calcularPagamento(horasTrabalhadas, valorValidado));
        this.valorHora = valorValidado;
    }

    private double calcularPagamento(int horas, double valor) {
        return horas * valor * SEMANAS_NO_MES;
    }

    private int validaHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < HORAS_MINIMAS || horasTrabalhadas > HORAS_MAXIMAS) {
            throw new IllegalArgumentException(
                    "O número de horas trabalhadas por funcionários próprios deve ser um valor entre 5 e 40.");
        }
        return horasTrabalhadas;
    }

    private double validaValorHora(double valorHora) {
        if (valorHora < VALOR_HORA_MINIMO || valorHora > VALOR_HORA_MAXIMO) {
            throw new IllegalArgumentException("O valor por hora deve ser um valor entre 15,18 e 151,80.");
        }
        return valorHora;
    }

    protected void validaPagamento(double pagamento) {
        if (pagamento < SALARIO_MINIMO) {
            throw new IllegalArgumentException("O pagamento não pode ser inferior a R$ 1.518,00.");
        }
        if (pagamento > PAGAMENTO_MAXIMO) {
            throw new IllegalArgumentException("O pagamento não pode ser superior a R$ 10.000,00.");
        }
    }
}
