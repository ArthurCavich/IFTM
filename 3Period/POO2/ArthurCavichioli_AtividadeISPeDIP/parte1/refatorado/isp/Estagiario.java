package isp;

public class Estagiario implements Funcionario, Remuneravel {

    @Override
    public String getCargo() {
        return "Estagiário";
    }

    @Override
    public double calculaSalario() {
        // lógica para calcular o salário do estagiário
        return 0;
    }
}
