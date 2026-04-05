package atividade3.tdd;

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
        // Valida se está entre 0 e 1000
        if (despesasAdicionais < 0 || despesasAdicionais > 1000) {
            throw new IllegalArgumentException("Despesa adicional deve estar entre 0 e 1000");
        }
        
        // Calcula o que seria o pagamento com essa despesa
        double pagamentoBase = super.calcularPagamento();
        double bonus = despesasAdicionais * 1.1; // 110% da despesa
        double pagamentoTotal = pagamentoBase + bonus;
        
        // Verifica se ultrapassa o máximo de 10000
        if (pagamentoTotal > 10000.0) {
            throw new IllegalArgumentException("Despesa adicional causaria pagamento acima do limite de 10000");
        }
        
        this.despesasAdicionais = despesasAdicionais;
    }

    @Override
    public double calcularPagamento() {
        // Pagamento base (horas * valor/hora + limites)
        double pagamentoBase = super.calcularPagamento();
        
        // Bônus de 110% da despesa adicional
        double bonus = despesasAdicionais * 1.1;
        double total = pagamentoBase + bonus;
        
        // Respeita o limite máximo de 10000
        if (total > 10000.0) {
            return 10000.0;
        }
        
        return total;
    }
}
