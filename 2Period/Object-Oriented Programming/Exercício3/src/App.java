import java.util.Scanner;


public class App {

    
    public static void main(String[] args) throws Exception {
        JogadorDeFutebol jogador = new JogadorDeFutebol();
        Scanner sc = new Scanner(System.in);
        leDados(jogador, sc);
        System.out.println(jogador.exibeDados());
        tempoAposentadoria(jogador);
        sc.close();
    }
    
    public static void tempoAposentadoria(JogadorDeFutebol jogador) {
        int idade = jogador.calculaIdade(jogador.nascimento);
        int idadeAposentadoria;
        switch (jogador.posicao) {
            case "Atacante":
                idadeAposentadoria = 35;
                break;
            case "Meio Campo":
                idadeAposentadoria = 38;
                break;
            case "Defesa":
                idadeAposentadoria = 40;
                break;
            default:
                idadeAposentadoria = 65; // valor padrão alto para indicar indefinido
        }

        int faltam = idadeAposentadoria - idade;
        if (faltam > 0) {
            System.out.println("Faltam " + faltam + " anos para o jogador " + jogador.getNome() + " se aposentar.");
        } else if (faltam == 0) {
            System.out.println("O jogador " + jogador.getNome() + " pode se aposentar este ano.");
        } else {
            System.out.println("O jogador " + jogador.getNome() + " já poderia estar aposentado há " + Math.abs(faltam) + " anos.");
        }
    }
    public static void leDados(JogadorDeFutebol jogador, Scanner sc) {
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        jogador.setNome(nome);

        System.out.println("Digite sua nacionalidade:");
        String nacionalidade = sc.nextLine();
        jogador.setNacionalidade(nacionalidade);

        System.out.println("Digite o ano de nascimento:");
        int nascimento = sc.nextInt();
        jogador.setNascimento(nascimento);

        System.out.println("Digite sua posição: (1 - Atacante / 2 - Defesa / 3 - Meio Campo)");
        int posicaoInt = sc.nextInt();
        String posicao;

        posicao = switch (posicaoInt) {
            case 1 -> "Atacante";
            case 2 -> "Defesa";
            case 3 -> "Meio Campo";
            default -> "Opção inválida.";
        };
        jogador.setPosicao(posicao);

        System.out.println("Digite seu peso:");
        double peso = sc.nextDouble();
        jogador.setPeso(peso);

        System.out.println("Digite sua altura:");
        double altura = sc.nextDouble();
        jogador.setAltura(altura);
    }

}

