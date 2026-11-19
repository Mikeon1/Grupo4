import java.awt.Color;
import java.awt.Graphics;

    public class Inimigo {

        private int x;
        private int y;

        public Inimigo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void perseguir(Protagonista protagonista) {

            double dx = protagonista.getX() - x;
            double dy = protagonista.getY() - y;

            double distancia = Math.sqrt(dx * dx + dy * dy);

            if (distancia > 0) {
                x += (int) Math.round(dx / distancia * 2);
                y += (int) Math.round(dy / distancia * 2);
            }
        }

        public void desenhar(Graphics g) {

            g.setColor(Color.RED);
            g.fillOval(x - 15, y - 15, 30, 30);

            g.setColor(Color.BLACK);
            g.fillOval(x - 5, y - 5, 10, 10);
        }

        public int getX() { return x; }
        public int getY() { return y; }
    }

