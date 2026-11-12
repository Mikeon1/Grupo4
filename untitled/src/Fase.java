import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fase extends JFrame implements KeyListener {

    JPanel caverna;

    JLabel protagonista;
    JLabel slime1;
    JLabel slime2;
    JLabel orc;

    int protagonistaX = 50;
    int protagonistaY = 200;

    public Fase() {

        setTitle("Fase 1 - A Caverna");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        criarFase();

        addKeyListener(this);
        setFocusable(true);

        setVisible(true);
    }

    public void criarFase() {

        caverna = new JPanel(null);
        caverna.setBackground(Color.DARK_GRAY);

        // PROTAGONISTA
        protagonista = new JLabel("P");
        protagonista.setHorizontalAlignment(SwingConstants.CENTER);
        protagonista.setVerticalAlignment(SwingConstants.CENTER);
        protagonista.setForeground(Color.WHITE);
        protagonista.setBackground(Color.BLUE);
        protagonista.setOpaque(true);

        protagonista.setBounds(
                protagonistaX,
                protagonistaY,
                50,
                50
        );

        // SLIME 1
        slime1 = new JLabel("S");
        slime1.setHorizontalAlignment(SwingConstants.CENTER);
        slime1.setVerticalAlignment(SwingConstants.CENTER);
        slime1.setForeground(Color.WHITE);
        slime1.setBackground(Color.GREEN);
        slime1.setOpaque(true);

        slime1.setBounds(
                220,
                200,
                50,
                50
        );

        // SLIME 2
        slime2 = new JLabel("S");
        slime2.setHorizontalAlignment(SwingConstants.CENTER);
        slime2.setVerticalAlignment(SwingConstants.CENTER);
        slime2.setForeground(Color.WHITE);
        slime2.setBackground(Color.GREEN);
        slime2.setOpaque(true);

        slime2.setBounds(
                400,
                200,
                50,
                50
        );

        // ORC - MINI BOSS
        orc = new JLabel("O");
        orc.setHorizontalAlignment(SwingConstants.CENTER);
        orc.setVerticalAlignment(SwingConstants.CENTER);
        orc.setForeground(Color.WHITE);
        orc.setBackground(Color.RED);
        orc.setOpaque(true);

        orc.setBounds(
                600,
                200,
                60,
                60
        );

        // ADICIONANDO NA FASE
        caverna.add(protagonista);
        caverna.add(slime1);
        caverna.add(slime2);
        caverna.add(orc);

        add(caverna);
    }

    public void moverProtagonista(int x, int y) {

        protagonistaX = x;
        protagonistaY = y;

        // Impede sair da tela pela esquerda
        if (protagonistaX < 0) {
            protagonistaX = 0;
        }

        // Impede sair da tela pelo topo
        if (protagonistaY < 0) {
            protagonistaY = 0;
        }

        // Impede sair pela direita
        if (protagonistaX > caverna.getWidth() - protagonista.getWidth()) {
            protagonistaX =
                    caverna.getWidth() - protagonista.getWidth();
        }

        // Impede sair por baixo
        if (protagonistaY > caverna.getHeight() - protagonista.getHeight()) {
            protagonistaY =
                    caverna.getHeight() - protagonista.getHeight();
        }

        protagonista.setLocation(
                protagonistaX,
                protagonistaY
        );

        verificarColisao();
    }

    public void verificarColisao() {

        if (protagonista.getBounds().intersects(slime2.getBounds())) {

            System.out.println("Você encontrou o segundo Slime!");

        }

        if (protagonista.getBounds().intersects(orc.getBounds())) {

            System.out.println("Você encontrou o ORC - MINI BOSS!");

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();

        int velocidade = 10;

        // CIMA
        if (tecla == KeyEvent.VK_W ||
                tecla == KeyEvent.VK_UP) {

            moverProtagonista(
                    protagonistaX,
                    protagonistaY - velocidade
            );
        }

        // BAIXO
        if (tecla == KeyEvent.VK_S ||
                tecla == KeyEvent.VK_DOWN) {

            moverProtagonista(
                    protagonistaX,
                    protagonistaY + velocidade
            );
        }

        // ESQUERDA
        if (tecla == KeyEvent.VK_A ||
                tecla == KeyEvent.VK_LEFT) {

            moverProtagonista(
                    protagonistaX - velocidade,
                    protagonistaY
            );
        }

        // DIREITA
        if (tecla == KeyEvent.VK_D ||
                tecla == KeyEvent.VK_RIGHT) {

            moverProtagonista(
                    protagonistaX + velocidade,
                    protagonistaY
            );
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {

        new Fase();

    }
}