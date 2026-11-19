import java.awt.Color;
import java.awt.Graphics;

    public class Protagonista {

        private int x;
        private int y;

        private int vida = 100;
        private int velocidade = 4;

        private boolean cima;
        private boolean baixo;
        private boolean esquerda;
        private boolean direita;

        private long ultimoDano = 0;

        public Protagonista(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void atualizar() {

            if (cima) y -= velocidade;
            if (baixo) y += velocidade;
            if (esquerda) x -= velocidade;
            if (direita) x += velocidade;

            x = Math.max(15, Math.min(785, x));
            y = Math.max(15, Math.min(585, y));
        }

        public void desenhar(Graphics g, int mouseX, int mouseY) {

            g.setColor(Color.BLACK);
            g.drawLine(x, y, mouseX, mouseY);

            g.setColor(Color.BLUE);
            g.fillOval(x - 15, y - 15, 30, 30);

            g.setColor(Color.WHITE);
            g.fillOval(x - 5, y - 5, 10, 10);
        }

        public void receberDano() {

            long agora = System.currentTimeMillis();

            if (agora - ultimoDano > 500) {
                vida -= 10;
                ultimoDano = agora;
            }
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getVida() { return vida; }

        public void setCima(boolean valor) { cima = valor; }
        public void setBaixo(boolean valor) { baixo = valor; }
        public void setEsquerda(boolean valor) { esquerda = valor; }
        public void setDireita(boolean valor) { direita = valor; }
    }

