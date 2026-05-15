public class App {
    public static void main(String[] args) {
        String cpf = "42569325856";

        if (!Validacoes.validarCpf(cpf)) {
            System.out.println("CPF inválido. Encerrando a aplicação.");
            return;
        }

        Gerente arthur = new Gerente("Arthur", 8000.0, new Departamento("TI"));
        arthur.setEndereco("Rua Domingues dos Santos Gomes, 360");
        arthur.registrarKpi(20);

        Desenvolvedor tui = new Desenvolvedor("Tui", 5000.0);

        Remuneravel[] folhaPagamento = { arthur, tui };

        System.out.println("--- PROCESSAMENTO DA FOLHA ---");
        for (Remuneravel r : folhaPagamento) {
            try {
                double liquido = r.calcularSalarioLiquido();
                System.out.println("Salário Líquido Processado: "
                        + String.format("R$ %.2f", liquido));
            } catch (CalculoInvalidoException e) {
                System.out.println("Erro no cálculo do salário: " + e.getMessage());
            }
        }
        System.out.println("____________________________");
        System.out.println("Total de colaboradores: " + Colaborador.getTotalColaboradores());
    }
}
