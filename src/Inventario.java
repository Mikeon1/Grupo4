import java.util.ArrayList;
import java.util.List;

public class Inventario {


    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
            System.out.println(item.getNome() + " foi adicionado ao inventário!");
        }
    }

    public void mostrarInventario() {
        System.out.println(" ITENS NO INVENTÁRIO ");
        if (itens.isEmpty()) {
            System.out.println("O inventário está vazio.");
            return;
        }
    }
}


