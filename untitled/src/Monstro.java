public class Monstro {

    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    public String tipo;
    private double esquiva;
    private int dano;
    private final int VIDAMAX;

    public Monstro(String nome, int vida, int ataque, int defesa, String tipo, double esquiva, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.tipo = tipo;
        this.esquiva = esquiva;
        this.dano = dano;
        this.VIDAMAX = vida;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void perderVida(int vidaPerdida) {
        vida = vida - vidaPerdida;
        if (vida < 0) {
            vida = 0;
        }
    }

        public boolean estarVivo(){
            return vida > 0;
        }
}
