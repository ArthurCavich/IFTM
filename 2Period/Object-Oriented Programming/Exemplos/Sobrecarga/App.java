import javax.swing.JOptionPane;

public class App {
    
    public static int leValor(int a){
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um número inteiro:"));
        return num;
    }

    public static float leValor(float b){
        float num = Float.parseFloat(JOptionPane.showInputDialog("Digite um número real:"));
        return num;
    }

    public static void menu(){
        int op;
        do { 
            op = Integer.parseInt(JOptionPane.showInputDialog("Menu\n\n1- Inteiros\n2- Reais\n3 - Sair"));
            switch (op) {
                case 1: 
                    a = leValor(a);
                    b = leValor(b);
                    Dados1 obj1 = new Dados1(a, b);
                    Adicao1 ad1 = new Adicao1();
                    JOptionPane.showMessageDialog(null, ad1.exibe(obj1.getA(), obj1.getB(), ad1.add(obj1.getA(), obj1.getB())), "Saída", 1);
                    break;
                case 2:
                    obj1 = new Dados1(leValor(obj1.getC(), leValor(obj1.getD()));
                    ad1 = new Adicao1();
                    JOptionPane.showMessageDialog(null, ad1.exibe(obj1.getC(), obj1.getD(), ad1.add(obj1.getC(), obj1.getD())), "Saída", 1);
                    break;
                case 3:
                    // Exit the loop
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (op == 3);
    }
    public static void main(String[] args) {
        menu();
    }
}
