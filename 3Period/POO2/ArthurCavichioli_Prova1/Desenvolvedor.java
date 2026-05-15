public class Desenvolvedor extends Operacional {
    public Desenvolvedor(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalarioLiquido() throws CalculoInvalidoException {
        Validacoes.validarValorPositivo(salarioBase);
        return salarioBase * 1.05 * 0.89;
    }
}
