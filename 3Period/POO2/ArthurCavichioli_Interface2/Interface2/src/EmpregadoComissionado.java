public class EmpregadoComissionado extends Empregado {
    private double vendasBrutasSemanais;
    private double taxaComissao;
    
    public EmpregadoComissionado(String nome, String sobrenome, String numeroSeguroSocial, double vendasBrutasSemanais, double taxaComissao) {
        super(nome, sobrenome, numeroSeguroSocial);
        if (vendasBrutasSemanais <= 0) {
            throw new IllegalArgumentException("Vendas brutas semanais deve ser maior que zero");
        }
        if (taxaComissao < 0 || taxaComissao > 1) {
            throw new IllegalArgumentException("Taxa de comissão deve estar entre 0 e 1 (0% e 100%)");
        }
        this.vendasBrutasSemanais = vendasBrutasSemanais;
        this.taxaComissao = taxaComissao;
    }
    
    public double getVendasBrutasSemanais() {
        return vendasBrutasSemanais;
    }
    
    public void setVendasBrutasSemanais(double vendasBrutasSemanais) {
        if (vendasBrutasSemanais <= 0) {
            throw new IllegalArgumentException("Vendas brutas semanais deve ser maior que zero");
        }
        this.vendasBrutasSemanais = vendasBrutasSemanais;
    }
    
    public double getTaxaComissao() {
        return taxaComissao;
    }
    
    public void setTaxaComissao(double taxaComissao) {
        if (taxaComissao < 0 || taxaComissao > 1) {
            throw new IllegalArgumentException("Taxa de comissão deve estar entre 0 e 1 (0% e 100%)");
        }
        this.taxaComissao = taxaComissao;
    }
    
    public double calculaPagamento() {
        return taxaComissao * vendasBrutasSemanais;
    }
    
    @Override
    public String toString() {
        return "Empregado Comissionado: " + getNome() + " " + getSobrenome() + "\n" +
               "Número do Seguro Social: " + getNumeroSeguroSocial() + "\n" +
               "Vendas Brutas Semanais: $" + String.format("%.2f", vendasBrutasSemanais) + 
               "; Taxa da comissão: " + taxaComissao;
    }
}
