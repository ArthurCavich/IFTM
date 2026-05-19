package org.iftm.edu.br.tdd.model;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesasAdicionais;

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        this.despesasAdicionais = despesasAdicionais;
    }

    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        this.despesasAdicionais = despesasAdicionais;
    }

    @Override
    public double calcularPagamento() {
        return super.calcularPagamento() + despesasAdicionais * 1.10;
    }
}
