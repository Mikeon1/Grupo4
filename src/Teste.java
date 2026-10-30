public class Teste {

    public static void main(String[] args) {
        // testando métodos
        System.out.println(" Testando poção de vida");
        PocaoVida pocao = new PocaoVida();
        pocao.mostrarInformacoes();
        System.out.println("Está esgotado? " + pocao.acabou());

        pocao.aumentarQuantidade(-1);

        System.out.println("Após usar a poção");
        System.out.println("Está esgotado? " + pocao.acabou());

        System.out.println();

        System.out.println("Testando espada");
        Espada espada = new Espada();
        espada.mostrarInformacoes();
        System.out.println("Nome: " + espada.getNome());

        System.out.println();

        System.out.println(" Testando armadura ");
        ArmaduraBasica armadura = new ArmaduraBasica();
        armadura.mostrarInformacoes();

        System.out.println();

        System.out.println(" Testando alteração de quantidade ");
        System.out.println("Aumentando a quantidade da poção em +3...");
        pocao.aumentarQuantidade(3);
        pocao.mostrarInformacoes();

        System.out.println();

        // Testando classe inventário
        System.out.println("Testando inventário");

        Inventario inventariodoHeroi = new  Inventario();
        inventariodoHeroi.adicionarItem(pocao);
        inventariodoHeroi.adicionarItem(armadura);
        inventariodoHeroi.mostrarInventario();

        System.out.println("Gasto de moedas");

        inventariodoHeroi.gastarMoedas(40);
        inventariodoHeroi.mostrarInventario();
        }
    }


