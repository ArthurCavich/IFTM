
import java.util.Scanner;
import java.util.HashSet;
import java.util.Objects;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //criei um hashset passando como parâmetro a classe Aluno
        HashSet<Aluno> alunos = new HashSet<>();

        //criei as variáveis de manipulação
        String matricula, nome;

        //programa roda enquanto for verdadeiro
        while (true) {
            matricula = sc.nextLine();

            if (matricula.equals("0")) {
                break;
            }
            nome = sc.nextLine();

            Aluno aluno = new Aluno(matricula, nome);
            alunos.add(aluno);

        }
        System.out.println("Alunos:");
        for (Aluno a : alunos) {
            System.out.println(a.getNome());
        }

        sc.close();
    }
}