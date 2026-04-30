public class Fatura {
    private final String numeroPeca;
    private final String descricaoPeca;
    private int quantidade;
    private double precoItem;
    
    public Fatura(String numeroPeca, String descricaoPeca, int quantidade, double precoItem) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (precoItem <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.numeroPeca = numeroPeca;
        this.descricaoPeca = descricaoPeca;
        this.quantidade = quantidade;
        this.precoItem = precoItem;
    }
    
    public String getNumeroPeca() {
        return numeroPeca;
    }
    
    public String getDescricaoPeca() {
        return descricaoPeca;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantidade = quantidade;
    }
    
    public double getPrecoItem() {
        return precoItem;
    }
    
    public void setPrecoItem(double precoItem) {
        if (precoItem <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.precoItem = precoItem;
    }
    
    @Override
    public String toString() {
        return "Fatura:\n" +
               "Número da peça: " + numeroPeca + " (assento)\n" +
               "Quantidade: " + quantidade + "\n" +
               "Preço por item: $" + String.format("%.2f", precoItem);
    }
    
    public double calculaPagamento() {
        return quantidade * precoItem;
    }
}
