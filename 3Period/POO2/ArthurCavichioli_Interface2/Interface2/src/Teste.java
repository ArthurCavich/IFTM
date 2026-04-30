public class Teste {
    public static void main(String[] args) {
        // SEM POLIMORFISMO
        System.out.println("=== SEM POLIMORFISMO ===\n");
        
        EmpregadoAssalariado empAssalariado = new EmpregadoAssalariado("João", "Silva", "111-11-1111", 800.00);
        System.out.println(empAssalariado);
        System.out.println("Pagamento: $" + String.format("%.2f", empAssalariado.calculaPagamento()));
        System.out.println();
        
        EmpregadoHorista empHorista = new EmpregadoHorista("Maria", "Santos", "222-22-2222", 16.75, 40.00);
        System.out.println(empHorista);
        System.out.println("Pagamento: $" + String.format("%.2f", empHorista.calculaPagamento()));
        System.out.println();
        
        EmpregadoComissionado empComissionado = new EmpregadoComissionado("Sue", "Jones", "333-33-3333", 10000.00, 0.06);
        System.out.println(empComissionado);
        System.out.println("Pagamento: $" + String.format("%.2f", empComissionado.calculaPagamento()));
        System.out.println();
        
        EmpregadoMisto empMisto = new EmpregadoMisto("Bob", "Lewis", "444-44-4444", 10000.00, 0.06, 300.00);
        System.out.println(empMisto);
        System.out.println("Pagamento: $" + String.format("%.2f", empMisto.calculaPagamento()));
        System.out.println();
        
        // COM POLIMORFISMO
        System.out.println("\n=== COM POLIMORFISMO ===\n");
        
        Empregado[] empregados = new Empregado[4];
        empregados[0] = new EmpregadoAssalariado("João", "Silva", "111-11-1111", 800.00);
        empregados[1] = new EmpregadoHorista("Maria", "Santos", "222-22-2222", 16.75, 40.00);
        empregados[2] = new EmpregadoComissionado("Sue", "Jones", "333-33-3333", 10000.00, 0.06);
        empregados[3] = new EmpregadoMisto("Bob", "Lewis", "444-44-4444", 10000.00, 0.06, 300.00);
        
        for (Empregado emp : empregados) {
            System.out.println(emp);
            System.out.println("Pagamento: $" + String.format("%.2f", calcularPagamento(emp)));
            System.out.println();
        }
    }
    
    public static double calcularPagamento(Empregado emp) {
        if (emp instanceof EmpregadoMisto) {
            EmpregadoMisto empMisto = (EmpregadoMisto) emp;
            double salarioBase = empMisto.getSalarioBase();
            double acrescimo = salarioBase * 0.10; // 10% de acréscimo
            System.out.println("Acréscimo de 10% no salário-base: $" + String.format("%.2f", acrescimo));
            return empMisto.calculaPagamento() + acrescimo;
        } else if (emp instanceof EmpregadoAssalariado) {
            return ((EmpregadoAssalariado) emp).calculaPagamento();
        } else if (emp instanceof EmpregadoHorista) {
            return ((EmpregadoHorista) emp).calculaPagamento();
        } else if (emp instanceof EmpregadoComissionado) {
            return ((EmpregadoComissionado) emp).calculaPagamento();
        }
        return 0;
    }
}
