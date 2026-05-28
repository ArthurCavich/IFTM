package comViolacao.ispviolacao;

public class Estagiario implements Funcionario {

    @Override
    public String getCargo() {
        return "Estagiário";
    }

    @Override
    public double calculaSalario() {
        // lógica para calcular o salário do estagiário
        return 0;
    }

    @Override
    public double calcula13o() {
        /*
         * VIOLAÇÃO DO ISP: Estagiário é forçado a implementar calcula13o()
         * por causa da interface Funcionario, mesmo não tendo direito ao 13º.
         * A classe fica com responsabilidade/método que não faz sentido para ela.
         */
        return 0;
    }
}
