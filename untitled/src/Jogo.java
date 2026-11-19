import java.util.ArrayList;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

    public class Jogo extends Canvas implements Runnable,
            KeyListener, MouseListener, MouseMotionListener {

        private boolean executando = true;
        private boolean gameOver = false;

        private Protagonista protagonista;

        private ArrayList<Inimigo> inimigos = new ArrayList<>();
        private ArrayList<Projetil> projeteis = new ArrayList<>();

        private int mouseX = 400;
        private int mouseY = 300;

        private int pontos = 0;

        private Random random = new Random();

        public Jogo() {

            setSize(800, 600);
            setBackground(Color.GREEN);

            addKeyListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);

            protagonista = new Protagonista(400, 300);

            criarInimigos();

            setFocusable(true);
        }




        private void criarInimigos() {

            for (int i = 0; i < 5; i++) {

                int x = random.nextBoolean() ? 30 : 770;
                int y = random.nextInt(500) + 50;

                inimigos.add(new Inimigo(x, y));
            }
        }

        @Override
        public void run() {

            while (executando) {

                atualizar();
                renderizar();

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        private void atualizar() {

            if (gameOver) {
                return;
            }

            protagonista.atualizar();

            for (Inimigo inimigo : inimigos) {
                inimigo.perseguir(protagonista);
            }

            atualizarProjeteis();

            for (Inimigo inimigo : inimigos) {

                double dx = protagonista.getX() - inimigo.getX();
                double dy = protagonista.getY() - inimigo.getY();

                if (Math.sqrt(dx * dx + dy * dy) < 25) {
                    protagonista.receberDano();
                }
            }

            if (protagonista.getVida() <= 0) {
                gameOver = true;
            }

            if (inimigos.isEmpty()) {
                criarInimigos();
            }
        }

        private void atualizarProjeteis() {

            ArrayList<Projetil> removals = new ArrayList<>();

            for (Projetil p : projeteis) {

                p.atualizar();

                if (p.foraDaTela()) {
                    removals.add(p);
                    continue;
                }

                for (Inimigo inimigo : inimigos) {

                    double dx = p.getX() - inimigo.getX();
                    double dy = p.getY() - inimigo.getY();

                    if (Math.sqrt(dx * dx + dy * dy) < 20) {

                        removals.add(p);
                        inimigos.remove(inimigo);
                        pontos += 10;

                        break;
                    }
                }
            }

            projeteis.removeAll(removals);
        }

        private void renderizar() {

            BufferStrategy bs = getBufferStrategy();

            if (bs == null) {
                createBufferStrategy(2);
                return;
            }

            Graphics g = bs.getDrawGraphics();

            g.setColor(new Color(70, 120, 65));
            g.fillRect(0, 0, 800, 600);

            protagonista.desenhar(g, mouseX, mouseY);

            for (Inimigo inimigo : inimigos) {
                inimigo.desenhar(g);
            }

            for (Projetil p : projeteis) {
                p.desenhar(g);
            }

            g.setColor(Color.WHITE);
            g.drawString("Vida: " + protagonista.getVida(), 20, 30);
            g.drawString("Pontos: " + pontos, 20, 55);
            g.drawString("Inimigos: " + inimigos.size(), 650, 30);

            if (gameOver) {

                g.setColor(Color.RED);
                g.drawString("GAME OVER - Pressione R para reiniciar", 250, 300);
            }

            g.dispose();
            bs.show();
        }

        private void reiniciar() {

            protagonista = new Protagonista(400, 300);

            inimigos.clear();
            projeteis.clear();

            pontos = 0;
            gameOver = false;

            criarInimigos();
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int tecla = e.getKeyCode();

            if (tecla == KeyEvent.VK_W) protagonista.setCima(true);
            if (tecla == KeyEvent.VK_S) protagonista.setBaixo(true);
            if (tecla == KeyEvent.VK_A) protagonista.setEsquerda(true);
            if (tecla == KeyEvent.VK_D) protagonista.setDireita(true);

            if (tecla == KeyEvent.VK_R && gameOver) {
                reiniciar();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int tecla = e.getKeyCode();

            if (tecla == KeyEvent.VK_W) protagonista.setCima(false);
            if (tecla == KeyEvent.VK_S) protagonista.setBaixo(false);
            if (tecla == KeyEvent.VK_A) protagonista.setEsquerda(false);
            if (tecla == KeyEvent.VK_D) protagonista.setDireita(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON1 && !gameOver) {

                Projetil p = new Projetil(
                        protagonista.getX(),
                        protagonista.getY(),
                        mouseX,
                        mouseY
                );

                projeteis.add(p);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

