package comViolacao.ispviolacao;

/*
 * VIOLAÇÃO DO ISP (Interface Segregation Principle):
 * Esta interface "gorda" agrupa métodos que nem todos os funcionários precisam implementar.
 * O Estagiário é obrigado a implementar calcula13o(), embora não tenha direito ao 13º salário,
 * resultando em implementação vazia ou inválida (retorno 0).
 *
 * CORREÇÃO: segregar em interfaces menores (ver pacote isp).
 */
public interface Funcionario {
    String getCargo();
    double calculaSalario();
    double calcula13o();
}
