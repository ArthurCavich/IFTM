public class Gerente extends CargoEstrategico {
    private final Departamento departamento;
    private Endereco endereco;

    public Gerente(String nome, double salarioBase, Departamento departamento) {
        super(nome, salarioBase);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(String logradouro) {
        this.endereco = new Endereco(logradouro);
    }

    @Override
    public double calcularSalarioLiquido() throws CalculoInvalidoException {
        Validacoes.validarValorPositivo(salarioBase);
        double bruto = salarioBase + (salarioBase * (kpiScore / 100));
        double imposto = (bruto > 7000) ? bruto * 0.275 : (bruto > 3000) ? bruto * 0.15 : 0;
        return bruto - imposto;
    }
}
