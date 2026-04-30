public class EmpregadoMisto extends EmpregadoComissionado {
    private double salarioBase;
    
    public EmpregadoMisto(String nome, String sobrenome, String numeroSeguroSocial, double vendasBrutasSemanais, double taxaComissao, double salarioBase) {
        super(nome, sobrenome, numeroSeguroSocial, vendasBrutasSemanais, taxaComissao);
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("Salário base deve ser maior que zero");
        }
        this.salarioBase = salarioBase;
    }
    
    public double getSalarioBase() {
        return salarioBase;
    }
    
    public void setSalarioBase(double salarioBase) {
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("Salário base deve ser maior que zero");
        }
        this.salarioBase = salarioBase;
    }
    
    @Override
    public double calculaPagamento() {
        return salarioBase + super.calculaPagamento();
    }
    
    @Override
    public String toString() {
        return "Empregado Misto: " + getNome() + " " + getSobrenome() + "\n" +
               "Número do Seguro Social: " + getNumeroSeguroSocial() + "\n" +
               "Vendas Brutas Semanais: $" + String.format("%.2f", getVendasBrutasSemanais()) + 
               "; Taxa da Comissão: " + getTaxaComissao() + 
               "; Salário-base: $" + String.format("%.2f", salarioBase);
    }
}
