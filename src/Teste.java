import javax.swing.*;
import java.awt.*;

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

        Inventario inventariodoHeroi = new Inventario();
        inventariodoHeroi.adicionarItem(pocao);
        inventariodoHeroi.adicionarItem(armadura);
        inventariodoHeroi.mostrarInventario();

        System.out.println("Gasto de moedas");

        inventariodoHeroi.gastarMoedas(40);
        inventariodoHeroi.mostrarInventario();


        // 1. Cria o teu protagonista (ajusta os parâmetros ao teu construtor)
        Protagonista heroi = new Protagonista("Hugo", 100, 15, 5, 0.1);

        // 2. Adiciona alguns itens que já tens no projeto para testar
        espada = new Espada();
        pocao = new PocaoVida();
        heroi.getInventario().adicionarItem(espada);
        heroi.getInventario().adicionarItem(pocao);

        // 3. Configura a janela do Swing
        JFrame janela = new JFrame("Teste de Integração - Protagonista & Inventário");
        janela.setSize(450, 350);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new BorderLayout(10, 10));

        // 4. Painel central com os dados do herói
        JTextArea areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Atualiza o texto na tela
        Runnable atualizarTela = () -> {
            StringBuilder sb = new StringBuilder();
            sb.append("=== STATUS DO PROTAGONISTA ===\n");
            sb.append("Nome: ").append(heroi.getNome()).append("\n");
            sb.append("Vida: ").append(heroi.getVida()).append("\n\n");
            sb.append("=== INVENTÁRIO ===\n");
            // Mostra os itens guardados
            for (Item item : heroi.getInventario().getItens()) {
                sb.append("- ").append(item.getNome()).append("\n");
            }
            areaInfo.setText(sb.toString());
        };

        atualizarTela.run();

        // 5. Botão para testar interação (ex: simular dano e cura)
        JButton btnTestarDano = new JButton("Simular Dano (-20 Vida)");
        btnTestarDano.addActionListener(e -> {
            heroi.setVida(heroi.getVida() - 20);
            atualizarTela.run();
        });

        janela.add(new JScrollPane(areaInfo), BorderLayout.CENTER);
        janela.add(btnTestarDano, BorderLayout.SOUTH);

        // 6. Exibe a janela
        janela.setVisible(true);
    }
}







