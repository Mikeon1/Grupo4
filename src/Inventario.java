import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private List<Item> itens = new ArrayList<>();
    private int saldoMoedas = 100;

    // Adiciona um item
    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
            System.out.println(item.getNome() + " foi adicionado ao inventário!");
        }
    }

    // Remove um item
    public void removerItem(Item item) {
        if (itens.contains(item)) {
            itens.remove(item);
            System.out.println(item.getNome() + " foi removido do inventário.");
        } else {
            System.out.println("Item não encontrado no inventário.");
        }
    }

    // Mostra os itens
    public void mostrarInventario() {
        System.out.println(" ITENS NO INVENTÁRIO ");
        if (itens.isEmpty()) {
            System.out.println("O inventário está vazio.");
            return;
        }

        for (Item item : itens) {
            item.mostrarInformacoes();
            System.out.println("                    ");
        }
    }

    // Getters
    public List<Item> getItens() {
        return List.copyOf(itens); // Encapsulamento
    }

    public int getSaldoMoedas() {
        return saldoMoedas;
    }

    // Método para receber moedas
    public void receberMoedas(int valor) {
        if (valor > 0) {
            saldoMoedas += valor;
            System.out.println("Você recebeu " + valor + " moedas. Saldo atual: " + saldoMoedas);
        }
    }

    // Método para gastar moedas
    public void gastarMoedas(int valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para gasto.");
            return;
        }
        if (saldoMoedas >= valor) {
            saldoMoedas -= valor;
            System.out.println("Compra realizada. Restam " + saldoMoedas + " moedas.");
        } else {
            System.out.println("Moedas insuficientes.");
        }
    }
}