package isp;

public class IspCorrecao {

    public static void main(String[] args) {
        Funcionario estagiario = new Estagiario();
        Beneficio13o programador = new Programador();

        System.out.println(estagiario.getCargo() + " - salário: " + ((Remuneravel) estagiario).calculaSalario());
        System.out.println("Programador - 13º: " + programador.calcula13o());
    }
}
