import java.awt.Frame;

public class Principal {

    public static void main(String[] args) {

        Frame janela = new Frame("Operação Guerra");

        Jogo jogo = new Jogo();

        janela.add(jogo);
        janela.setSize(800, 600);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        janela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        janela.setVisible(true);

    }
}