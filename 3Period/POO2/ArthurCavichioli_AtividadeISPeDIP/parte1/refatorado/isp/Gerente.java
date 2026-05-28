package isp;

public class Gerente implements Funcionario, Remuneravel, Beneficio13o {

    @Override
    public String getCargo() {
        return "Gerente";
    }

    @Override
    public double calculaSalario() {
        // lógica para calcular o salário do gerente
        return 0;
    }

    @Override
    public double calcula13o() {
        // lógica para calcular o décimo terceiro salário do gerente
        return 0;
    }
}
