public class Protagonista {

    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private double esquiva;
    private Inventario inventario;
    private final int vidamaxima;

    public Protagonista(String nome, int vida, int dano, int defesa, double esquiva) {
        this.nome = nome;
        this.vidamaxima = Math.max(1, vida);
        this.vida = Math.min(Math.max(0, vida), this.vidamaxima);
        this.dano = Math.max(0, dano);
        this.defesa = Math.max(0, defesa);
        this.esquiva = Math.min(1.0, Math.max(0.0, esquiva));
        this.inventario = new Inventario();
    }

    public int calcularDanoTotal() {
        return this.dano;
    }

    public boolean estarvivo() {
        return this.vida > 0;
    }

    public void perderVida(int vidaperdida) {
        if (vidaperdida <= 0) {
            return;
        }
        this.vida = Math.max(0, this.vida - vidaperdida);
    }

    public int recuperarVida(int valorCura) {
        if (valorCura > 0) {
            this.vida = Math.min(this.vidamaxima, this.vida + valorCura);
        }
        return this.vida;
    }

    public void equiparArma(int valorDano) {
        this.dano += valorDano;
    }

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > this.vidamaxima) {
            this.vida = this.vidamaxima;
        } else {
            this.vida = vida;
        }
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = Math.max(0, dano);
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = Math.max(0, defesa);
    }

    public double getEsquiva() {
        return esquiva;
    }

    public void setEsquiva(double esquiva) {
        this.esquiva = Math.min(1.0, Math.max(0.0, esquiva));
    }

    public int getVidamaxima() {
        return vidamaxima;
    }

    public Inventario getInventario() {
        return inventario;
    }
}