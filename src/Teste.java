public class Teste {

    public static void main(String[] args) {

        System.out.println(" Testando poção de vida");
        PocaoVida pocao = new PocaoVida();
        pocao.mostrarInformacoes();
        System.out.println("Está esgotado? " + pocao.acabou());

        pocao.aumentarQuantidade(-1);

        System.out.println("Após usar a poção");
        System.out.println("Está esgotado? " + pocao.acabou());

        System.out.println("Testando espada");
        Espada espada = new Espada();
        espada.mostrarInformacoes();
        System.out.println("Nome: " + espada.getNome());

        System.out.println(" Testando armadura ");
        ArmaduraBasica armadura = new ArmaduraBasica();
        armadura.mostrarInformacoes();

        System.out.println(" Testando alteração de quantidade ");
        System.out.println("Aumentando a quantidade da poção em +3...");
        pocao.aumentarQuantidade(3);
        pocao.mostrarInformacoes();


         }
    }


