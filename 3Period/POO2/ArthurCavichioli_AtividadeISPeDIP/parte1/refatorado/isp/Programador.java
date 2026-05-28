package isp;

public class Programador implements Funcionario, Remuneravel, Beneficio13o {

    @Override
    public String getCargo() {
        return "Programador";
    }

    @Override
    public double calculaSalario() {
        // lógica para calcular o salário do programador
        return 0;
    }

    @Override
    public double calcula13o() {
        // lógica para calcular o décimo terceiro salário do programador
        return 0;
    }
}
