package org.iftm.edu.br.tdd.model;

public class FuncionarioTerceirizado extends Funcionario {

    private static final double DESPESA_MAXIMA = 1000.0;

    private double despesasAdicionais;

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        this.despesasAdicionais = validaDespesasAdicionais(despesasAdicionais);
    }

    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        this.despesasAdicionais = validaDespesasAdicionais(despesasAdicionais);
    }

    @Override
    public double calcularPagamento() {
        return calcularPagamentoComDespesa(despesasAdicionais);
    }

    private double validaDespesasAdicionais(double despesasAdicionais) {
        if (despesasAdicionais < 0) {
            throw new IllegalArgumentException("As despesas adicionais não podem ser inferiores a R$ 0,00.");
        }
        if (despesasAdicionais > DESPESA_MAXIMA) {
            throw new IllegalArgumentException("As despesas adicionais não podem ser superiores a R$ 1.000,00.");
        }
        validaPagamento(calcularPagamentoComDespesa(despesasAdicionais));
        return despesasAdicionais;
    }

    private double calcularPagamentoComDespesa(double despesasAdicionais) {
        return super.calcularPagamento() + (despesasAdicionais * 1.1);
    }
}
