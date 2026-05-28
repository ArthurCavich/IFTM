package parte2.comViolacao.ispviolacao;

/*
 * VIOLAÇÃO DO ISP (Interface Segregation Principle):
 * Uma interface "faz-tudo" (gorda) que força os clientes a implementar o que não precisam.
 * Nem todo dispositivo imprime documentos, mas todos são obrigados a implementar imprimirDocumento().
 *
 * CORREÇÃO: segregar em interfaces menores (ver pacote parte2.refatorado.isp).
 */
public interface DispositivoSuperInteligente {
    void ligar();
    void desligar();
    void imprimirDocumento(String texto); // Nem todo dispositivo imprime!
}
