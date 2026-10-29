import javax.swing.*;
import java.awt.*;

public class Fase1 extends JFrame {

    private protagonista protagonista;

    private Monstro inimigo;

    private JLabel lblFase;
    private JLabel lblInimigo;
    private JLabel lblVidaInimigo;
    private JLabel lblMensagem;

    private JButton btnAtacar;

    private int inimigoAtual = 1;

    public Fase1() {

        protagonista = new protagonista("Herói");

        inimigo = new Slime();

        setTitle("Fase 1 - A Caverna");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarTela();

        setVisible(true);
    }

    private void criarTela() {

        setLayout(new BorderLayout());

        lblFase = new JLabel(
                "FASE 1 - A CAVERNA",
                SwingConstants.CENTER
        );

        add(lblFase, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(3, 1));

        lblInimigo = new JLabel(
                "Inimigo: " + inimigo.getNome(),
                SwingConstants.CENTER
        );

        lblVidaInimigo = new JLabel(
                "Vida: " + inimigo.getVida(),
                SwingConstants.CENTER
        );

        lblMensagem = new JLabel(
                "Um Slime apareceu!",
                SwingConstants.CENTER
        );

        painelCentral.add(lblInimigo);
        painelCentral.add(lblVidaInimigo);
        painelCentral.add(lblMensagem);

        add(painelCentral, BorderLayout.CENTER);

        btnAtacar = new JButton("ATACAR");

        add(btnAtacar, BorderLayout.SOUTH);

        btnAtacar.addActionListener(e -> atacar());
    }

    private void atacar() {

        protagonista.atacar(inimigo);

        lblVidaInimigo.setText(
                "Vida: " + inimigo.getVida()
        );

        if (inimigo.getVida() <= 0) {

            proximoInimigo();
        }
    }

    private void proximoInimigo() {

        inimigoAtual++;

        if (inimigoAtual == 2) {

            inimigo = new Slime();

            lblInimigo.setText(
                    "Inimigo: " + inimigo.getNome()
            );

            lblVidaInimigo.setText(
                    "Vida: " + inimigo.getVida()
            );

            lblMensagem.setText(
                    "O segundo Slime apareceu!"
            );
        }

        else if (inimigoAtual == 3) {

            inimigo = new Orc();

            lblInimigo.setText(
                    "MINI-CHEFE: " + inimigo.getNome()
            );

            lblVidaInimigo.setText(
                    "Vida: " + inimigo.getVida()
            );

            lblMensagem.setText(
                    "O Orc apareceu!"
            );
        }

        else {

            lblMensagem.setText(
                    "🏆 FASE CONCLUÍDA!"
            );

            btnAtacar.setEnabled(false);
        }
    }

    public static void main(String[] args) {

        new Fase1();
    }
}
