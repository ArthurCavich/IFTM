package prodPlan;

public class Teste {

    static Parte[] criaPartes() {
        Parte[] partes = new Parte[8];
        
        // Motors
        partes[0] = new Motor(112, "motor m112", "motor de avanco do cabecote", 100.0f, 1.2f, 1.1f, 1250);
        partes[1] = new Motor(114, "motor m114", "motor auxiliar", 60.0f, 0.6f, 0.8f, 1250);
        partes[2] = new Motor(111, "motor m111", "motor de ventilador", 70.0f, 1.0f, 1.0f, 3000);
        partes[3] = new Motor(110, "motor m110", "motor principal", 120.0f, 1.8f, 1.5f, 1250);
        
        // Parafusos
        partes[4] = new Parafuso(231, "parafuso p1", "parafuso de fixacao do cabecote", 2.5f, 100.0f, 8.0f);
        partes[5] = new Parafuso(232, "parafuso p2", "parafuso de fixacao do motor", 2.5f, 80.0f, 6.0f);
        partes[6] = new Parafuso(233, "parafuso p3", "parafuso de fixacao do ventilador", 2.0f, 60.0f, 6.0f);
        partes[7] = new Parafuso(234, "parafuso p4", "parafuso de uso geral", 3.0f, 120.0f, 12.0f);
        
        return partes;
    }

    static void listaPartes(String titulo, Parte[] partes) {
        System.out.println(titulo);
        for (Parte p : partes) {
            System.out.println(p.toString());
        }
    }

    static Item[] criaItens(Parte[] partes) {
        Item[] itens = new Item[4];
        
        itens[0] = new Item(partes[0], 10);  // motor m112, quantidade 10
        itens[1] = new Item(partes[5], 50);  // parafuso p2, quantidade 50
        itens[2] = new Item(partes[7], 30);  // parafuso p4, quantidade 30
        itens[3] = new Item(partes[2], 5);   // motor m111, quantidade 5
        
        return itens;
    }

    static void listaItens(String titulo, Item[] itens) {
        System.out.println(titulo);
        float valorTotal = 0;
        for (Item i : itens) {
            System.out.println(i.toString());
            valorTotal += i.calculaValor();
        }
        System.out.printf(java.util.Locale.US, "Valor total:%.1f%n", valorTotal);
    }

    public static void main(String[] args) {
        Parte[] partes = criaPartes();
        Item[] itens = criaItens(partes);
        
        listaPartes("*** Partes utilizadas na producao ****", partes);
        System.out.println();
        listaItens("*** Itens solicitados ***", itens);
    }
}
