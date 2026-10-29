import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RPGCompleto extends JPanel implements KeyListener {

    // ============================================================
    // CONFIGURAÇÕES
    // ============================================================

    static final int LARGURA = 1100;
    static final int ALTURA = 700;

    static final int MAPA_LARGURA = 2200;
    static final int MAPA_ALTURA = 1600;

    Timer timer;
    Random random = new Random();

    // ============================================================
    // JOGADOR
    // ============================================================

    int jogadorX = 200;
    int jogadorY = 300;

    int jogadorLargura = 42;
    int jogadorAltura = 60;

    int velocidade = 5;

    int vidaMaxima = 300;
    int vida = 300;

    int staminaMaxima = 100;
    int stamina = 100;

    int manaMaxima = 100;
    int mana = 100;

    int ataque = 35;
    int defesa = 10;

    int nivel = 1;
    int xp = 0;

    int ouro = 100;
    int pocoes = 5;

    // ============================================================
    // FASE
    // ============================================================

    int fase = 1;
    int totalFases = 30;

    boolean faseConcluida = false;

    String nomeFase = "Vale dos Antigos";

    // ============================================================
    // CÂMERA
    // ============================================================

    int cameraX = 0;
    int cameraY = 0;

    // ============================================================
    // CONTROLES
    // ============================================================

    boolean cima;
    boolean baixo;
    boolean esquerda;
    boolean direita;

    boolean atacando = false;
    boolean ataquePesado = false;
    boolean esquivando = false;

    int tempoAtaque = 0;
    int tempoEsquiva = 0;

    // ============================================================
    // ESTADO DO JOGO
    // ============================================================

    boolean menu = true;
    boolean pausado = false;
    boolean gameOver = false;
    boolean venceuJogo = false;

    // ============================================================
    // LISTAS
    // ============================================================

    ArrayList<Inimigo> inimigos =
            new ArrayList<>();

    ArrayList<Projetil> projeteis =
            new ArrayList<>();

    ArrayList<Efeito> efeitos =
            new ArrayList<>();

    ArrayList<Bau> baus =
            new ArrayList<>();

    ArrayList<Portal> portais =
            new ArrayList<>();

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public RPGCompleto() {

        setPreferredSize(
                new Dimension(
                        LARGURA,
                        ALTURA
                )
        );

        setFocusable(true);

        addKeyListener(this);

        timer = new Timer(
                16,
                e -> atualizar()
        );

        timer.start();
    }

    // ============================================================
    // INICIAR JOGO
    // ============================================================

    void iniciarJogo() {

        menu = false;

        pausado = false;

        gameOver = false;

        venceuJogo = false;

        fase = 1;

        jogadorX = 200;

        jogadorY = 300;

        vida = vidaMaxima;

        stamina = staminaMaxima;

        mana = manaMaxima;

        xp = 0;

        nivel = 1;

        ouro = 100;

        pocoes = 5;

        carregarFase();
    }

    // ============================================================
    // CARREGAR FASE
    // ============================================================

    void carregarFase() {

        inimigos.clear();

        projeteis.clear();

        efeitos.clear();

        baus.clear();

        portais.clear();

        jogadorX = 150;

        jogadorY = 350;

        faseConcluida = false;

        nomeFase =
                obterNomeFase(fase);

        criarInimigosDaFase();

        criarBaus();

        if (fase < totalFases) {

            portais.add(
                    new Portal(
                            2000,
                            1300
                    )
            );
        }

        criarEfeito(
                jogadorX,
                jogadorY,
                "FASE " + fase,
                Color.YELLOW
        );
    }

    // ============================================================
    // NOMES DAS FASES
    // ============================================================

    String obterNomeFase(int numero) {

        String[] nomes = {

                "Vale dos Antigos",
                "Floresta Sombria",
                "Templo Perdido",
                "Cavernas de Gelo",
                "Montanha dos Gigantes",
                "Ruínas Esquecidas",
                "Lago dos Espíritos",
                "Floresta dos Lobos",
                "Cidade Abandonada",
                "Fortaleza Negra",

                "Deserto dos Ossos",
                "Torre do Trovão",
                "Pântano Maldito",
                "Caverna dos Cristais",
                "Campos de Batalha",
                "Templo do Fogo",
                "Vale da Tempestade",
                "Muralha Antiga",
                "Cidade dos Mortos",
                "Palácio Congelado",

                "Montanha Sagrada",
                "Reino Subterrâneo",
                "Floresta dos Deuses",
                "Templo Celestial",
                "Abismo",

                "Fortaleza do Rei",
                "Trono Sombrio",
                "Reino Perdido",
                "Portal Final",
                "Trono do Rei do Abismo"
        };

        return nomes[numero - 1];
    }

    // ============================================================
    // CRIAR INIMIGOS
    // ============================================================

    void criarInimigosDaFase() {

        int quantidade =
                3 + fase / 2;

        for (int i = 0;
             i < quantidade;
             i++) {

            int x =
                    350 +
                            random.nextInt(1500);

            int y =
                    150 +
                            random.nextInt(1100);

            int vidaInimigo =
                    70 +
                            fase * 15;

            int ataqueInimigo =
                    10 +
                            fase * 2;

            int tamanho =
                    35 +
                            random.nextInt(20);

            String nome =
                    escolherNomeInimigo();

            inimigos.add(
                    new Inimigo(
                            x,
                            y,
                            vidaInimigo,
                            ataqueInimigo,
                            tamanho,
                            nome
                    )
            );
        }

        // Chefe a cada 5 fases

        if (fase % 5 == 0) {

            inimigos.add(
                    new Inimigo(
                            1650,
                            650,
                            700 + fase * 100,
                            30 + fase * 3,
                            100,
                            "CHEFE DA FASE"
                    )
            );
        }
    }

    // ============================================================
    // NOMES DE INIMIGOS
    // ============================================================

    String escolherNomeInimigo() {

        String[] nomes = {

                "Goblin",
                "Lobo Sombrio",
                "Guerreiro",
                "Orc",
                "Esqueleto",
                "Demônio",
                "Guardião",
                "Caçador",
                "Fera",
                "Espírito"
        };

        return nomes[
                random.nextInt(
                        nomes.length
                )
                ];
    }

    // ============================================================
    // BAÚS
    // ============================================================

    void criarBaus() {

        for (int i = 0; i < 3; i++) {

            baus.add(
                    new Bau(
                            400 +
                                    random.nextInt(1300),

                            200 +
                                    random.nextInt(1000)
                    )
            );
        }
    }

    // ============================================================
    // ATUALIZAÇÃO
    // ============================================================

    void atualizar() {

        if (menu) {

            repaint();

            return;
        }

        if (pausado ||
                gameOver ||
                venceuJogo) {

            repaint();

            return;
        }

        moverJogador();

        atualizarAtaques();

        atualizarEsquiva();

        recuperarRecursos();

        atualizarInimigos();

        atualizarProjeteis();

        atualizarEfeitos();

        verificarBaus();

        verificarPortal();

        verificarMorte();

        verificarConclusao();

        atualizarCamera();

        repaint();
    }

    // ============================================================
    // MOVIMENTO
    // ============================================================

    void moverJogador() {

        int velocidadeAtual =
                velocidade;

        if (atacando) {

            velocidadeAtual = 2;
        }

        if (cima) {

            jogadorY -=
                    velocidadeAtual;
        }

        if (baixo) {

            jogadorY +=
                    velocidadeAtual;
        }

        if (esquerda) {

            jogadorX -=
                    velocidadeAtual;
        }

        if (direita) {

            jogadorX +=
                    velocidadeAtual;
        }

        if (jogadorX < 30) {

            jogadorX = 30;
        }

        if (jogadorY < 110) {

            jogadorY = 110;
        }

        if (jogadorX >
                MAPA_LARGURA -
                        jogadorLargura) {

            jogadorX =
                    MAPA_LARGURA -
                            jogadorLargura;
        }

        if (jogadorY >
                MAPA_ALTURA -
                        jogadorAltura) {

            jogadorY =
                    MAPA_ALTURA -
                            jogadorAltura;
        }
    }

    // ============================================================
    // RECURSOS
    // ============================================================

    void recuperarRecursos() {

        if (stamina <
                staminaMaxima) {

            stamina++;
        }

        if (mana <
                manaMaxima) {

            mana++;
        }
    }

    // ============================================================
    // ATAQUE
    // ============================================================

    void atacar() {

        if (atacando ||
                esquivando) {

            return;
        }

        atacando = true;

        ataquePesado = false;

        tempoAtaque = 12;

        int alcance = 80;

        Rectangle areaAtaque =
                new Rectangle(
                        jogadorX - alcance,
                        jogadorY - alcance / 2,
                        jogadorLargura +
                                alcance * 2,
                        jogadorAltura +
                                alcance
                );

        acertarInimigos(
                areaAtaque,
                ataque
        );
    }

    // ============================================================
    // ATAQUE PESADO
    // ============================================================

    void ataquePesado() {

        if (atacando ||
                esquivando) {

            return;
        }

        if (stamina < 30) {

            return;
        }

        stamina -= 30;

        atacando = true;

        ataquePesado = true;

        tempoAtaque = 25;

        Rectangle areaAtaque =
                new Rectangle(
                        jogadorX - 130,
                        jogadorY - 100,
                        jogadorLargura + 260,
                        jogadorAltura + 200
                );

        acertarInimigos(
                areaAtaque,
                ataque * 3
        );
    }

    // ============================================================
    // ACERTAR INIMIGOS
    // ============================================================

    void acertarInimigos(
            Rectangle area,
            int danoBase
    ) {

        for (Inimigo inimigo :
                inimigos) {

            if (inimigo.vida <= 0) {

                continue;
            }

            Rectangle alvo =
                    new Rectangle(
                            inimigo.x,
                            inimigo.y,
                            inimigo.tamanho,
                            inimigo.tamanho
                    );

            if (area.intersects(alvo)) {

                int dano =
                        danoBase +
                                random.nextInt(20);

                inimigo.vida -= dano;

                criarEfeito(
                        inimigo.x,
                        inimigo.y,
                        "-" + dano,
                        Color.YELLOW
                );

                if (inimigo.vida <= 0) {

                    derrotarInimigo(
                            inimigo
                    );
                }
            }
        }
    }

    // ============================================================
    // ATUALIZAR ATAQUE
    // ============================================================

    void atualizarAtaques() {

        if (tempoAtaque > 0) {

            tempoAtaque--;

            if (tempoAtaque == 0) {

                atacando = false;

                ataquePesado = false;
            }
        }
    }

    // ============================================================
    // ESQUIVA
    // ============================================================

    void esquivar() {

        if (esquivando) {

            return;
        }

        if (stamina < 25) {

            return;
        }

        stamina -= 25;

        esquivando = true;

        tempoEsquiva = 15;

        int distancia = 100;

        if (cima) {

            jogadorY -= distancia;
        }

        else if (baixo) {

            jogadorY += distancia;
        }

        else if (esquerda) {

            jogadorX -= distancia;
        }

        else {

            jogadorX += distancia;
        }

        criarEfeito(
                jogadorX,
                jogadorY,
                "ESQUIVA",
                Color.CYAN
        );
    }

    // ============================================================
    // ATUALIZAR ESQUIVA
    // ============================================================

    void atualizarEsquiva() {

        if (tempoEsquiva > 0) {

            tempoEsquiva--;

            if (tempoEsquiva == 0) {

                esquivando = false;
            }
        }
    }

    // ============================================================
    // PODER
    // ============================================================

    void usarPoder() {

        if (mana < 40) {

            return;
        }

        mana -= 40;

        int alcance = 220;

        Rectangle area =
                new Rectangle(
                        jogadorX - alcance,
                        jogadorY - alcance,
                        jogadorLargura +
                                alcance * 2,
                        jogadorAltura +
                                alcance * 2
                );

        criarEfeito(
                jogadorX,
                jogadorY,
                "PODER!",
                Color.ORANGE
        );

        acertarInimigos(
                area,
                ataque * 4
        );
    }

    // ============================================================
    // INIMIGOS
    // ============================================================

    void atualizarInimigos() {

        for (Inimigo inimigo :
                inimigos) {

            if (inimigo.vida <= 0) {

                continue;
            }

            double dx =
                    jogadorX -
                            inimigo.x;

            double dy =
                    jogadorY -
                            inimigo.y;

            double distancia =
                    Math.sqrt(
                            dx * dx +
                                    dy * dy
                    );

            if (distancia > 75) {

                if (jogadorX >
                        inimigo.x) {

                    inimigo.x +=
                            inimigo.velocidade;
                }

                if (jogadorX <
                        inimigo.x) {

                    inimigo.x -=
                            inimigo.velocidade;
                }

                if (jogadorY >
                        inimigo.y) {

                    inimigo.y +=
                            inimigo.velocidade;
                }

                if (jogadorY <
                        inimigo.y) {

                    inimigo.y -=
                            inimigo.velocidade;
                }
            }

            if (distancia < 85) {

                if (random.nextInt(45)
                        == 0) {

                    receberDano(
                            inimigo.ataque
                    );
                }
            }
        }
    }

    // ============================================================
    // DANO
    // ============================================================

    void receberDano(int dano) {

        if (esquivando) {

            return;
        }

        int danoFinal =
                dano - defesa;

        if (danoFinal < 1) {

            danoFinal = 1;
        }

        vida -= danoFinal;

        criarEfeito(
                jogadorX,
                jogadorY,
                "-" + danoFinal,
                Color.RED
        );
    }

    // ============================================================
    // DERROTAR INIMIGO
    // ============================================================

    void derrotarInimigo(
            Inimigo inimigo
    ) {

        inimigo.vida = 0;

        int xpGanho = 50;

        int ouroGanho = 25;

        if (inimigo.nome
                .contains("CHEFE")) {

            xpGanho = 500;

            ouroGanho = 250;
        }

        xp += xpGanho;

        ouro += ouroGanho;

        criarEfeito(
                inimigo.x,
                inimigo.y,
                "+" + xpGanho + " XP",
                Color.WHITE
        );

        subirNivel();
    }

    // ============================================================
    // LEVEL UP
    // ============================================================

    void subirNivel() {

        int necessario =
                nivel * 300;

        if (xp >= necessario) {

            xp -= necessario;

            nivel++;

            vidaMaxima += 50;

            vida = vidaMaxima;

            staminaMaxima += 10;

            stamina = staminaMaxima;

            manaMaxima += 10;

            mana = manaMaxima;

            ataque += 10;

            defesa += 5;

            pocoes++;

            criarEfeito(
                    jogadorX,
                    jogadorY,
                    "NÍVEL " + nivel,
                    Color.YELLOW
            );
        }
    }

    // ============================================================
    // BAÚS
    // ============================================================

    void verificarBaus() {

        Rectangle jogador =
                new Rectangle(
                        jogadorX,
                        jogadorY,
                        jogadorLargura,
                        jogadorAltura
                );

        for (Bau bau : baus) {

            if (bau.aberto) {

                continue;
            }

            Rectangle caixa =
                    new Rectangle(
                            bau.x,
                            bau.y,
                            40,
                            40
                    );

            if (jogador.intersects(
                    caixa
            )) {

                bau.aberto = true;

                int recompensa =
                        50 +
                                random.nextInt(150);

                ouro += recompensa;

                pocoes++;

                criarEfeito(
                        bau.x,
                        bau.y,
                        "+" + recompensa +
                                " OURO",
                        Color.YELLOW
                );
            }
        }
    }

    // ============================================================
    // PORTAL
    // ============================================================

    void verificarPortal() {

        if (!faseConcluida) {

            return;
        }

        if (portais.isEmpty()) {

            return;
        }

        Portal portal =
                portais.get(0);

        Rectangle jogador =
                new Rectangle(
                        jogadorX,
                        jogadorY,
                        jogadorLargura,
                        jogadorAltura
                );

        Rectangle portalRect =
                new Rectangle(
                        portal.x,
                        portal.y,
                        80,
                        100
                );

        if (jogador.intersects(
                portalRect
        )) {

            proximaFase();
        }
    }

    // ============================================================
    // CONCLUSÃO
    // ============================================================

    void verificarConclusao() {

        boolean inimigoVivo = false;

        for (Inimigo inimigo :
                inimigos) {

            if (inimigo.vida > 0) {

                inimigoVivo = true;

                break;
            }
        }

        if (!inimigoVivo) {

            faseConcluida = true;

            if (fase == totalFases) {

                venceuJogo = true;
            }
        }
    }

    // ============================================================
    // PRÓXIMA FASE
    // ============================================================

    void proximaFase() {

        if (fase >= totalFases) {

            venceuJogo = true;

            return;
        }

        fase++;

        vida = vidaMaxima;

        stamina = staminaMaxima;

        mana = manaMaxima;

        carregarFase();
    }

    // ============================================================
    // MORTE
    // ============================================================

    void verificarMorte() {

        if (vida <= 0) {

            vida = 0;

            gameOver = true;
        }
    }

    // ============================================================
    // EFEITOS
    // ============================================================

    void criarEfeito(
            int x,
            int y,
            String texto,
            Color cor
    ) {

        efeitos.add(
                new Efeito(
                        x,
                        y,
                        texto,
                        cor
                )
        );
    }

    void atualizarEfeitos() {

        for (Efeito efeito :
                efeitos) {

            efeito.y--;

            efeito.tempo--;
        }

        efeitos.removeIf(
                e -> e.tempo <= 0
        );
    }

    // ============================================================
    // PROJÉTEIS
    // ============================================================

    void atualizarProjeteis() {

        Iterator<Projetil> it =
                projeteis.iterator();

        while (it.hasNext()) {

            Projetil p = it.next();

            p.x += p.vx;

            p.y += p.vy;

            p.tempo--;

            if (p.tempo <= 0) {

                it.remove();
            }
        }
    }

    // ============================================================
    // CÂMERA
    // ============================================================

    void atualizarCamera() {

        cameraX =
                jogadorX -
                        LARGURA / 2;

        cameraY =
                jogadorY -
                        ALTURA / 2;

        if (cameraX < 0) {

            cameraX = 0;
        }

        if (cameraY < 100) {

            cameraY = 100;
        }

        if (cameraX >
                MAPA_LARGURA -
                        LARGURA) {

            cameraX =
                    MAPA_LARGURA -
                            LARGURA;
        }

        if (cameraY >
                MAPA_ALTURA -
                        ALTURA) {

            cameraY =
                    MAPA_ALTURA -
                            ALTURA;
        }
    }

    // ============================================================
    // DESENHAR
    // ============================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g;

        if (menu) {

            desenharMenu(g2);

            return;
        }

        desenharMundo(g2);

        desenharBaus(g2);

        desenharPortal(g2);

        desenharInimigos(g2);

        desenharJogador(g2);

        desenharEfeitos(g2);

        desenharHUD(g2);

        if (pausado) {

            desenharMensagem(
                    g2,
                    "PAUSADO"
            );
        }

        if (gameOver) {

            desenharMensagem(
                    g2,
                    "GAME OVER"
            );
        }

        if (venceuJogo) {

            desenharMensagem(
                    g2,
                    "VOCÊ VENCEU!"
            );
        }
    }

    // ============================================================
    // MENU
    // ============================================================

    void desenharMenu(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        10,
                        10,
                        20
                )
        );

        g.fillRect(
                0,
                0,
                LARGURA,
                ALTURA
        );

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        60
                )
        );

        g.drawString(
                "REINO DO ABISMO",
                250,
                200
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        g.drawString(
                "RPG DE AÇÃO",
                430,
                250
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        g.drawString(
                "PRESSIONE ENTER PARA COMEÇAR",
                280,
                400
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        g.drawString(
                "30 FASES • INIMIGOS • CHEFES • PODERES",
                330,
                450
        );
    }

    // ============================================================
    // MUNDO
    // ============================================================

    void desenharMundo(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        25,
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

        g.setColor(
                new Color(
                        75,
                        75,
                        75
                )
        );

        g.fillRect(
                -cameraX,
                100 - cameraY,
                MAPA_LARGURA,
                MAPA_ALTURA
        );

        // quadrados do mapa

        for (int x = 0;
             x < MAPA_LARGURA;
             x += 120) {

            for (int y = 100;
                 y < MAPA_ALTURA;
                 y += 120) {

                g.setColor(
                        new Color(
                                90,
                                90,
                                90
                        )
                );

                g.drawRect(
                        x - cameraX,
                        y - cameraY,
                        120,
                        120
                );
            }
        }

        // árvores

        for (int i = 0; i < 50; i++) {

            int x =
                    (i * 137) %
                            MAPA_LARGURA;

            int y =
                    150 +
                            (i * 211) %
                                    1300;

            desenharArvore(
                    g,
                    x - cameraX,
                    y - cameraY
            );
        }
    }

    // ============================================================
    // ÁRVORE
    // ============================================================

    void desenharArvore(
            Graphics2D g,
            int x,
            int y
    ) {

        g.setColor(
                new Color(
                        70,
                        45,
                        25
                )
        );

        g.fillRect(
                x,
                y,
                20,
                70
        );

        g.setColor(
                new Color(
                        30,
                        80,
                        40
                )
        );

        g.fillOval(
                x - 25,
                y - 40,
                70,
                70
        );
    }

    // ============================================================
    // JOGADOR
    // ============================================================

    void desenharJogador(
            Graphics2D g
    ) {

        int x =
                jogadorX -
                        cameraX;

        int y =
                jogadorY -
                        cameraY;

        // sombra

        g.setColor(
                new Color(
                        0,
                        0,
                        0,
                        100
                )
        );

        g.fillOval(
                x - 10,
                y + 50,
                60,
                18
        );

        // pernas

        g.setColor(Color.BLACK);

        g.fillRect(
                x + 7,
                y + 40,
                12,
                25
        );

        g.fillRect(
                x + 25,
                y + 40,
                12,
                25
        );

        // corpo

        g.setColor(
                new Color(
                        80,
                        80,
                        85
                )
        );

        g.fillRect(
                x + 5,
                y + 18,
                35,
                30
        );

        // cabeça

        g.setColor(
                new Color(
                        170,
                        130,
                        100
                )
        );

        g.fillOval(
                x + 8,
                y - 8,
                28,
                30
        );

        // cabelo

        g.setColor(Color.BLACK);

        g.fillArc(
                x + 7,
                y - 10,
                30,
                22,
                0,
                180
        );

        // braços

        g.setColor(
                new Color(
                        170,
                        130,
                        100
                )
        );

        g.fillRect(
                x - 8,
                y + 20,
                13,
                28
        );

        g.fillRect(
                x + 40,
                y + 20,
                13,
                28
        );

        // arma

        g.setColor(
                new Color(
                        210,
                        210,
                        220
                )
        );

        g.setStroke(
                new BasicStroke(6)
        );

        g.drawLine(
                x + 45,
                y + 20,
                x + 80,
                y - 15
        );

        // ataque

        if (atacando) {

            g.setColor(
                    ataquePesado
                            ? Color.ORANGE
                            : Color.WHITE
            );

            g.setStroke(
                    new BasicStroke(
                            ataquePesado
                                    ? 12
                                    : 7
                    )
            );

            g.drawArc(
                    x - 80,
                    y - 80,
                    170,
                    170,
                    20,
                    130
            );
        }

        // esquiva

        if (esquivando) {

            g.setColor(Color.CYAN);

            g.drawOval(
                    x - 20,
                    y - 20,
                    80,
                    90
            );
        }
    }

    // ============================================================
    // INIMIGOS
    // ============================================================

    void desenharInimigos(
            Graphics2D g
    ) {

        for (Inimigo inimigo :
                inimigos) {

            if (inimigo.vida <= 0) {

                continue;
            }

            int x =
                    inimigo.x -
                            cameraX;

            int y =
                    inimigo.y -
                            cameraY;

            Color cor;

            if (inimigo.nome
                    .contains("CHEFE")) {

                cor =
                        new Color(
                                130,
                                20,
                                20
                        );

            } else {

                cor =
                        new Color(
                                90,
                                30,
                                30
                        );
            }

            g.setColor(cor);

            g.fillRect(
                    x,
                    y,
                    inimigo.tamanho,
                    inimigo.tamanho
            );

            // olhos

            g.setColor(
                    Color.YELLOW
            );

            g.fillOval(
                    x + 10,
                    y + 12,
                    8,
                    8
            );

            g.fillOval(
                    x +
                            inimigo.tamanho -
                            18,
                    y + 12,
                    8,
                    8
            );

            // nome

            g.setColor(
                    Color.WHITE
            );

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );

            g.drawString(
                    inimigo.nome,
                    x,
                    y - 12
            );

            // vida

            g.setColor(
                    Color.RED
            );

            g.fillRect(
                    x,
                    y - 7,
                    inimigo.tamanho,
                    5
            );

            g.setColor(
                    Color.GREEN
            );

            int vidaBarra =
                    (inimigo.vida *
                            inimigo.tamanho)
                            /
                            inimigo.vidaMaxima;

            g.fillRect(
                    x,
                    y - 7,
                    vidaBarra,
                    5
            );
        }
    }

    // ============================================================
    // BAÚS
    // ============================================================

    void desenharBaus(
            Graphics2D g
    ) {

        for (Bau bau : baus) {

            int x =
                    bau.x -
                            cameraX;

            int y =
                    bau.y -
                            cameraY;

            g.setColor(
                    bau.aberto
                            ? Color.GRAY
                            : new Color(
                            120,
                            70,
                            20
                    )
            );

            g.fillRect(
                    x,
                    y,
                    40,
                    40
            );

            g.setColor(
                    Color.YELLOW
            );

            g.drawRect(
                    x,
                    y,
                    40,
                    40
            );
        }
    }

    // ============================================================
    // PORTAL
    // ============================================================

    void desenharPortal(
            Graphics2D g
    ) {

        if (!faseConcluida ||
                portais.isEmpty()) {

            return;
        }

        Portal p =
                portais.get(0);

        int x =
                p.x -
                        cameraX;

        int y =
                p.y -
                        cameraY;

        g.setColor(
                new Color(
                        100,
                        0,
                        200,
                        180
                )
        );

        g.fillOval(
                x,
                y,
                80,
                100
        );

        g.setColor(
                Color.CYAN
        );

        g.drawOval(
                x,
                y,
                80,
                100
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        g.drawString(
                "PRÓXIMA FASE",
                x - 10,
                y - 10
        );
    }

    // ============================================================
    // EFEITOS
    // ============================================================

    void desenharEfeitos(
            Graphics2D g
    ) {

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        for (Efeito e :
                efeitos) {

            g.setColor(e.cor);

            g.drawString(
                    e.texto,
                    e.x - cameraX,
                    e.y - cameraY
            );
        }
    }

    // ============================================================
    // HUD
    // ============================================================

    void desenharHUD(
            Graphics2D g
    ) {

        g.setColor(
                new Color(
                        0,
                        0,
                        0,
                        190
                )
        );

        g.fillRect(
                0,
                0,
                LARGURA,
                105
        );

        // VIDA

        g.setColor(
                Color.DARK_GRAY
        );

        g.fillRect(
                20,
                20,
                280,
                20
        );

        g.setColor(
                Color.RED
        );

        g.fillRect(
                20,
                20,
                vida * 280 /
                        vidaMaxima,
                20
        );

        // STAMINA

        g.setColor(
                Color.DARK_GRAY
        );

        g.fillRect(
                20,
                48,
                280,
                12
        );

        g.setColor(
                Color.CYAN
        );

        g.fillRect(
                20,
                48,
                stamina * 280 /
                        staminaMaxima,
                12
        );

        // MANA

        g.setColor(
                Color.DARK_GRAY
        );

        g.fillRect(
                20,
                68,
                280,
                12
        );

        g.setColor(
                Color.BLUE
        );

        g.fillRect(
                20,
                68,
                mana * 280 /
                        manaMaxima,
                12
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        g.drawString(
                "FASE " + fase +
                        " - " +
                        nomeFase,
                330,
                25
        );

        g.drawString(
                "NÍVEL " + nivel,
                330,
                50
        );

        g.drawString(
                "XP " + xp,
                330,
                75
        );

        g.drawString(
                "OURO " + ouro,
                500,
                25
        );

        g.drawString(
                "POÇÕES " + pocoes,
                500,
                50
        );

        g.drawString(
                "WASD MOVER",
                700,
                25
        );

        g.drawString(
                "J ATAQUE",
                700,
                50
        );

        g.drawString(
                "L ATAQUE PESADO",
                700,
                75
        );

        g.drawString(
                "K PODER",
                900,
                25
        );

        g.drawString(
                "ESPAÇO ESQUIVA",
                900,
                50
        );

        g.drawString(
                "P POÇÃO",
                900,
                75
        );
    }

    // ============================================================
    // MENSAGEM
    // ============================================================

    void desenharMensagem(
            Graphics2D g,
            String texto
    ) {

        g.setColor(
                new Color(
                        0,
                        0,
                        0,
                        220
                )
        );

        g.fillRect(
                0,
                0,
                LARGURA,
                ALTURA
        );

        g.setColor(
                Color.WHITE
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        55
                )
        );

        int largura =
                g.getFontMetrics()
                        .stringWidth(texto);

        g.drawString(
                texto,
                (LARGURA - largura) / 2,
                300
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20
                )
        );

        g.drawString(
                "ENTER = continuar",
                430,
                360
        );
    }

    // ============================================================
    // TECLADO
    // ============================================================

    @Override
    public void keyPressed(
            KeyEvent e
    ) {

        int tecla =
                e.getKeyCode();

        // MENU

        if (menu &&
                tecla ==
                        KeyEvent.VK_ENTER) {

            iniciarJogo();

            return;
        }

        // GAME OVER

        if (gameOver &&
                tecla ==
                        KeyEvent.VK_ENTER) {

            iniciarJogo();

            return;
        }

        // VITÓRIA

        if (venceuJogo &&
                tecla ==
                        KeyEvent.VK_ENTER) {

            iniciarJogo();

            return;
        }

        // PAUSE

        if (tecla ==
                KeyEvent.VK_ESCAPE) {

            pausado =
                    !pausado;

            return;
        }

        if (menu ||
                pausado ||
                gameOver ||
                venceuJogo) {

            return;
        }

        if (tecla ==
                KeyEvent.VK_W) {

            cima = true;
        }

        if (tecla ==
                KeyEvent.VK_S) {

            baixo = true;
        }

        if (tecla ==
                KeyEvent.VK_A) {

            esquerda = true;
        }

        if (tecla ==
                KeyEvent.VK_D) {

            direita = true;
        }

        if (tecla ==
                KeyEvent.VK_J) {

            atacar();
        }

        if (tecla ==
                KeyEvent.VK_L) {

            ataquePesado();
        }

        if (tecla ==
                KeyEvent.VK_K) {

            usarPoder();
        }

        if (tecla ==
                KeyEvent.VK_SPACE) {

            esquivar();
        }

        if (tecla ==
                KeyEvent.VK_P) {

            usarPocao();
        }
    }

    // ============================================================
    // POÇÃO
    // ============================================================

    void usarPocao() {

        if (pocoes <= 0) {

            return;
        }

        if (vida >= vidaMaxima) {

            return;
        }

        pocoes--;

        vida += 120;

        if (vida >
                vidaMaxima) {

            vida =
                    vidaMaxima;
        }

        criarEfeito(
                jogadorX,
                jogadorY,
                "+120",
                Color.GREEN
        );
    }

    @Override
    public void keyReleased(
            KeyEvent e
    ) {

        int tecla =
                e.getKeyCode();

        if (tecla ==
                KeyEvent.VK_W) {

            cima = false;
        }

        if (tecla ==
                KeyEvent.VK_S) {

            baixo = false;
        }

        if (tecla ==
                KeyEvent.VK_A) {

            esquerda = false;
        }

        if (tecla ==
                KeyEvent.VK_D) {

            direita = false;
        }
    }

    @Override
    public void keyTyped(
            KeyEvent e
    ) {
    }

    // ============================================================
    // CLASSE INIMIGO
    // ============================================================

    class Inimigo {

        int x;
        int y;

        int vida;
        int vidaMaxima;

        int ataque;

        int tamanho;

        int velocidade;

        String nome;

        Inimigo(
                int x,
                int y,
                int vida,
                int ataque,
                int tamanho,
                String nome
        ) {

            this.x = x;

            this.y = y;

            this.vida = vida;

            this.vidaMaxima = vida;

            this.ataque = ataque;

            this.tamanho = tamanho;

            this.nome = nome;

            if (nome.contains(
                    "CHEFE"
            )) {

                velocidade = 2;

            } else {

                velocidade =
                        1 +
                                random.nextInt(2);
            }
        }
    }

    // ============================================================
    // BAÚ
    // ============================================================

    class Bau {

        int x;
        int y;

        boolean aberto = false;

        Bau(
                int x,
                int y
        ) {

            this.x = x;

            this.y = y;
        }
    }

    // ============================================================
    // PORTAL
    // ============================================================

    class Portal {

        int x;
        int y;

        Portal(
                int x,
                int y
        ) {

            this.x = x;

            this.y = y;
        }
    }

    // ============================================================
    // PROJÉTIL
    // ============================================================

    class Projetil {

        int x;
        int y;

        int vx;
        int vy;

        int dano;

        int tempo = 100;

        Color cor;

        Projetil(
                int x,
                int y,
                int vx,
                int vy,
                int dano,
                Color cor
        ) {

            this.x = x;

            this.y = y;

            this.vx = vx;

            this.vy = vy;

            this.dano = dano;

            this.cor = cor;
        }
    }

    // ============================================================
    // EFEITO
    // ============================================================

    class Efeito {

        int x;
        int y;

        String texto;

        Color cor;

        int tempo = 50;

        Efeito(
                int x,
                int y,
                String texto,
                Color cor
        ) {

            this.x = x;

            this.y = y;

            this.texto = texto;

            this.cor = cor;
        }
    }

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(
            String[] args
    ) {

        JFrame janela =
                new JFrame(
                        "Reino do Abismo"
                );

        RPGCompleto jogo =
                new RPGCompleto();

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

        jogo.requestFocusInWindow();
    }
}

