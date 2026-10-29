import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimuladorForca extends JPanel implements MouseListener {

    // =====================================================
    // CONFIGURAÇÕES
    // =====================================================

    static final int LARGURA = 900;
    static final int ALTURA = 600;

    // =====================================================
    // STATUS DO JOGADOR
    // =====================================================

    String nome = "Guerreiro";

    double forca = 0;
    double energia = 100;

    int nivel = 50;
    int moedas = 9999999;

    int treino = 1;

    // =====================================================
    // EQUIPAMENTOS
    // =====================================================

    boolean halter = false;
    boolean barra = false;
    boolean supino = false;
    boolean equipamentoDourado = false;

    // =====================================================
    // ESTADO
    // =====================================================

    boolean lojaAberta = false;

    String mensagem = "Clique em TREINAR para ganhar força!";

    Timer timer;

    // =====================================================
    // CONSTRUTOR
    // =====================================================

    public SimuladorForca() {

        setPreferredSize(
                new Dimension(
                        LARGURA,
                        ALTURA
                )
        );

        setFocusable(true);

        addMouseListener(this);

        timer = new Timer(
                50,
                e -> atualizar()
        );

        timer.start();
    }

    // =====================================================
    // ATUALIZAÇÃO
    // =====================================================

    void atualizar() {

        if (energia < 100) {

            energia += 0.05;

            if (energia > 100) {
                energia = 100;
            }
        }

        repaint();
    }

    // =====================================================
    // TREINAR
    // =====================================================

    void treinar() {

        if (energia < 5) {

            mensagem =
                    "Você está cansado! Espere recuperar energia.";

            return;
        }

        energia -= 5;

        double ganho = treino;

        // Bônus dos equipamentos

        if (halter) {
            ganho *= 1.5;
        }

        if (barra) {
            ganho *= 2;
        }

        if (supino) {
            ganho *= 3;
        }

        if (equipamentoDourado) {
            ganho *= 10;
        }

        forca += ganho;

        moedas += 1;

        verificarNivel();

        mensagem =
                "+" +
                        String.format(
                                "%.1f",
                                ganho
                        ) +
                        " força!";
    }

    // =====================================================
    // LEVEL UP
    // =====================================================

    void verificarNivel() {

        double necessario =
                nivel * 100;

        if (forca >= necessario) {

            nivel++;

            treino++;

            mensagem =
                    "SUBIU DE NÍVEL! Agora você está no nível "
                            + nivel
                            + "!";

            moedas += 25;
        }
    }

    // =====================================================
    // LOJA
    // =====================================================

    void comprarHalter() {

        if (halter) {

            mensagem =
                    "Você já possui o halter.";

            return;
        }

        if (moedas >= 50) {

            moedas -= 50;

            halter = true;

            mensagem =
                    "Halter comprado! +50% força.";
        } else {

            mensagem =
                    "Você precisa de 50 moedas.";
        }
    }

    void comprarBarra() {

        if (barra) {

            mensagem =
                    "Você já possui a barra.";

            return;
        }

        if (moedas >= 150) {

            moedas -= 150;

            barra = true;

            mensagem =
                    "Barra comprada! +100% força.";
        } else {

            mensagem =
                    "Você precisa de 150 moedas.";
        }
    }

    void comprarSupino() {

        if (supino) {

            mensagem =
                    "Você já possui o supino.";

            return;
        }

        if (moedas >= 500) {

            moedas -= 500;

            supino = true;

            mensagem =
                    "Supino comprado! +200% força.";
        } else {

            mensagem =
                    "Você precisa de 500 moedas.";
        }
    }

    void comprarDourado() {

        if (equipamentoDourado) {

            mensagem =
                    "Você já possui o equipamento dourado.";

            return;
        }

        if (moedas >= 5000) {

            moedas -= 5000;

            equipamentoDourado = true;

            mensagem =
                    "EQUIPAMENTO DOURADO! +1000% força!";
        } else {

            mensagem =
                    "Você precisa de 5000 moedas.";
        }
    }

    // =====================================================
    // DESENHAR
    // =====================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g;

        desenharFundo(g2);

        desenharPersonagem(g2);

        desenharHUD(g2);

        if (lojaAberta) {

            desenharLoja(g2);
        }
    }

    // =====================================================
    // FUNDO
    // =====================================================

    void desenharFundo(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        30,
                        35,
                        45
                )
        );

        g.fillRect(
                0,
                0,
                LARGURA,
                ALTURA
        );

        // Piso

        g.setColor(
                new Color(
                        60,
                        60,
                        65
                )
        );

        g.fillRect(
                0,
                450,
                LARGURA,
                150
        );

        // Academia

        g.setColor(
                new Color(
                        90,
                        90,
                        100
                )
        );

        g.fillRect(
                100,
                350,
                200,
                20
        );

        g.fillRect(
                130,
                200,
                20,
                150
        );

        g.fillRect(
                250,
                200,
                20,
                150
        );

        // Barra

        g.setColor(
                Color.LIGHT_GRAY
        );

        g.fillRect(
                100,
                190,
                200,
                10
        );
    }

    // =====================================================
    // PERSONAGEM
    // =====================================================

    void desenharPersonagem(
            Graphics2D g
    ) {

        int x = 500;
        int y = 250;

        // Sombra

        g.setColor(
                Color.BLACK
        );

        g.fillOval(
                x - 40,
                y + 180,
                100,
                20
        );

        // Pernas

        g.setColor(
                new Color(
                        30,
                        30,
                        35
                )
        );

        g.fillRect(
                x - 15,
                y + 120,
                20,
                70
        );

        g.fillRect(
                x + 20,
                y + 120,
                20,
                70
        );

        // Corpo

        g.setColor(
                new Color(
                        50,
                        100,
                        180
                )
        );

        g.fillRoundRect(
                x - 25,
                y + 50,
                70,
                90,
                20,
                20
        );

        // Cabeça

        g.setColor(
                new Color(
                        190,
                        145,
                        110
                )
        );

        g.fillOval(
                x - 15,
                y,
                50,
                60
        );

        // Cabelo

        g.setColor(
                Color.BLACK
        );

        g.fillArc(
                x - 18,
                y - 5,
                56,
                35,
                0,
                180
        );

        // Braços

        g.setColor(
                new Color(
                        190,
                        145,
                        110
                )
        );

        g.fillRoundRect(
                x - 70,
                y + 60,
                45,
                20,
                15,
                15
        );

        g.fillRoundRect(
                x + 40,
                y + 60,
                45,
                20,
                15,
                15
        );

        // Músculos

        g.setColor(
                new Color(
                        80,
                        130,
                        210
                )
        );

        g.fillOval(
                x - 20,
                y + 65,
                30,
                25
        );

        g.fillOval(
                x + 20,
                y + 65,
                30,
                25
        );

        // Peso

        if (treino >= 5) {

            g.setColor(
                    Color.GRAY
            );

            g.fillRect(
                    x - 90,
                    y + 50,
                    20,
                    40
            );

            g.fillRect(
                    x + 80,
                    y + 50,
                    20,
                    40
            );
        }
    }

    // =====================================================
    // HUD
    // =====================================================

    void desenharHUD(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        0,
                        0,
                        0,
                        180
                )
        );

        g.fillRect(
                0,
                0,
                LARGURA,
                120
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        g.drawString(
                nome,
                25,
                35
        );

        g.drawString(
                "Nível: " + nivel,
                25,
                65
        );

        g.drawString(
                "Força: " +
                        String.format(
                                "%.1f",
                                forca
                        ),
                180,
                35
        );

        g.drawString(
                "Moedas: " + moedas,
                180,
                65
        );

        // Energia

        g.setColor(
                Color.DARK_GRAY
        );

        g.fillRect(
                400,
                25,
                250,
                25
        );

        g.setColor(
                Color.GREEN
        );

        g.fillRect(
                400,
                25,
                (int)
                        (energia * 2.5),
                25
        );

        g.setColor(
                Color.WHITE
        );

        g.drawRect(
                400,
                25,
                250,
                25
        );

        g.drawString(
                "Energia",
                670,
                45
        );

        // Mensagem

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        g.drawString(
                mensagem,
                25,
                100
        );

        // Botões

        desenharBotao(
                g,
                350,
                500,
                200,
                60,
                "TREINAR"
        );

        desenharBotao(
                g,
                600,
                500,
                200,
                60,
                lojaAberta
                        ? "FECHAR LOJA"
                        : "LOJA"
        );
    }

    // =====================================================
    // BOTÃO
    // =====================================================

    void desenharBotao(
            Graphics2D g,
            int x,
            int y,
            int largura,
            int altura,
            String texto
    ) {

        g.setColor(
                new Color(
                        50,
                        120,
                        220
                )
        );

        g.fillRoundRect(
                x,
                y,
                largura,
                altura,
                15,
                15
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        int textoLargura =
                g.getFontMetrics()
                        .stringWidth(
                                texto
                        );

        g.drawString(
                texto,
                x +
                        (largura -
                                textoLargura)
                                / 2,
                y + 38
        );
    }

    // =====================================================
    // LOJA
    // =====================================================

    void desenharLoja(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        20,
                        20,
                        25,
                        245
                )
        );

        g.fillRoundRect(
                120,
                100,
                660,
                400,
                25,
                25
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        g.drawString(
                "LOJA DE EQUIPAMENTOS",
                280,
                145
        );

        desenharItemLoja(
                g,
                160,
                180,
                "HALTER",
                "50 moedas",
                halter
        );

        desenharItemLoja(
                g,
                480,
                180,
                "BARRA",
                "150 moedas",
                barra
        );

        desenharItemLoja(
                g,
                160,
                320,
                "SUPINO",
                "500 moedas",
                supino
        );

        desenharItemLoja(
                g,
                480,
                320,
                "EQUIPAMENTO DOURADO",
                "5000 moedas",
                equipamentoDourado
        );
    }

    // =====================================================
    // ITEM DA LOJA
    // =====================================================

    void desenharItemLoja(
            Graphics2D g,
            int x,
            int y,
            String nome,
            String preco,
            boolean comprado
    ) {

        g.setColor(
                new Color(
                        50,
                        50,
                        60
                )
        );

        g.fillRoundRect(
                x,
                y,
                260,
                100,
                15,
                15
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        g.drawString(
                nome,
                x + 15,
                y + 30
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        g.drawString(
                comprado
                        ? "COMPRADO"
                        : preco,
                x + 15,
                y + 60
        );
    }

    // =====================================================
    // CLIQUE
    // =====================================================

    @Override
    public void mouseClicked(
            MouseEvent e
    ) {

        int x = e.getX();
        int y = e.getY();

        // Treinar

        if (!lojaAberta &&
                x >= 350 &&
                x <= 550 &&
                y >= 500 &&
                y <= 560) {

            treinar();

            return;
        }

        // Loja

        if (x >= 600 &&
                x <= 800 &&
                y >= 500 &&
                y <= 560) {

            lojaAberta =
                    !lojaAberta;

            return;
        }

        if (!lojaAberta) {
            return;
        }

        // Halter

        if (x >= 160 &&
                x <= 420 &&
                y >= 180 &&
                y <= 280) {

            comprarHalter();

            return;
        }

        // Barra

        if (x >= 480 &&
                x <= 740 &&
                y >= 180 &&
                y <= 280) {

            comprarBarra();

            return;
        }

        // Supino

        if (x >= 160 &&
                x <= 420 &&
                y >= 320 &&
                y <= 420) {

            comprarSupino();

            return;
        }

        // Dourado

        if (x >= 480 &&
                x <= 740 &&
                y >= 320 &&
                y <= 420) {

            comprarDourado();
        }
    }

    // =====================================================
    // MÉTODOS DO MOUSE
    // =====================================================

    @Override
    public void mousePressed(
            MouseEvent e
    ) {
    }

    @Override
    public void mouseReleased(
            MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(
            MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(
            MouseEvent e
    ) {
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        JFrame janela =
                new JFrame(
                        "Simulador de Força"
                );

        SimuladorForca jogo =
                new SimuladorForca();

        janela.add(jogo);

        janela.pack();

        janela.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        janela.setLocationRelativeTo(
                null
        );

        janela.setResizable(false);

        janela.setVisible(true);
    }
}

