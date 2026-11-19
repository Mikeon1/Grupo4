import java.awt.Color;
import java.awt.Graphics;

    public class Projetil {

        private double x;
        private double y;

        private double velocidadeX;
        private double velocidadeY;

        public Projetil(int origemX, int origemY, int destinoX, int destinoY) {

            x = origemX;
            y = origemY;

            double dx = destinoX - origemX;
            double dy = destinoY - origemY;

            double distancia = Math.sqrt(dx * dx + dy * dy);

            if (distancia > 0) {
                velocidadeX = (dx / distancia) * 10;
                velocidadeY = (dy / distancia) * 10;
            }
        }

        public void atualizar() {
            x += velocidadeX;
            y += velocidadeY;
        }

        public void desenhar(Graphics g) {

            g.setColor(Color.YELLOW);

            g.fillOval(
                    (int) x - 5,
                    (int) y - 5,
                    10,
                    10
            );
        }

        public boolean foraDaTela() {
            return x < 0 || x > 800 || y < 0 || y > 600;
        }

        public double getX() { return x; }
        public double getY() { return y; }
    }


